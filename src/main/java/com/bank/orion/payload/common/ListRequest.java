package com.bank.orion.payload.common;

import com.bank.orion.payload.Request;

public class ListRequest extends Request {
	
	private ListPayload payload;
	
	public ListRequest() {
		setOperation("list");
		setPayload(new ListPayload());
	}

	public ListPayload getPayload() {
		return payload;
	}

	public void setPayload(ListPayload payload) {
		this.payload = payload;
	}

}
