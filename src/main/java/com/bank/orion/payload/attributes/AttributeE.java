package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeE extends AttributeD {

	String e;

	@JsonProperty(":e")
	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

}
