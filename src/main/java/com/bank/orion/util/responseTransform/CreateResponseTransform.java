package com.bank.orion.util.responseTransform;

import com.bank.orion.util.Mapper;
import com.bank.orion.util.responseTransform.bo.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import aws.apigateway.db.model.PostDynamodbResult;

public class CreateResponseTransform {
	
	private static Mapper mapper = Mapper.getMapper();
	
	
	public static String transformCreateResponse(PostDynamodbResult result) throws JsonMappingException, JsonProcessingException {
		String response = result.getStringResponse().getResponse();
		
		if (response.contains("errorMessage")) {
			ErrorMessage errorMessage = mapper.readValue(response, ErrorMessage.class);
			return errorMessage.getErrorMessage();
		}
		return "OK";
	}
	 
}
