package com.bank.orion.payload.common;

import com.bank.orion.payload.Payload;
import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListPayload extends Payload {

	private String filterExpression;
	private ExpressionAttributeValues expAttrVals;

	@JsonProperty("FilterExpression")
	public String getFilterExpression() {
		return filterExpression;
	}

	public void setFilterExpression(String filterExpression) {
		this.filterExpression = filterExpression;
	}

	@JsonProperty("ExpressionAttributeValues")
	public ExpressionAttributeValues getExpAttrVals() {
		return expAttrVals;
	}

	public void setExpAttrVals(ExpressionAttributeValues expAttrVals) {
		this.expAttrVals = expAttrVals;
	}
}
