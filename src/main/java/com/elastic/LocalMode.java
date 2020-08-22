package com.elastic;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.util.CommonUtil;

public class LocalMode {

public static void main(String[] args) throws IOException {
		
		String host = "localhost";
		String index = "candidates";
		int port = 9200;
		String protocol = "http";
		String type = "default";
		
		String settingURL = "src/main/resources/json/candidate_index_setting.json";
		String mappingURL = "src/main/resources/json/candidate_index_mapping.json";
		
		Logger logger = Logger.getLogger(LocalMode.class);
		
		RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port, protocol));
		RestHighLevelClient restHighClient = new RestHighLevelClient(restClientBuilder);
		logger.info("Open connection: successfully !");
		
		GetIndexRequest getIndexRequest = new GetIndexRequest();
		getIndexRequest.indices(index);
		boolean indexExist = restHighClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
		if(indexExist) {
			DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
			restHighClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
			logger.info("Delete index: successfully !");
		}
		
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
		createIndexRequest.source(CommonUtil.readJsonFile(settingURL), XContentType.JSON);
		restHighClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		logger.info("Index: successfully !");
		
		PutMappingRequest putMappingRequest = new PutMappingRequest(index);
		putMappingRequest.type(type);
		putMappingRequest.source(CommonUtil.readJsonFile(mappingURL), XContentType.JSON);
		restHighClient.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
		logger.info("Mapping: successfully !");
		
		restHighClient.close();
		logger.info("Close connection: successfully !");
	}
}
