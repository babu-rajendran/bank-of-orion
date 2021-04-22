package com.bank.orion.payload.accountHolder;

import com.bank.orion.payload.common.ReadPayload;
import com.bank.orion.payload.common.ReadRequest;

public class ReadAccountHolderRequest extends ReadRequest {

	public ReadAccountHolderRequest(String keyValue) {
		ReadPayload payload = getPayload();
		payload.setTableName("AccountHolder");
		payload.setKey(new AccountHolderKey(keyValue));
	}
}