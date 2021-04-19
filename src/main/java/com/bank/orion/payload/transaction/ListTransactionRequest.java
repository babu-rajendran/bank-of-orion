package com.bank.orion.payload.transaction;

import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.common.ListPayload;
import com.bank.orion.payload.common.ListRequest;

public class ListTransactionRequest extends ListRequest {

	public ListTransactionRequest(String filterExpression, ExpressionAttributeValues attributes) {
		ListPayload payload = getPayload();
		payload.setTableName("Transaction");
		payload.setFilterExpression(filterExpression);
		payload.setExpAttrVals(attributes);
	}

}
