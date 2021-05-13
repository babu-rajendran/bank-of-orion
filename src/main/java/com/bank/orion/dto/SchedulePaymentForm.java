package com.bank.orion.dto;

public class SchedulePaymentForm {
	private String amount;
	private String transactionDate;
	private String description;
	private String receivingAccountNumber;
	private String receivingRounting;
	private String isRecur;
	private String interval;
	private String times;
	private String sendingAccountNumber;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceivingAccountNumber() {
		return receivingAccountNumber;
	}

	public void setReceivingAccountNumber(String receivingAccountNumber) {
		this.receivingAccountNumber = receivingAccountNumber;
	}

	public String getReceivingRounting() {
		return receivingRounting;
	}

	public void setReceivingRounting(String receivingRounting) {
		this.receivingRounting = receivingRounting;
	}

	public String getIsRecur() {
		return isRecur;
	}

	public void setIsRecur(String isRecur) {
		this.isRecur = isRecur;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getSendingAccountNumber() {
		return sendingAccountNumber;
	}

	public void setSendingAccountNumber(String sendingAccountNumber) {
		this.sendingAccountNumber = sendingAccountNumber;
	}

}
