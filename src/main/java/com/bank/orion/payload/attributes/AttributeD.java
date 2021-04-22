package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeD extends AttributeC {

	String d;

	@JsonProperty(":d")
	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

}
