package com.elastic;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import com.util.Mapper;

public class GetAPI {
	
	private Logger logger = Logger.getLogger(GetAPI.class);
	private static final String INDEX_NAME = "candidates";
	
	public void getAPI(RestHighLevelClient restHighClient, String documentId) {
		GetRequest getRequest = new GetRequest(INDEX_NAME, "default", documentId);
		
		String[] includes = new String[] {CandidateIndexName.FULL_NAME.getValue(), CandidateIndexName.YEAR_OF_BIRTH.getValue(),
				CandidateIndexName.EMAILS.getValue(), CandidateIndexName.PHONES.getValue(), CandidateIndexName.SKYPE.getValue()};
		String[] excludes = Strings.EMPTY_ARRAY;
		
		FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
		getRequest.fetchSourceContext(fetchSourceContext);
		
		try {
			GetResponse getResponse = restHighClient.get(getRequest, RequestOptions.DEFAULT);
		
			String index = getResponse.getIndex();
			String type = getResponse.getType();
			String id = getResponse.getId();
			
			if(getResponse.isExists()) {
				long version = getResponse.getVersion();
				String sourceAsString = getResponse.getSourceAsString();
				Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
				CandidateIndexData candidateIndexData = Mapper.convertMaptoCandidateIndexData(sourceAsMap);
				
				logger.info("Get response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
//				logger.info("Get response: source = " + sourceAsString);
				logger.info("Get response: source = " + candidateIndexData.toString());
			} else {
				logger.info("Get response: Document not exist !");
			}
		} catch (IOException e) {
			logger.error("Get response: " + e.getMessage());
		}
	}

}
