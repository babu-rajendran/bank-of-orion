package com.bank.orion.repository;

import java.util.ArrayList;
import java.util.List;

import com.bank.orion.model.AccountHolder;
import com.bank.orion.payload.accountHolder.CreateAccountHolderRequest;
import com.bank.orion.payload.accountHolder.ListAccountHolderRequest;
import com.bank.orion.payload.accountHolder.ReadAccountHolderRequest;
import com.bank.orion.payload.accountHolder.UpdateAccountHolderRequest;
import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.util.responseTransform.CreateResponseTransform;
import com.bank.orion.util.responseTransform.ListResponseTransform;
import com.bank.orion.util.responseTransform.ReadAndUpdateResponseTransform;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class AccountHolderRepository extends Repository {

	public String createAccountHolder(AccountHolder AccountHolder) throws JsonProcessingException {
		initNewDbRequest();

		CreateAccountHolderRequest creatReq = new CreateAccountHolderRequest(AccountHolder);
		stringPayload = mapper.writeValueAsString(creatReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		return CreateResponseTransform.transformCreateResponse(result);
	}

	public AccountHolder readAccountHolder(String emailID) throws JsonProcessingException {
		initNewDbRequest();

		ReadAccountHolderRequest rdReq = new ReadAccountHolderRequest(emailID);
		stringPayload = mapper.writeValueAsString(rdReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		AccountHolder readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		System.out.println(readResponse);

		return readResponse;
	}

	public List<AccountHolder> listAccountHolder(String filterExpression, ExpressionAttributeValues listAttrs)
			throws JsonProcessingException {
		initNewDbRequest();

		ListAccountHolderRequest lstReq = new ListAccountHolderRequest(filterExpression, listAttrs);
		stringPayload = mapper.writeValueAsString(lstReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);

		List<AccountHolder> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<AccountHolder>>() {
				});
		listResponse.forEach(System.out::println);

		return listResponse;
	}

	public AccountHolder updateAccountHolder(String emailID, String updateExpression,
			ExpressionAttributeValues updateAttrs) throws JsonProcessingException {
		initNewDbRequest();

		UpdateAccountHolderRequest updtReq = new UpdateAccountHolderRequest(emailID, updateExpression,
				updateAttrs);
		stringPayload = mapper.writeValueAsString(updtReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		AccountHolder updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, AccountHolder.class);
		System.out.println(updateResponse);

		return updateResponse;
	}
}
