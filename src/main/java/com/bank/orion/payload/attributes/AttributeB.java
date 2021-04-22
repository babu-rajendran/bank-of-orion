package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeB extends AttributeA {

	String b;

	@JsonProperty(":b")
	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}
