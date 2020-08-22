package com.elastic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.DocWriteRequest.OpType;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;

public class BulkAPI {
	
	private Logger logger = Logger.getLogger(BulkAPI.class);
	private static final String INDEX_NAME = "candidates";
	
	/*
	 * Have another special method is Bulk Processor
	 */
	
	private Map<String, Object> buildMap(String documentId){
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("id", documentId);
		jsonMap.put("fullName", "Dao Quang ".concat(documentId));
		jsonMap.put("yearOfBirth", 1996);
		jsonMap.put("emails", "dinh.dao".concat(documentId).concat("@gmail.com"));
		jsonMap.put("phones", "0123456789");
		jsonMap.put("skype", "daoquangdinh_".concat(documentId));
		
		return jsonMap;
	}

	public void bulkAPI(RestHighLevelClient restHighClient) {
		BulkRequest bulkRequest = new BulkRequest();
		for (Integer i = 1; i <= 5; i++) {
			bulkRequest.add(new IndexRequest(INDEX_NAME, "default", i.toString())
					.source(buildMap(i.toString())));
		}
		bulkRequest.timeout(TimeValue.timeValueMinutes(1));
		
		try {
			BulkResponse bulkResponse = restHighClient.bulk(bulkRequest, RequestOptions.DEFAULT);
			
			for (BulkItemResponse bulkItemResponse : bulkResponse) {
				if(!bulkItemResponse.isFailed()) {
					DocWriteResponse itemResponse = bulkItemResponse.getResponse();
					if(bulkItemResponse.getOpType() == OpType.INDEX || bulkItemResponse.getOpType() == OpType.CREATE) {
						IndexResponse indexResponse = (IndexResponse) itemResponse;
						logger.info("Bulk response: index or create !");
					} else if (bulkItemResponse.getOpType() == OpType.UPDATE) {
						UpdateResponse updateResponse = (UpdateResponse) itemResponse;
						logger.info("Bulk response: update !");
					} else if (bulkItemResponse.getOpType() == OpType.DELETE) {
						DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
						logger.info("Bulk response: delete !");
					}
				} else {
					BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
					logger.info("Bulk response: " + failure.toString());
				}
			}
		} catch (IOException e) {
			logger.error("Bulk response: " + e.getMessage());
		}
	}
	
}
