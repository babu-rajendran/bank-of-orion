package com.bank.orion.payload.accountHolder;

import com.bank.orion.payload.Key;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountHolderKey implements Key {

	private String emailID;

	public AccountHolderKey() {
	}

	public AccountHolderKey(String emailID) {
		setEmailID(emailID);
	}

	public String retrieveKeyName() {
		return "emailID";
	}

	@JsonProperty("emailID")
	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}
