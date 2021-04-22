package com.bank.orion.payload.common;

import com.bank.orion.payload.Request;

public class ReadRequest extends Request {

	private ReadPayload payload;

	public ReadRequest() {
		setOperation("read");
		setPayload(new ReadPayload());
	}

	public ReadPayload getPayload() {
		return payload;
	}

	public void setPayload(ReadPayload payload) {
		this.payload = payload;
	}
}
