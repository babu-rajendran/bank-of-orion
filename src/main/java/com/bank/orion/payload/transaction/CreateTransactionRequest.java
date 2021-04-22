package com.bank.orion.payload.transaction;

import com.bank.orion.model.Transaction;
import com.bank.orion.payload.common.CreatePayload;
import com.bank.orion.payload.common.CreateRequest;

public class CreateTransactionRequest extends CreateRequest {

	public CreateTransactionRequest(Transaction transsaction) {
		CreatePayload payload = getPayload();
		payload.setTableName("Transaction");
		payload.setItem(transsaction);
		payload.setConditionExpression(new TransactionKey().retrieveKeyName());
	}
}
