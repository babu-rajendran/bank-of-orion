package com.bank.orion.payload.common;

import com.bank.orion.payload.Key;
import com.bank.orion.payload.Payload;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadPayload extends Payload {

	private Key key;

	@JsonProperty("Key")
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

}