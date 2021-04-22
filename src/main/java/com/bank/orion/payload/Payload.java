package com.bank.orion.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

	private String tableName;

	@JsonProperty("TableName")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
