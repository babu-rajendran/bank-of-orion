package com.bank.orion.payload.account;

import com.bank.orion.payload.Key;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountKey implements Key {

	private String accountNumber;

	public AccountKey() {
	}

	public AccountKey(String accountNumber) {
		setAccountNumber(accountNumber);
	}

	@JsonProperty("accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}

	public String retrieveKeyName() {
		return "accountNumber";
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
