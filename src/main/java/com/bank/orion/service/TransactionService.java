package com.bank.orion.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import com.bank.orion.model.Transaction;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TransactionService {

	private TransactionRepository transactionRepo = new TransactionRepository();
	public static final String BANK_ROUTING_NUMBER = "12345";
	private AccountService accountService = new AccountService();

	public Transaction getTransactionWithTransactionID(String transactionID) throws JsonProcessingException {
		return transactionRepo.readTransaction(transactionID);
	}

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

		return "OK";
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

	public String cancelTransaction(String transactionID) {

		boolean isPending;
		Transaction readResponse;
		try {
			readResponse = getTransactionWithTransactionID(transactionID);
			isPending = readResponse.getTransactionStatus().equals("Pending");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while getting transaction";
		}

		if (isPending) {
			return updateTransactionStatus(transactionID, "Cancelled");
		}
		return "Transaction cannot be cancelled";
	}

	public String approveTransaction(String transactionID, String userRole) {
		if (userRole.equals("Admin")) {

			boolean isPending;
			Transaction readResponse;
			try {
				readResponse = getTransactionWithTransactionID(transactionID);
				isPending = readResponse.getTransactionStatus().equals("Pending");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "Error while getting transaction";
			}

			if (isPending) {
				String amount = readResponse.getAmount();
				String deduct = accountService.deductBalance(readResponse.getSendingAccountNumber(), amount);

				if (readResponse.getReceivingRounting().equals(BANK_ROUTING_NUMBER)
						&& !deduct.equals(AccountService.CURRENT_BALANCE_IS_NOT_ENOUGH)) {
					accountService.addBalance(readResponse.getReceivingAccountNumber(), amount);
				} else {
					return AccountService.CURRENT_BALANCE_IS_NOT_ENOUGH;
				}

				return updateTransactionStatus(transactionID, "Completed");
			} else {
				return "Transaction is already cancellled or completed";
			}

		}
		return "Need to be admin";
	}

	public String schedulePayment(String amount, String transactionDate, String description,
			String receivingAccountNumber, String sendingAccountNumber, String receivingRounting, boolean isRecur,
			int repeatTime, long period) {
		String datePattern = "([12]\\d{3}\\/(0[1-9]|1[0-2])\\/(0[1-9]|[12]\\d|3[01]))";
		Pattern pattern = Pattern.compile(datePattern);

		Matcher matcher = pattern.matcher(transactionDate);
		boolean matches = matcher.matches();

		if (!matches) {
			return "Invalid date format! Please enter with format as mm/dd/yyyy";
		}

		String transactionID = generateTransactionID();
		createTransaction(amount, transactionDate, description, receivingAccountNumber, sendingAccountNumber, "Pending",
				transactionID, receivingRounting);

		if (isRecur) {
			for (int i = 0; i < repeatTime; i++) {
				transactionDate = getNextTransactionDate(transactionDate, period);
				transactionID = generateTransactionID();
				createTransaction(amount, transactionDate, description, receivingAccountNumber, sendingAccountNumber,
						"Pending", transactionID, receivingRounting);
			}
		}

		return "SUCCESS: Scheduled payment!!!";
	}

	private String getNextTransactionDate(String transactionDate, long period) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		formatter = formatter.withLocale(Locale.US);
		LocalDate date = LocalDate.parse(transactionDate, formatter);
		date = date.plusDays(period);
		return date.format(formatter);
	}

	public String generateTransactionID() {
		return RandomStringUtils.randomAlphanumeric(16);
	}

}
