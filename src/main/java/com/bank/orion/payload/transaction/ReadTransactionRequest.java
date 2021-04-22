package com.bank.orion.payload.transaction;

import com.bank.orion.payload.common.ReadPayload;
import com.bank.orion.payload.common.ReadRequest;

public class ReadTransactionRequest extends ReadRequest {

	public ReadTransactionRequest(String keyValue) {
		ReadPayload payload = getPayload();
		payload.setTableName("Transaction");
		payload.setKey(new TransactionKey(keyValue));
	}
}