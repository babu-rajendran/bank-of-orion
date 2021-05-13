package com.bank.orion.operation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.bank.orion.model.AccountHolder;
import com.bank.orion.model.Transaction;
import com.bank.orion.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AdminOperation extends UserOperation {

	public List<AccountHolder> searchAccountHolder(String key) {
		List<AccountHolder> accountHolders = new ArrayList<>();

		AccountHolder acctHolder = getUserWithEmail(key);
		if (acctHolder != null) {
			accountHolders.add(acctHolder);
			return accountHolders;
		}

		acctHolder = getUserWithUserName(key);
		if (acctHolder != null) {
			accountHolders.add(acctHolder);
			return accountHolders;
		}

		return getUsersWithLegalName(key);
	}

	public List<Transaction> searchTransactions(String key) {
		List<Transaction> transactions = new ArrayList<>();

		Transaction transaction = getTransactionWithID(key);
		if (transaction != null) {
			transactions.add(transaction);
			return transactions;
		}

		return getTransactionsWithAcctNum(key);
	}

	public String suspendUser(String emailID) {
		return accountHolderService.closeAccountHolder(emailID);
	}

	public String reactivateUser(String emailID) {
		return accountHolderService.reActivateAcctHolder(emailID);
	}

	public String suspendAccount(String accountNum) {
		return accountService.closeAccount(accountNum);
	}

	public String reactivateAccount(String acctNum) {
		return accountService.reActivateAccount(acctNum);
	}

	public String deposit(String acctNum, String amount) {
		String addBalance = accountService.addBalance(acctNum, amount);
		if (addBalance.equals("OK")) {
			String transactionDate = getCurrentDate();
			String transactionID = transactionService.generateTransactionID();
			return transactionService.createTransaction(amount, transactionDate, "Deposit", acctNum, "", "Completed",
					transactionID, TransactionService.BANK_ROUTING_NUMBER);
		}
		return addBalance;
	}

	public String deduct(String acctNum, String amount) {
		String deductBalance = accountService.deductBalance(acctNum, amount);
		if (deductBalance.equals("OK")) {
			String transactionDate = getCurrentDate();
			String transactionID = transactionService.generateTransactionID();
			return transactionService.createTransaction(amount, transactionDate, "Withdrawn", "", acctNum, "Completed",
					transactionID, TransactionService.BANK_ROUTING_NUMBER);
		}
		return deductBalance;
	}

	public String approveTransaction(String transactionID) {
		return transactionService.approveTransaction(transactionID, "Admin");
	}

	public String createUser(String accountNum, String accountType, String email, String dob, String SSN, String name,
			String balance, String phone) {
		return accountHolderService.createCustomer(accountNum, accountType, email, dob, SSN, name, balance, phone);
	}

	public AccountHolder getUserWithEmail(String emailID) {
		try {
			return accountHolderService.getAccountHolderWithEmail(emailID);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private AccountHolder getUserWithUserName(String userName) {
		try {
			return accountHolderService.getAccountHolderWithUserName(userName);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private List<AccountHolder> getUsersWithLegalName(String legalName) {
		try {
			return accountHolderService.getAccountHolderWithLegalName(legalName);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		formatter = formatter.withLocale(Locale.US);
		LocalDate date = LocalDate.now();
		return date.format(formatter);
	}

}
