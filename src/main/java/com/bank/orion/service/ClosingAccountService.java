package com.bank.orion.service;

import java.util.List;

import com.bank.orion.model.Account;
import com.bank.orion.model.AccountHolder;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.AccountHolderRepository;
import com.bank.orion.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ClosingAccountService extends AccountService {

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

	public String closeAccountHolder(String emailID) {

		List<Account> listResponse;
		try {
			listResponse = getAccountsWithEmailID(emailID);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account list response";
		}

		listResponse.forEach(account -> {
			System.out.println(closeAccount(account.getAccountNumber()));
		});

		String updateExpression = "set holderStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Suspend");

		AccountHolder updateResponse;
		try {
			updateResponse = acctHolderRepo.updateAccountHolder(emailID, updateExpression, updateAttrs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account holder response";
		}

		System.out.println(updateResponse);

		if (updateResponse == null) {
			return "Error while updating Account Holder Status";
		}

		return "Success in closing the account holder";
	}
}
