package com.bank.orion.repository;

import com.bank.orion.util.Mapper;
import com.bank.orion.util.OrionDBConnection;

import aws.apigateway.db.model.PostDynamodbRequest;
import aws.apigateway.db.model.PostDynamodbResult;
import aws.apigateway.db.model.StringRequest;

public class Repository {

	protected Mapper mapper = Mapper.getMapper();
	protected OrionDBConnection connection;
	protected PostDynamodbRequest request;
	protected StringRequest strReq;
	protected String stringPayload;
	protected PostDynamodbResult result;

	protected void initNewDbRequest() {
		connection = new OrionDBConnection();
		request = new PostDynamodbRequest();
		strReq = new StringRequest();
	}

}
