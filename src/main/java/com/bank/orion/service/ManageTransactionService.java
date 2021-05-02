package com.bank.orion.service;

import com.bank.orion.model.Transaction;
import com.bank.orion.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ManageTransactionService {

	public static final String BANK_ROUNTING_NUMBER = "12345";
	protected TransactionService transactionService = new TransactionService();
	protected TransactionRepository transactionRepo = new TransactionRepository();
	protected AccountService accountService = new AccountService();

	public String cancelTransaction(String transactionID) {

		boolean isPending;
		Transaction readResponse;
		try {
			readResponse = transactionRepo.readTransaction(transactionID);
			isPending = readResponse.getTransactionStatus().equals("Pending");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while getting transaction";
		}

		if (isPending) {
			return transactionService.updateTransactionStatus(transactionID, "Cancelled");
		}
		return "Transaction cannot be cancelled";
	}

	public String approveTransaction(String transactionID, String userRole) {
		if (userRole.equals("Admin")) {

			boolean isPending;
			Transaction readResponse;
			try {
				readResponse = transactionRepo.readTransaction(transactionID);
				isPending = readResponse.getTransactionStatus().equals("Pending");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "Error while getting transaction";
			}

			if (isPending) {
				String amount = readResponse.getAmount();
				String deduct = accountService.deductBalance(readResponse.getSendingAccountNumber(), amount);

				if (readResponse.getReceivingRounting().equals(BANK_ROUNTING_NUMBER)
						&& !deduct.equals(AccountService.CURRENT_BALANCE_IS_NOT_ENOUGH)) {
					accountService.addBalance(readResponse.getReceivingAccountNumber(), amount);
				} else {
					return AccountService.CURRENT_BALANCE_IS_NOT_ENOUGH;
				}

				return transactionService.updateTransactionStatus(transactionID, "Completed");
			} else {
				return "Transaction is already cancellled or completed";
			}

		}
		return "Need to be admin";
	}
}
