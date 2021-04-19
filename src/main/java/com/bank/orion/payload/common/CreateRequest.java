package com.bank.orion.payload.common;

import com.bank.orion.payload.Request;

public class CreateRequest extends Request {

	private CreatePayload payload;

	public CreateRequest() {
		setOperation("create");
		setPayload(new CreatePayload());
	}

	public CreatePayload getPayload() {
		return payload;
	}

	public void setPayload(CreatePayload payload) {
		this.payload = payload;
	}

}
