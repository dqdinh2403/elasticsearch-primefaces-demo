package com.elastic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;

import com.util.CommonUtil;
import com.util.Mapper;

public class UpdateAPI {
	
	private Logger logger = Logger.getLogger(UpdateAPI.class);
	private static final String INDEX_NAME = "candidates";
	private static final String FILE_NAME = "src/main/resources/json/candidate_update.json";

	/* instead of using updateReuquest.doc(), using updateReuquest.upsert() in case
	 * the document does not already exist, define and insert new document
	 */
	
	/*
	 * updateRequest.retryOnConflict(3): how many times to retry the update operation if the document
	 * has been changed by another operation between the get and indexing phases of the update operation
	 */
	
	/* updateRequest.fetchSource(true): enable source retrieval, default is disabled
	 */
	
	public void updateUByJsonString(RestHighLevelClient restHighClient, String documentId) {
		UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, "default", documentId);
		String jsonString = CommonUtil.readJsonFile(FILE_NAME);
		updateRequest.doc(jsonString, XContentType.JSON);
		updateRequest.retryOnConflict(3);
		updateRequest.fetchSource(true);
		
		try {
			UpdateResponse updateResponse = restHighClient.update(updateRequest, RequestOptions.DEFAULT);
		
			String index = updateResponse.getIndex();
			String type = updateResponse.getType();
			String id = updateResponse.getId();
			long version = updateResponse.getVersion();
			Result updateResult = updateResponse.getResult();
			
			if(updateResult == Result.UPDATED) {
				logger.info("Update response: document was updated !");
				logger.info("Update response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
				
				GetResult updateGetResult = updateResponse.getGetResult();
				if (updateGetResult.isExists()) {
					String sourceAsString = updateGetResult.sourceAsString();
					Map<String, Object> sourceAsMap = updateGetResult.sourceAsMap();
					CandidateIndexData candidateIndexData = Mapper.convertMaptoCandidateIndexData(sourceAsMap);
					logger.info("Update response: source = " + candidateIndexData.toString());
				} else {
					logger.info("Update response: GetResult not exist !");
				}
			} else if(updateResult == Result.DELETED) {
				logger.info("Update response: document was deleted, update fail !");
			} else if(updateResult == Result.NOOP) {
				logger.info("Update response: document was noop, update fail !");
			}
		} catch (IOException e) {
			logger.error("Update response: " + e.getMessage());
		}
	}
	
	public void updateByJsonMap(RestHighLevelClient restHighClient, String documentId) {
		UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, "default", documentId);

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("fullName", "Dao Quang update");
		jsonMap.put("emails", "dinh.dao1.update@gmail.com");
		jsonMap.put("skype", "daoquangdinh_1_update");
		
		updateRequest.doc(jsonMap);
		updateRequest.retryOnConflict(3);
		updateRequest.fetchSource(true);
		
		try {
			UpdateResponse updateResponse = restHighClient.update(updateRequest, RequestOptions.DEFAULT);
		
			String index = updateResponse.getIndex();
			String type = updateResponse.getType();
			String id = updateResponse.getId();
			long version = updateResponse.getVersion();
			Result updateResult = updateResponse.getResult();
			
			if(updateResult == Result.UPDATED) {
				logger.info("Update response: document was updated !");
				logger.info("Update response: index = " + index + "; type = " + type + "; id = " + id + "; version = " + version);
				
				GetResult updateGetResult = updateResponse.getGetResult();
				if (updateGetResult.isExists()) {
					String sourceAsString = updateGetResult.sourceAsString();
					Map<String, Object> sourceAsMap = updateGetResult.sourceAsMap();
					CandidateIndexData candidateIndexData = Mapper.convertMaptoCandidateIndexData(sourceAsMap);
					logger.info("Update response: source = " + candidateIndexData.toString());
				} else {
					logger.info("Update response: GetResult not exist !");
				}
			} else if(updateResult == Result.DELETED) {
				logger.info("Update response: document was deleted, update fail !");
			} else if(updateResult == Result.NOOP) {
				logger.info("Update response: document was noop, update fail !");
			}
		} catch (IOException e) {
			logger.error("Update response: " + e.getMessage());
		}
	}
	
}
