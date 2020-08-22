package com.elastic;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class DeleteAPI {
	
	private Logger logger = Logger.getLogger(DeleteAPI.class);
	private static final String INDEX_NAME = "candidates";
	
	public void deleteAPI(RestHighLevelClient restHighClient, String documentId) {
		DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME, "default", documentId);
		
		try {
			DeleteResponse deleteResponse = restHighClient.delete(deleteRequest, RequestOptions.DEFAULT);
		
			if(deleteResponse.getResult() == Result.NOT_FOUND) {
				logger.info("Delete response: document not found !");
			} else {
				String index = deleteResponse.getIndex();
				String type = deleteResponse.getType();
				String id = deleteResponse.getId();
				long version = deleteResponse.getVersion();
				
				logger.info("Delete response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
			}
		} catch (IOException e) {
			logger.error("Delete response: " + e.getMessage());
		}
	}
	
}
