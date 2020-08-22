package com.elastic;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ConnectionAPI {
	
	private Logger logger = Logger.getLogger(ConnectionAPI.class);
	
	public RestHighLevelClient createConnection() {
		RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
		logger.info("*** Open connection successfully ***");
		return new RestHighLevelClient(restClientBuilder);
	}
	
	public void closeConnection(RestHighLevelClient restHighClient) {
		try {
			restHighClient.close();
			logger.info("*** Close connection successfully ***");
		} catch (IOException e) {
			logger.error("CLose client: " + e.getMessage());
		}
	}

}
