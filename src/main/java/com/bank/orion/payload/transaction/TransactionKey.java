package com.bank.orion.payload.transaction;

import com.bank.orion.payload.Key;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionKey implements Key {

	private String transactionID;

	public TransactionKey() {
	}

	public TransactionKey(String transactionID) {
		setTransactionID(transactionID);
	}

	public String retrieveKeyName() {
		return "transactionID";
	}

	@JsonProperty("transactionID")
	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

}
