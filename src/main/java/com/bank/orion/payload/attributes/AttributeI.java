package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeI extends AttributeH {

	String i;

	@JsonProperty(":i")
	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

}
