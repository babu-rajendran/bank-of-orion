package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeG extends AttributeF {

	String g;

	@JsonProperty(":g")
	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

}
