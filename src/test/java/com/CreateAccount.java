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
		createAccountHolder("1234", "Checking", "hunle@test.com", "011201", "6075", "Active", "john", "1500", "john1",
				"admin");
	}

	private static void createAccountHolder(String accountNum, String accountType, String email, String dob, String SSN,
			String accountStatus, String name, String balance, String userName, String userRole)
			throws JsonProcessingException {

		// Check if AccountHolder exists
		List<AccountHolder> listResponse = listAccountHolder(email, userName);

		// Check if Account Exists
		if (listResponse.isEmpty()) {
			System.out.println("FAILED: Account does not exist");
			AccountHolder readResponse = readAccountHolder(email);
			if (readResponse == null) {
				System.out.println("FAILED: Email does not exist");
			} else {
				createAccount(accountNum, accountStatus, accountType, balance, email);
			}
		} else {
			System.out.println("Account Exists");
			AccountHolder readResponse = readAccountHolder(email);
			if (readResponse == null) {
				//Check all verifying fields
				List<AccountHolder> vertifyUser = vertifyAccountHolder(SSN, dob, name);
				if (vertifyUser.isEmpty()) {
					//No matching user
					System.out.println("FAILED: No matching user is found");
				}else{
					//User exists; update userName and Name
				}
			} else {
				AccountHolder checkUsername = readAccountHolder(userName);
				if(checkUsername == null) {
					createAccount(accountNum, accountStatus, accountType, balance, email);
				}else{
					System.out.println("FAILED: Another Account is already using this username");
				}
			}
		}
	}

	private static void createAccount(String accountNum, String accountStatus, String accountType, String balance,
			String email) throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		// Create checking account
		Account acct = new Account();
		acct.setAccountNumber(accountNum);
		acct.setAccountType(accountType);
		acct.setBalance(balance);
		acct.setEmailID(email);
		acct.setAccountStatus(accountStatus);
		CreateAccountRequest creatAcctReq = new CreateAccountRequest(acct);
		String stringPayload = mapper.writeValueAsString(creatAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request); // Send to DB
		System.out.println(CreateResponseTransform.transformCreateResponse(result));
	}

	private static List<AccountHolder> listAccountHolder(String attribute1, String attribute2)
			throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		String filterExpression = "emailID =:a and userName =:b";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(attribute1);
		listAttrs.setB(attribute2);
		ListAccountHolderRequest lstAcctHolderReq = new ListAccountHolderRequest(filterExpression, listAttrs);
		String stringPayload = mapper.writeValueAsString(lstAcctHolderReq);
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
	
	private static List<AccountHolder> vertifyAccountHolder(String attribute1, String attribute2, String attribute3)
			throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		String filterExpression = "SSN =:a and dob =:b and name =:c";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(attribute1);
		listAttrs.setB(attribute2);
		listAttrs.setC(attribute3);
		ListAccountHolderRequest lstAcctHolderReq = new ListAccountHolderRequest(filterExpression, listAttrs);
		String stringPayload = mapper.writeValueAsString(lstAcctHolderReq);
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
