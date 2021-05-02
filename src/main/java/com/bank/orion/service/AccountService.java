package com.bank.orion.service;

import java.util.List;

import com.bank.orion.model.Account;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AccountService {

	public static final String CURRENT_BALANCE_IS_NOT_ENOUGH = "Current balance is not enough!";
	public static final String ACCOUNT_NUMBER_DOES_NOT_EXIST = "Account number does not exist";
	private AccountRepository acctRepo = new AccountRepository();

	public Account getAccountWithAcctNum(String acctNum) throws JsonProcessingException {
		return acctRepo.readAccount(acctNum);
	}

	public List<Account> getAccountsWithEmailID(String emailID) throws JsonProcessingException {
		String filterExpression = "emailID =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(emailID);

		List<Account> listResponse = acctRepo.listAccount(filterExpression, listAttrs);
		listResponse.forEach(System.out::println);
		return listResponse;
	}

	public String addBalance(String accountNumber, String amount) {

		Account readResponse;
		try {
			readResponse = acctRepo.readAccount(accountNumber);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account";
		}

		if (readResponse != null) {
			Double currentBalance = Double.valueOf(readResponse.getBalance());
			Double newBalance = currentBalance + Double.valueOf(amount);

			String updateExpression = "set balance =:a";
			AttributeJ updateAttrs = new AttributeJ();
			updateAttrs.setA(newBalance.toString());

			Account updateResponse;
			try {
				updateResponse = acctRepo.updateAccount(accountNumber, updateExpression, updateAttrs);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "Error while updating account balance";
			}

			if (updateResponse == null) {
				return "Error while updating account balance";
			}

			return "Success in updating account balance";
		}

		return ACCOUNT_NUMBER_DOES_NOT_EXIST;

	}

	public String deductBalance(String accountNumber, String amount) {

		Account readResponse;
		try {
			readResponse = acctRepo.readAccount(accountNumber);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account";
		}

		if (readResponse != null) {
			Double currentBalance = Double.valueOf(readResponse.getBalance());
			Double newBalance = currentBalance - Double.valueOf(amount);

			if (newBalance < 0) {
				return CURRENT_BALANCE_IS_NOT_ENOUGH;
			}

			String updateExpression = "set balance =:a";
			AttributeJ updateAttrs = new AttributeJ();
			updateAttrs.setA(newBalance.toString());

			Account updateResponse;
			try {
				updateResponse = acctRepo.updateAccount(accountNumber, updateExpression, updateAttrs);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "Error while updating account balance";
			}

			if (updateResponse == null) {
				return "Error while updating account balance";
			}

			return "Success in updating account balance";
		}

		return ACCOUNT_NUMBER_DOES_NOT_EXIST;
	}

	public String closeAccount(String accountNumber) {

		String updateExpression = "set accountStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Suspend");

		Account updateResponse;
		try {
			updateResponse = acctRepo.updateAccount(accountNumber, updateExpression, updateAttrs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while updating Account Status";
		}

		if (updateResponse == null) {
			return "Error while updating Account Status";
		}

		return "Success in closing the account";
	}

	public void createAccount(String accountNum, String accountType, String balance, String email)
			throws JsonProcessingException {
		Account acct = new Account();
		acct.setAccountNumber(accountNum);
		acct.setAccountType(accountType);
		acct.setBalance(balance);
		acct.setEmailID(email);
		acct.setAccountStatus("Inactive");
		acctRepo.createAccount(acct);
	}

	public void activateAcct(String accountNum) throws JsonProcessingException {
		String updateExpression = "set accountStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Active");
		acctRepo.updateAccount(accountNum, updateExpression, updateAttrs);
	}
	
	public String reActivateAccount(String accountNumber) {

		String updateExpression = "set accountStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Active");

		Account updateResponse;
		try {
			updateResponse = acctRepo.updateAccount(accountNumber, updateExpression, updateAttrs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while updating Account Status";
		}

		if (updateResponse == null) {
			return "Error while updating Account Status";
		}

		return "Success in reactivate the account";
	}
}
