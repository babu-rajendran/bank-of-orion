package com.bank.orion.util;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;

import aws.apigateway.db.OrionBankDynamoDB;
import aws.apigateway.db.model.PostDynamodbRequest;
import aws.apigateway.db.model.PostDynamodbResult;

public class OrionDBConnection {

	private OrionBankDynamoDB client;

	public OrionDBConnection() {
		initSdk();
	}

	// The configuration settings are for illustration purposes and may not be a
	// recommended best practice.
	private void initSdk() {
		client = OrionBankDynamoDB.builder()
				.connectionConfiguration(
						new ConnectionConfiguration().maxConnections(100).connectionMaxIdleMillis(1000))
				.timeoutConfiguration(new TimeoutConfiguration().httpRequestTimeout(3000).totalExecutionTimeout(10000)
						.socketTimeout(2000))
				.build();

	}

	public PostDynamodbResult post(PostDynamodbRequest request) {
		return client.postDynamodb(request);
	}

	// Calling shutdown is not necessary unless you want to exert explicit control
	// of this resource.
	public void shutdown() {
		client.shutdown();
	}
}
