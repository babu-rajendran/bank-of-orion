package com.bank.orion.service;

import java.util.List;

import com.bank.orion.model.Account;
import com.bank.orion.model.AccountHolder;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.AccountHolderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AccountHolderService {

	private AccountHolderRepository acctHolderRepo = new AccountHolderRepository();
	private AccountService acctService = new AccountService();

	public AccountHolder getAccountHolderWithEmail(String email) throws JsonProcessingException {
		return acctHolderRepo.readAccountHolder(email);
	}

	public AccountHolder getAccountHolderWithUserName(String userName) throws JsonProcessingException {

		String filterExpression = "userName =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(userName);
		List<AccountHolder> lstResponse = acctHolderRepo.listAccountHolder(filterExpression, listAttrs);

		if (!lstResponse.isEmpty()) {
			return lstResponse.get(0);
		}

		return null;
	}

	public List<AccountHolder> getAccountHolderWithLegalName(String legalName) throws JsonProcessingException {

		String filterExpression = "legalName =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(legalName);
		return acctHolderRepo.listAccountHolder(filterExpression, listAttrs);
	}

	public String closeAccountHolder(String emailID) {

		List<Account> listResponse;
		try {
			listResponse = acctService.getAccountsWithEmailID(emailID);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account list response";
		}

		listResponse.forEach(account -> {
			System.out.println(acctService.closeAccount(account.getAccountNumber()));
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

	public String createCustomer(String accountNum, String accountType, String email, String dob, String SSN,
			String name, String balance) {
		AccountHolder readResponse;
		try {
			readResponse = acctHolderRepo.readAccountHolder(email);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account holder";
		}
		if (readResponse == null) {
			System.out.println("Create new accountholder and account");

			try {
				createAccountHolder(email, dob, SSN, name);
				if (acctService.getAccountWithAcctNum(accountNum) == null) {
					acctService.createAccount(accountNum, accountType, balance, email);
				} else {
					return "FAILED: Account number is already exist.";
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "Error while creating customer";
			}
			return "OK";
		} else {
			try {
				if (acctService.getAccountWithAcctNum(accountNum) == null) {
					acctService.createAccount(accountNum, accountType, balance, email);
				} else {
					return "FAILED: Account number is already exist.";
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "Error while creating account";
			}
			return "OK";
		}
	}

	public String activateCustomer(String email, String dob, String SSN, String name, String password,
			String userName) {
		AccountHolder readResponse;
		try {
			readResponse = acctHolderRepo.readAccountHolder(email);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account holder";
		}

		if (readResponse == null) {
			return "FAILED: No accountholder found";
		} else {
			String accountHolderStatus = readResponse.getHolderStatus();
			if (accountHolderStatus.equals("Inactive")) {
				List<AccountHolder> checkUsername;
				try {
					checkUsername = verifyUserName(userName);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					return "Error while activate customer";
				}

				if (!checkUsername.isEmpty()) {
					return "FAILED: Username is not available";
				}
				if (readResponse.getDob().equals(dob) && readResponse.getLast4SSN().equals(SSN)
						&& readResponse.getLegalName().equals(name)) {
					try {
						activateAcctHolder(password, userName, email);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
						return "Error while activate account holder";
					}
					List<Account> accounts;
					try {
						accounts = acctService.getAccountsWithEmailID(email);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
						return "Error while getting account list";
					}
					for (Account account : accounts) {
						try {
							acctService.activateAcct(account.getAccountNumber());
						} catch (JsonProcessingException e) {
							e.printStackTrace();
							return "Error while activate acount";
						}
					}
					return "Account has been updated";
				} else {
					return "FAILED: Verification does not match";
				}
			} else {
				return "FAILED: The account exists and is active or suspended";
			}
		}
	}

	private void createAccountHolder(String email, String dob, String SSN, String name) throws JsonProcessingException {

		AccountHolder acctHolder = new AccountHolder();
		acctHolder.setEmailID(email);
		acctHolder.setDob(dob);
		acctHolder.setHolderStatus("Inactive");
		acctHolder.setLast4SSN(SSN);
		acctHolder.setLegalName(name);
		acctHolder.setUserRole("User");
		acctHolderRepo.createAccountHolder(acctHolder);
	}

	private List<AccountHolder> verifyUserName(String userName) throws JsonProcessingException {
		String filterExpression = "userName =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(userName);

		return acctHolderRepo.listAccountHolder(filterExpression, listAttrs);
	}

	private void activateAcctHolder(String accountPass, String userName, String email) throws JsonProcessingException {
		String updateExpression = "set accountPass =:a, userName =:b, holderStatus =:c";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA(accountPass);
		updateAttrs.setB(userName);
		updateAttrs.setC("Active");
		acctHolderRepo.updateAccountHolder(email, updateExpression, updateAttrs);
	}

	public String reActivateAcctHolder(String emailID) {
		List<Account> listResponse;
		try {
			listResponse = acctService.getAccountsWithEmailID(emailID);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Error while reading account list response";
		}

		listResponse.forEach(account -> {
			System.out.println(acctService.reActivateAccount(account.getAccountNumber()));
		});

		String updateExpression = "set holderStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Active");

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

		return "Success in reactivate the account holder";
	}
}
