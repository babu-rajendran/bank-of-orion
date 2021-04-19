package com.bank.orion.payload.account;

import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.common.ListPayload;
import com.bank.orion.payload.common.ListRequest;

public class ListAccountRequest extends ListRequest {

	public ListAccountRequest(String filterExpression, ExpressionAttributeValues attributes) {
		ListPayload payload = getPayload();
		payload.setTableName("Account");
		payload.setFilterExpression(filterExpression);
		payload.setExpAttrVals(attributes);
	}

}
