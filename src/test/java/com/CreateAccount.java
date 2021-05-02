package com;

import java.util.ArrayList;
import java.util.List;

import com.bank.orion.model.Account;
import com.bank.orion.model.AccountHolder;
import com.bank.orion.model.Transaction;
import com.bank.orion.payload.account.CreateAccountRequest;
import com.bank.orion.payload.account.ListAccountRequest;
import com.bank.orion.payload.account.ReadAccountRequest;
import com.bank.orion.payload.account.UpdateAccountRequest;
import com.bank.orion.payload.accountHolder.CreateAccountHolderRequest;
import com.bank.orion.payload.accountHolder.ListAccountHolderRequest;
import com.bank.orion.payload.accountHolder.ReadAccountHolderRequest;
import com.bank.orion.payload.accountHolder.UpdateAccountHolderRequest;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.payload.transaction.CreateTransactionRequest;
import com.bank.orion.payload.transaction.ListTransactionRequest;
import com.bank.orion.payload.transaction.ReadTransactionRequest;
import com.bank.orion.payload.transaction.UpdateTransactionRequest;
import com.bank.orion.util.Mapper;
import com.bank.orion.util.OrionDBConnection;
import com.bank.orion.util.responseTransform.CreateResponseTransform;
import com.bank.orion.util.responseTransform.ListResponseTransform;
import com.bank.orion.util.responseTransform.ReadAndUpdateResponseTransform;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import aws.apigateway.db.model.PostDynamodbRequest;
import aws.apigateway.db.model.PostDynamodbResult;
import aws.apigateway.db.model.StringRequest;

public class CreateAccount {
	private static Mapper mapper = Mapper.getMapper();

	public static void main(String[] args) throws JsonProcessingException {
		//createCustomer("917498187304", "Saving", "1234@sjsu.edu", "091211", "8726", "David", "2000");
		//System.out.println(activateCustomer("1234@sjsu.edu", "091211", "8726", "David", "adsfa", "John2"));
	}

	public static void createCustomer(String accountNum, String accountType, String email, String dob, String SSN, String name, String balance) throws JsonProcessingException {
		AccountHolder readResponse = readAccountHolder(email);
		if (readResponse == null) {
			System.out.println("Create new accountholder and account");
			createAccountHolder(email, dob, SSN, name);
			createAccount(accountNum, accountType, balance, email);
		} else {
			createAccount(accountNum, accountType, balance, email);
		}
	}

	public static String activateCustomer(String email, String dob, String SSN, String name, String password,
			String userName) throws JsonProcessingException {
		AccountHolder readResponse = readAccountHolder(email);
		if (readResponse == null) {
			return "FAILED: No accountholder found";
		} else {
			String accountHolderStatus = readResponse.getHolderStatus();
			if(accountHolderStatus.equals("Inactive")) {
				List<AccountHolder> checkUsername = verifyUserName(userName);
				if (!checkUsername.isEmpty()) {
					return "FAILED: Username is not available";
				}
				if (readResponse.getDob().equals(dob) && readResponse.getLast4SSN().equals(SSN)
						&& readResponse.getLegalName().equals(name)) {
					updateAccountHolder(password, userName, email);
					List<Account> singleAccount = getAccountsWithEmailID(email);
					for (Account account : singleAccount) {
						updateAccount(account.getAccountNumber());
					}
					return "Account has been updated";
				} else {
					return "FAILED: Verification does not match";
				}
			}else {
				return "FAILED: The account exists and is active or suspended";
			}
		}
	}

	private static void createAccountHolder(String email, String dob, String SSN, String name)
			throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		AccountHolder acctHolder2 = new AccountHolder();
		acctHolder2.setEmailID(email);
		acctHolder2.setDob(dob);
		acctHolder2.setHolderStatus("Inactive");
		acctHolder2.setLast4SSN(SSN);
		acctHolder2.setLegalName(name);
		acctHolder2.setUserRole("User");
		CreateAccountHolderRequest creatAcctReq2 = new CreateAccountHolderRequest(acctHolder2);
		String stringPayload = mapper.writeValueAsString(creatAcctReq2);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		System.out.println(CreateResponseTransform.transformCreateResponse(result));
	}

	private static void createAccount(String accountNum, String accountType, String balance, String email)
			throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		// Create checking account
		Account acct = new Account();
		acct.setAccountNumber(accountNum);
		acct.setAccountType(accountType);
		acct.setBalance(balance);
		acct.setEmailID(email);
		acct.setAccountStatus("Inactive");
		CreateAccountRequest creatAcctReq = new CreateAccountRequest(acct);
		String stringPayload = mapper.writeValueAsString(creatAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request); // Send to DB
		System.out.println(CreateResponseTransform.transformCreateResponse(result));
	}

	public static List<AccountHolder> verifyUserName(String userName) throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		String filterExpression = "userName =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(userName);

		ListAccountHolderRequest lstAcctReq = new ListAccountHolderRequest(filterExpression, listAttrs);
		String stringPayload = mapper.writeValueAsString(lstAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);

		List<AccountHolder> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<AccountHolder>>() {
				});
		listResponse.forEach(System.out::println);
		return listResponse;
	}

	public static List<Account> getAccountsWithEmailID(String emailID) throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		String filterExpression = "emailID =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(emailID);

		ListAccountRequest lstAcctReq = new ListAccountRequest(filterExpression, listAttrs);
		String stringPayload = mapper.writeValueAsString(lstAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);

		List<Account> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<Account>>() {
				});
		listResponse.forEach(System.out::println);
		return listResponse;
	}

	private static void updateAccountHolder(String attribute1, String attribute2, String email)
			throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		String updateExpression = "set accountPass =:a, userName =:b, holderStatus =:c";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA(attribute1);
		updateAttrs.setB(attribute2);
		updateAttrs.setC("Active");
		UpdateAccountHolderRequest updtAcctReq = new UpdateAccountHolderRequest(email, updateExpression, updateAttrs);
		String stringPayload = mapper.writeValueAsString(updtAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		AccountHolder updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		System.out.println(updateResponse);
	}

	private static void updateAccount(String accountNum) throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		String updateExpression = "set accountStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Active");
		UpdateAccountRequest updtAcctReq = new UpdateAccountRequest(accountNum, updateExpression, updateAttrs);
		String stringPayload = mapper.writeValueAsString(updtAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		Account updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Account.class);
		System.out.println(updateResponse);
	}

	private static AccountHolder readAccountHolder(String data) throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		ReadAccountHolderRequest rdAcctReq = new ReadAccountHolderRequest(data);
		String stringPayload = mapper.writeValueAsString(rdAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		AccountHolder readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		return readResponse;
	}
}
