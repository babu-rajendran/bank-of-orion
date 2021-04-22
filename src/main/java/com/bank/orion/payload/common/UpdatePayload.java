package com.bank.orion.payload.common;

import com.bank.orion.payload.Key;
import com.bank.orion.payload.Payload;
import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePayload extends Payload {

	private Key key;
	private String updateExpression;
	private ExpressionAttributeValues expAttrVals;
	private String conditionExpression;
	private final String returnValues = "ALL_NEW";
	

	@JsonProperty("Key")
	public Key getKey() {
		return key;
	}
	
	@JsonProperty("UpdateExpression")
	public String getUpdateExpression() {
		return updateExpression;
	}

	@JsonProperty("ExpressionAttributeValues")
	public ExpressionAttributeValues getExpAttrVals() {
		return expAttrVals;
	}

	@JsonProperty("ConditionExpression")
	public String getConditionExpression() {
		return conditionExpression;
	}

	@JsonProperty("ReturnValues")
	public String getReturnValues() {
		return returnValues;
	}

	public void setConditionExpression(String key) {
		conditionExpression = "attribute_exists(" + key + ")";
	}

	public void setExpAttrVals(ExpressionAttributeValues expAttrVals) {
		this.expAttrVals = expAttrVals;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public void setUpdateExpression(String updateExpression) {
		this.updateExpression = updateExpression;
	}

}
