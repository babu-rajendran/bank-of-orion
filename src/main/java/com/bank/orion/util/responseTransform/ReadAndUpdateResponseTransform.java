package com.bank.orion.util.responseTransform;

import com.bank.orion.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import aws.apigateway.db.model.PostDynamodbResult;

public class ReadAndUpdateResponseTransform {

	private static Mapper mapper = Mapper.getMapper();

	public static <T> T transformRUResponse(PostDynamodbResult result, Class<T> responseType)
			throws JsonMappingException, JsonProcessingException {
		String response = result.getStringResponse().getResponse();

		if (response.contains("errorMessage")) {
			return null;
		} else {
			return mapper.readValue(response, responseType);
		}
	}
}
