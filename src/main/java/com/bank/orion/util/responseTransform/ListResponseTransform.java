package com.bank.orion.util.responseTransform;

import com.bank.orion.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import aws.apigateway.db.model.PostDynamodbResult;

public class ListResponseTransform {

	private static Mapper mapper = Mapper.getMapper();

	public static <T> T transformListResponse(PostDynamodbResult result, TypeReference<T> responseTypeRef)
			throws JsonMappingException, JsonProcessingException {
		String response = result.getStringResponse().getResponse();

		if (response.contains("errorMessage")) {
			return null;
		} else {
			return mapper.readValue(response, responseTypeRef);
		}
	}
}
