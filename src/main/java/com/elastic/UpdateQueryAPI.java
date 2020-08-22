package com.elastic;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;

public class UpdateQueryAPI {
	
	private Logger logger = Logger.getLogger(UpdateQueryAPI.class);
	private static final String INDEX_NAME = "candidates";
	
	/*
	 * Chua chay thu :v
	 * DeleteByQueryRequest : the same :v
	 */

	public void updateByQueryRequestAPI(RestHighLevelClient restHighClient) {
		UpdateByQueryRequest updateQueryRequest = new UpdateByQueryRequest(INDEX_NAME);
		/*
		 * Only copy documents which have field user set to kimchy
		 * updateQueryRequest.setQuery(new TermQueryBuilder("user", "kimchy"));
		 */
		
		try {
			BulkByScrollResponse bulkResponse = restHighClient.updateByQuery(updateQueryRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			logger.error("UpdateByQuery response: " + e.getMessage());
		}
		
	}
	
}
