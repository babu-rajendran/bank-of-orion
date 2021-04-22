package com.bank.orion.payload.account;

import com.bank.orion.payload.common.ReadPayload;
import com.bank.orion.payload.common.ReadRequest;

public class ReadAccountRequest extends ReadRequest {

	public ReadAccountRequest(String keyValue) {
		ReadPayload payload = getPayload();
		payload.setTableName("Account");
		payload.setKey(new AccountKey(keyValue));
	}
}