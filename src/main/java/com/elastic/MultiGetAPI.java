package com.elastic;

import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import com.util.Mapper;

public class MultiGetAPI {

	private Logger logger = Logger.getLogger(MultiGetAPI.class);
	private static final String INDEX_NAME = "candidates";
	
	public void multiGetAPI(RestHighLevelClient restHighClient, List<String> documentIds) {
		MultiGetRequest multiGetRequest = new MultiGetRequest();
		for (String documentId : documentIds) {
			multiGetRequest.add(new MultiGetRequest.Item(INDEX_NAME, "default", documentId));
		}
		
		try {
			MultiGetResponse multiGetResponse = restHighClient.mget(multiGetRequest, RequestOptions.DEFAULT);
			
			for (int i = 0; i < 5; i++) {
				MultiGetItemResponse itemResponse = multiGetResponse.getResponses()[i];
				assertNull(itemResponse.getFailure());
				GetResponse getResponse = itemResponse.getResponse();
				String index = getResponse.getIndex();
				String type = getResponse.getType();
				String id = getResponse.getId();
				if(getResponse.isExists()) {
					long version = getResponse.getVersion();
					Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
					CandidateIndexData candidateIndexData = Mapper.convertMaptoCandidateIndexData(sourceAsMap);
					logger.info("Get " + i + " response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
					logger.info("Get " + i + " response: source = " + candidateIndexData.toString());
				}else {
					logger.info("MultiGet response: " + i +" get response is not exist !");
				}
			}
		} catch (IOException e) {
			logger.error("MultiGet response: " + e.getMessage());
		}
	}
	
}
