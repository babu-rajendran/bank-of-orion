package com.bank.orion.operation;

import java.util.List;

import com.bank.orion.model.Account;
import com.bank.orion.model.AccountHolder;
import com.bank.orion.model.Transaction;
import com.bank.orion.service.AccountHolderService;
import com.bank.orion.service.AccountService;
import com.bank.orion.service.LoginService;
import com.bank.orion.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class UserOperation {

	protected AccountService accountService = new AccountService();
	protected AccountHolderService accountHolderService = new AccountHolderService();
	protected TransactionService transactionService = new TransactionService();
	protected LoginService loginService = new LoginService();

	public AccountHolder login(String userName, String password) {
		return loginService.checkLogin(userName, password);
	}

	public List<Account> getAccountsWithEmail(String emailID) {
		try {
			return accountService.getAccountsWithEmailID(emailID);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account getAccountWithAcctNum(String acctNum) {
		try {
			return accountService.getAccountWithAcctNum(acctNum);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Transaction> getTransactionsWithAcctNum(String acctNum) {
		try {
			return transactionService.getTransactionsWithAcctNum(acctNum);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Transaction getTransactionWithID(String transactionID) {
		try {
			return transactionService.getTransactionWithTransactionID(transactionID);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String cancelTransaction(String transactionID) {
		return transactionService.cancelTransaction(transactionID);
	}

	public String schedulePayment(String amount, String transactionDate, String description,
			String receivingAccountNumber, String sendingAccountNumber, String receivingRounting, boolean isRecur,
			int repeatTime, long period) {
		return transactionService.schedulePayment(amount, transactionDate, description, receivingAccountNumber,
				sendingAccountNumber, receivingRounting, isRecur, repeatTime, period);
	}

	public String activateUser(String email, String dob, String SSN, String name, String password, String userName) {
		return accountHolderService.activateCustomer(email, dob, SSN, name, password, userName);
	}
}
