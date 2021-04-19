package com.bank.orion.payload.account;

import com.bank.orion.model.Account;
import com.bank.orion.payload.common.CreatePayload;
import com.bank.orion.payload.common.CreateRequest;

public class CreateAccountRequest extends CreateRequest {

	public CreateAccountRequest(Account account) {
		CreatePayload payload = getPayload();
		payload.setTableName("Account");
		payload.setItem(account);
		payload.setConditionExpression(new AccountKey().retrieveKeyName());
	}
}
