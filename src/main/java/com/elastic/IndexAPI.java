package com.elastic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.util.CommonUtil;

public class IndexAPI {
	
	private Logger logger = Logger.getLogger(IndexAPI.class);
	private static final String INDEX_NAME = "candidates";
	private static final String FILE_NAME = "src/main/resources/json/candidate_index.json";

	public void indexByJsonString(RestHighLevelClient restHighClient, String documentId) {
		IndexRequest indexRequest = new IndexRequest(INDEX_NAME, "default", documentId);
		String jsonString = CommonUtil.readJsonFile(FILE_NAME);
		indexRequest.source(jsonString, XContentType.JSON);
		
		try {
			IndexResponse indexResponse = restHighClient.index(indexRequest, RequestOptions.DEFAULT);
		
			String index = indexResponse.getIndex();
			String type = indexResponse.getType();
			String id = indexResponse.getId();
			long version = indexResponse.getVersion();
			
			if(indexResponse.getResult() == Result.CREATED)
				logger.info("Index response: is created !");
			else if(indexResponse.getResult() == Result.UPDATED)
				logger.info("Index response: is updated !");
		
			logger.info("Index response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
		} catch (IOException e) {
			logger.info("Index response: " + e.getMessage());
		}
	}
	
	public void indexByJsonMap(RestHighLevelClient restHighClient, String documentId) {
		IndexRequest indexRequest = new IndexRequest(INDEX_NAME, "default", documentId);
		
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", 1);
		jsonMap.put("fullName", "Dao Quang 1");
		jsonMap.put("yearOfBirth", 1996);
		jsonMap.put("emails", "dinh.dao1@gmail.com");
		jsonMap.put("phones", "0123456789");
		jsonMap.put("skype", "daoquangdinh_1");
		
		indexRequest.source(jsonMap);
		
		try {
			IndexResponse indexResponse = restHighClient.index(indexRequest, RequestOptions.DEFAULT);
		
			String index = indexResponse.getIndex();
			String type = indexResponse.getType();
			String id = indexResponse.getId();
			long version = indexResponse.getVersion();
			
			if(indexResponse.getResult() == Result.CREATED)
				logger.info("Index response: is created !");
			else if(indexResponse.getResult() == Result.UPDATED)
				logger.info("Index response: is updated !");
		
			logger.info("Index response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
		} catch (IOException e) {
			logger.error("Index response: " + e.getMessage());
		}
	}
	
}
