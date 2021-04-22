package com.bank.orion.payload.accountHolder;

import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.common.ListPayload;
import com.bank.orion.payload.common.ListRequest;

public class ListAccountHolderRequest extends ListRequest {

	public ListAccountHolderRequest(String filterExpression, ExpressionAttributeValues attributes) {
		ListPayload payload = getPayload();
		payload.setTableName("AccountHolder");
		payload.setFilterExpression(filterExpression);
		payload.setExpAttrVals(attributes);
	}

}
