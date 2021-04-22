package com.bank.orion.payload.accountHolder;

import com.bank.orion.model.AccountHolder;
import com.bank.orion.payload.common.CreatePayload;
import com.bank.orion.payload.common.CreateRequest;

public class CreateAccountHolderRequest extends CreateRequest {

	public CreateAccountHolderRequest(AccountHolder accountHolder) {
		CreatePayload payload = getPayload();
		payload.setTableName("AccountHolder");
		payload.setItem(accountHolder);
		payload.setConditionExpression(new AccountHolderKey().retrieveKeyName());
	}
}
