package com.bank.orion.payload.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeH extends AttributeG {

	String h;

	@JsonProperty(":h")
	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}
}
