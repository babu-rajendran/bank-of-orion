package com.bank.orion.repository;

import java.util.ArrayList;
import java.util.List;

import com.bank.orion.model.Account;
import com.bank.orion.payload.account.CreateAccountRequest;
import com.bank.orion.payload.account.ListAccountRequest;
import com.bank.orion.payload.account.ReadAccountRequest;
import com.bank.orion.payload.account.UpdateAccountRequest;
import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.util.responseTransform.CreateResponseTransform;
import com.bank.orion.util.responseTransform.ListResponseTransform;
import com.bank.orion.util.responseTransform.ReadAndUpdateResponseTransform;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class AccountRepository extends Repository {

	public String createAccount(Account account) throws JsonProcessingException {
		initNewDbRequest();

		CreateAccountRequest creatReq = new CreateAccountRequest(account);
		stringPayload = mapper.writeValueAsString(creatReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		return CreateResponseTransform.transformCreateResponse(result);
	}

	public Account readAccount(String accountNumber) throws JsonProcessingException {
		initNewDbRequest();

		ReadAccountRequest rdReq = new ReadAccountRequest(accountNumber);
		stringPayload = mapper.writeValueAsString(rdReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Account readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Account.class);
		System.out.println(readResponse);

		return readResponse;
	}

	public List<Account> listAccount(String filterExpression, ExpressionAttributeValues listAttrs)
			throws JsonProcessingException {
		initNewDbRequest();

		ListAccountRequest lstReq = new ListAccountRequest(filterExpression, listAttrs);
		stringPayload = mapper.writeValueAsString(lstReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);

		List<Account> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<Account>>() {
				});
		listResponse.forEach(System.out::println);

		return listResponse;
	}

	public Account updateAccount(String accountNumber, String updateExpression, ExpressionAttributeValues updateAttrs)
			throws JsonProcessingException {
		initNewDbRequest();

		UpdateAccountRequest updtReq = new UpdateAccountRequest(accountNumber, updateExpression, updateAttrs);
		stringPayload = mapper.writeValueAsString(updtReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Account updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Account.class);
		System.out.println(updateResponse);

		return updateResponse;
	}
}
