package com.bank.orion.payload.common;

import com.bank.orion.payload.Request;

public class UpdateRequest extends Request {

	private UpdatePayload payload;

	public UpdateRequest() {
		setOperation("update");
		setPayload(new UpdatePayload());
	}

	public UpdatePayload getPayload() {
		return payload;
	}

	public void setPayload(UpdatePayload payload) {
		this.payload = payload;
	}

}
