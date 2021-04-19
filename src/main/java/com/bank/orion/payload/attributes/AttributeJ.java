package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeJ extends AttributeI {

	String j;

	@JsonProperty(":j")
	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

}
