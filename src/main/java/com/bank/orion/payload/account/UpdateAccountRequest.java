package com.bank.orion.payload.account;

import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.common.UpdatePayload;
import com.bank.orion.payload.common.UpdateRequest;

public class UpdateAccountRequest extends UpdateRequest {

	public UpdateAccountRequest(String keyValue, String updateExpression, ExpressionAttributeValues attributes) {
		AccountKey key = new AccountKey(keyValue);
		UpdatePayload payload = getPayload();
		payload.setTableName("Account");
		payload.setKey(key);
		payload.setUpdateExpression(updateExpression);
		payload.setExpAttrVals(attributes);
		payload.setConditionExpression(key.retrieveKeyName());
	}
}
