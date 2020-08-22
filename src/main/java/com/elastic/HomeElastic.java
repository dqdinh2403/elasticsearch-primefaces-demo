package com.elastic;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.RestHighLevelClient;

public class HomeElastic {
	
	public static void main(String[] args) {
		String documentId = "1";
		String indexName = "candidates";
		
		ConnectionAPI connectionES = new ConnectionAPI();
		RestHighLevelClient restHighClient = connectionES.createConnection();
		
//		IndexAPI indexES = new IndexAPI();
//		indexES.indexByJsonString(restHighClient, documentId);
////		indexES.indexByJsonMap(restHighClient, documentId);
		
//		GetAPI getES = new GetAPI();
//		getES.getAPI(restHighClient, documentId);
		
//		UpdateAPI updateES = new UpdateAPI();
//		updateES.updateUByJsonString(restHighClient, documentId);
////		updateES.updateByJsonMap(restHighClient, documentId);
		
//		DeleteAPI deleteES = new DeleteAPI();
//		deleteES.deleteAPI(restHighClient, documentId);
		
//		BulkAPI bulkES = new BulkAPI();
//		bulkES.bulkAPI(restHighClient);
		
//		MultiGetAPI multiGetES = new MultiGetAPI();
//		List<String> documentIds = new ArrayList<>();
//		documentIds.add("1"); documentIds.add("2"); documentIds.add("3"); documentIds.add("4"); documentIds.add("5");
//		multiGetES.multiGetAPI(restHighClient, documentIds);
		
//		ReindexAPI reindexES = new ReindexAPI();
//		reindexES.reindexAPI(restHighClient, indexName, "candidates2");
		
		connectionES.closeConnection(restHighClient);
	}
}
