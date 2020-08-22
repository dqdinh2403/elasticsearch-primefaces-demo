package com.elastic;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.index.reindex.ScrollableHitSource;

public class ReindexAPI {
	
	private Logger logger = Logger.getLogger(ReindexAPI.class); 
	
	/*
	 * Error ko chay duoc :v
	 * Copy document tu index nay sang index khac (ko kem theo setting, mapping)
	 */
	
	public void reindexAPI(RestHighLevelClient restHighClient, String source, String destination) {
		ReindexRequest reindexRequest = new ReindexRequest();
		reindexRequest.setSourceIndices(source);
		reindexRequest.setDestIndex(destination);
		
		reindexRequest.setDestVersionType(VersionType.INTERNAL);
		reindexRequest.setDestOpType("create");
		reindexRequest.setConflicts("proceed");
		reindexRequest.setSourceDocTypes("doc");
		
		try {
			BulkByScrollResponse bulkResponse = restHighClient.reindex(reindexRequest, RequestOptions.DEFAULT);
		
			TimeValue timeTaken = bulkResponse.getTook();
			boolean timedOut = bulkResponse.isTimedOut();
			long totalDocs = bulkResponse.getTotal();
			long updatedDocs = bulkResponse.getUpdated();
			long createdDocs = bulkResponse.getCreated();
			long deletedDocs = bulkResponse.getDeleted();
			long batches = bulkResponse.getBatches();
			long noops = bulkResponse.getNoops();
			long versionConflicts = bulkResponse.getVersionConflicts();
			long bulkRetries = bulkResponse.getBulkRetries();
			long searchRetries = bulkResponse.getSearchRetries();
			TimeValue throttledMillis = bulkResponse.getStatus().getThrottled();
			TimeValue throttledUntilMillis = bulkResponse.getStatus().getThrottledUntil();
			List<ScrollableHitSource.SearchFailure> searchFailures = bulkResponse.getSearchFailures();
			List<BulkItemResponse.Failure> bulkFailures = bulkResponse.getBulkFailures();
		
		} catch (IOException e) {
			logger.error("Reindex response: " + e.getMessage());
		}
		
	}
	
}
