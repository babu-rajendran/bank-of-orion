package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeF extends AttributeE {

	String f;

	@JsonProperty(":F")
	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

}
