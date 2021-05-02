package com.bank.orion.service;

import java.util.List;

import com.bank.orion.model.Transaction;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TransactionService {

	protected TransactionRepository transactionRepo = new TransactionRepository();

	public List<Transaction> getTransactionsWithAcctNum(String accountNumber) throws JsonProcessingException {

		String filterExpression = "receivingAccountNumber =:a or sendingAccountNumber =:b";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(accountNumber);
		listAttrs.setB(accountNumber);

		List<Transaction> listResponse = transactionRepo.listTransaction(filterExpression, listAttrs);
		listResponse.forEach(System.out::println);
		return listResponse;
	}

	public String updateTransactionStatus(String transactionID, String status) {

		String updateExpression = "set transactionStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA(status);
		Transaction updateResponse;
		try {
			updateResponse = transactionRepo.updateTransaction(transactionID, updateExpression, updateAttrs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading transaction response";
		}

		System.out.println(updateResponse);

		if (updateResponse == null || !updateResponse.getTransactionStatus().equals(status)) {
			return "Error while updating transaction status";
		}

		return "Success in updating transaction status";
	}

	public String createTransaction(String amount, String transactionDate, String description,
			String receivingAccountNumber, String sendingAccountNumber, String transactionStatus, String transactionID,
			String receivingRounting) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setReceivingAccountNumber(receivingAccountNumber);
		transaction.setReceivingRounting(receivingRounting);
		transaction.setSendingAccountNumber(sendingAccountNumber);
		transaction.setTransactionDate(transactionDate);
		transaction.setTransactionID(transactionID);
		transaction.setTransactionStatus(transactionStatus);

		String readResponse;
		try {
			readResponse = transactionRepo.createTransaction(transaction);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while creating transaction record";
		}

		return readResponse;
	}

}
