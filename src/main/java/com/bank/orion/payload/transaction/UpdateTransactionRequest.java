package com.bank.orion.payload.transaction;

import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.common.UpdatePayload;
import com.bank.orion.payload.common.UpdateRequest;

public class UpdateTransactionRequest extends UpdateRequest {

	public UpdateTransactionRequest(String keyValue, String updateExpression, ExpressionAttributeValues attributes) {
		TransactionKey key = new TransactionKey(keyValue);
		UpdatePayload payload = getPayload();
		payload.setTableName("Transaction");
		payload.setKey(key);
		payload.setUpdateExpression(updateExpression);
		payload.setExpAttrVals(attributes);
		payload.setConditionExpression(key.retrieveKeyName());
	}
}
