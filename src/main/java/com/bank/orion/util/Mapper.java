package com.bank.orion.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

	private volatile static Mapper mapper;
	private static ObjectMapper objectMapper;

	private Mapper() {
	}

	public static Mapper getMapper() {
		if (mapper == null) {
			synchronized (ObjectMapper.class) {
				if (mapper == null) {
					mapper = new Mapper();
					objectMapper = new ObjectMapper();
					objectMapper.setSerializationInclusion(Include.NON_NULL);
				}
			}
		}

		return mapper;
	}

	public String writeValueAsString(Object o) throws JsonProcessingException {
		return objectMapper.writeValueAsString(o);
	}

	public <T> T readValue(String content, Class<T> valueType) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(content, valueType);
	}

	public <T> T readValue(String content, TypeReference<T> valueTypeRef)
			throws JsonProcessingException, JsonMappingException {
		return objectMapper.readValue(content, valueTypeRef);
	}
}
