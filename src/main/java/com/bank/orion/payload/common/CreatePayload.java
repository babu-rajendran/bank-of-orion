package com.bank.orion.payload.common;

import com.bank.orion.model.Item;
import com.bank.orion.payload.Payload;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePayload extends Payload {

	private Item item;
	private String conditionExpression;

	@JsonProperty("Item")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@JsonProperty("ConditionExpression")
	public String getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(String key) {
		conditionExpression = "attribute_not_exists(" + key + ")";
	}

	@Override
	public String toString() {
		return "CreatePayload [item=" + item + ", conditionExpression=" + conditionExpression + "]";
	}

}
