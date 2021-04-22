package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeA implements ExpressionAttributeValues {

	String a;

	@JsonProperty(":a")
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

}
