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

public class ApiGatewayToDynamoDBDemo {
	private static Mapper mapper = Mapper.getMapper();

	public static void main(String[] args) throws JsonProcessingException {
		// createAccount("123456", "Checking", "hello@sjsu.edu", "Active", "1500");
		System.out.println();
		// createAccountHolder();
		System.out.println();
		// testTransaction();

		createAccountHolder("1234", "Checking", "test1@sjsu.edu", "011201", "6075", "Active", "john", "john1", "admin");
	}

	private static void listAccount() throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		ReadAccountHolderRequest rdAcctReq = new ReadAccountHolderRequest("123@test.com");
		String stringPayload = mapper.writeValueAsString(rdAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		AccountHolder readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		System.out.println(readResponse);
	}

	private static void createAccount(String accountNum, String accountStatus, String accountType, String balance,
			String email) throws JsonProcessingException {

	}

	private static void createAccountHolder(String accountNum, String accountType, String email, String dob, String SSN,
			String accountStatus, String name, String userName, String userRole) throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		// Check if AccountHolder exists
		String filterExpression = "emailID =:a or userName =:b";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA("1321@test.com");
		listAttrs.setB("developer1");
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

		// Check if Account Exists
		if (listResponse.isEmpty()) {
			System.out.println("nothing inside");
		} else {
			System.out.println("something inside");
		}
	}

	private static void testAccount() throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		// Create checking account
		Account acct = new Account();
		acct.setAccountNumber("1812391401");
		acct.setAccountType("Checking");
		acct.setBalance("1500");
		acct.setEmailID("1234@test.com");
		acct.setAccountStatus("Active");
		CreateAccountRequest creatAcctReq = new CreateAccountRequest(acct);
		String stringPayload = mapper.writeValueAsString(creatAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request); // Send to DB
		System.out.println(CreateResponseTransform.transformCreateResponse(result));

		System.out.println();
		System.out.println();

		// Create saving account
		Account acct1 = new Account();
		acct1.setAccountNumber("400013412341236");
		acct1.setAccountType("Saving");
		acct1.setBalance("1500");
		acct1.setEmailID("123@test.com");
		acct1.setAccountStatus("Active");
		CreateAccountRequest creatAcctReq1 = new CreateAccountRequest(acct1);
		stringPayload = mapper.writeValueAsString(creatAcctReq1);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		System.out.println(CreateResponseTransform.transformCreateResponse(result));

		System.out.println();
		System.out.println();

		// Read checking account
		ReadAccountRequest rdAcctReq = new ReadAccountRequest("400013412341235");
		stringPayload = mapper.writeValueAsString(rdAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Account readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Account.class);
		System.out.println(readResponse);

		System.out.println();
		System.out.println();

		// Update checking account's balance
		String updateExpression = "set balance =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("2000");
		UpdateAccountRequest updtAcctReq = new UpdateAccountRequest("400013412341235", updateExpression, updateAttrs);
		stringPayload = mapper.writeValueAsString(updtAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Account updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Account.class);
		System.out.println(updateResponse);

		System.out.println();
		System.out.println();

		String filterExpression = "emailID =:a and accountStatus =:b";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA("123@test.com");
		listAttrs.setB("Active");
		ListAccountRequest lstAcctReq = new ListAccountRequest(filterExpression, listAttrs);
		stringPayload = mapper.writeValueAsString(lstAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);

		List<Account> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<Account>>() {
				});
		listResponse.forEach(System.out::println);

		System.out.println();
		System.out.println();

	}

	private static void testAccountHolder() throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		// Create first account
		AccountHolder acctHolder1 = new AccountHolder();
		acctHolder1.setEmailID("123@test.com");
		acctHolder1.setDob("01/01/2000");
		acctHolder1.setHolderStatus("Active");
		acctHolder1.setLast4SSN("1234");
		acctHolder1.setLegalName("Tester");
		acctHolder1.setUserName("tester01");
		acctHolder1.setUserRole("Admin");
		CreateAccountHolderRequest creatAcctReq1 = new CreateAccountHolderRequest(acctHolder1);
		String stringPayload = mapper.writeValueAsString(creatAcctReq1);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		System.out.println(CreateResponseTransform.transformCreateResponse(result));

		System.out.println();
		System.out.println();

		// Create second account
		AccountHolder acctHolder2 = new AccountHolder();
		acctHolder2.setEmailID("321@test.com");
		acctHolder2.setDob("11/11/2000");
		acctHolder2.setHolderStatus("Active");
		acctHolder2.setLast4SSN("4321");
		acctHolder2.setLegalName("Developer");
		acctHolder2.setUserName("developer1");
		acctHolder2.setUserRole("Admin");
		CreateAccountHolderRequest creatAcctReq2 = new CreateAccountHolderRequest(acctHolder2);
		stringPayload = mapper.writeValueAsString(creatAcctReq2);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		System.out.println(CreateResponseTransform.transformCreateResponse(result));

		System.out.println();
		System.out.println();

		// Read first account
		ReadAccountHolderRequest rdAcctReq = new ReadAccountHolderRequest("123@test.com");
		stringPayload = mapper.writeValueAsString(rdAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		AccountHolder readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		System.out.println(readResponse);

		System.out.println();
		System.out.println();

		// Update first account phone
		String updateExpression = "set phone =:a, userName =:b";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("408-1111-222");
		updateAttrs.setB("Developer2");
		UpdateAccountHolderRequest updtAcctReq = new UpdateAccountHolderRequest("123@test.com", updateExpression,
				updateAttrs);
		stringPayload = mapper.writeValueAsString(updtAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		AccountHolder updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		System.out.println(updateResponse);

		System.out.println();
		System.out.println();

		String filterExpression = "userRole =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA("Admin");
		ListAccountHolderRequest lstAcctReq = new ListAccountHolderRequest(filterExpression, listAttrs);
		stringPayload = mapper.writeValueAsString(lstAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		List<AccountHolder> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<AccountHolder>>() {
				});
		listResponse.forEach(System.out::println);

		System.out.println();
		System.out.println();

	}

	private static void testTransaction() throws JsonProcessingException {
		OrionDBConnection connection = new OrionDBConnection();
		PostDynamodbRequest request = new PostDynamodbRequest();
		StringRequest strReq = new StringRequest();

		Transaction transaction = new Transaction();
		transaction.setTransactionID("0123");
		transaction.setSendingAccountNumber("400013412341235");
		transaction.setReceivingAccountNumber("400013412341234");
		transaction.setAmount("500");
		transaction.setDescription("test Transaction");
		transaction.setTransactionDate("02/02/2020");
		transaction.setTransactionStatus("Pending");
		CreateTransactionRequest creatAcctReq = new CreateTransactionRequest(transaction);
		String stringPayload = mapper.writeValueAsString(creatAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		PostDynamodbResult result = connection.post(request);
		System.out.println(CreateResponseTransform.transformCreateResponse(result));

		ReadTransactionRequest rdAcctReq = new ReadTransactionRequest("0123");
		stringPayload = mapper.writeValueAsString(rdAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Transaction readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Transaction.class);
		System.out.println(readResponse);

		System.out.println();
		System.out.println();

		String updateExpression = "set transactionStatus =:a";
		AttributeJ updateAttrs = new AttributeJ();
		updateAttrs.setA("Completed");
		UpdateTransactionRequest updtAcctReq = new UpdateTransactionRequest("0123", updateExpression, updateAttrs);
		stringPayload = mapper.writeValueAsString(updtAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Transaction updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Transaction.class);
		System.out.println(updateResponse);

		System.out.println();
		System.out.println();

		String filterExpression = "receivingAccountNumber =:a or sendingAccountNumber =:b";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA("400013412341235");
		listAttrs.setB("400013412341235");
		ListTransactionRequest lstAcctReq = new ListTransactionRequest(filterExpression, listAttrs);
		stringPayload = mapper.writeValueAsString(lstAcctReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		List<Transaction> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<Transaction>>() {
				});
		listResponse.forEach(System.out::println);

		System.out.println();
		System.out.println();

	}
}
