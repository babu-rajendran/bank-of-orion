package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeC extends AttributeB {

	String c;

	@JsonProperty(":c")
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
