package com.bank.orion.payload.accountHolder;

import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.common.UpdatePayload;
import com.bank.orion.payload.common.UpdateRequest;

public class UpdateAccountHolderRequest extends UpdateRequest {

	public UpdateAccountHolderRequest(String keyValue, String updateExpression, ExpressionAttributeValues attributes) {
		AccountHolderKey key = new AccountHolderKey(keyValue);
		UpdatePayload payload = getPayload();
		payload.setTableName("AccountHolder");
		payload.setKey(key);
		payload.setUpdateExpression(updateExpression);
		payload.setExpAttrVals(attributes);
		payload.setConditionExpression(key.retrieveKeyName());
	}
}
