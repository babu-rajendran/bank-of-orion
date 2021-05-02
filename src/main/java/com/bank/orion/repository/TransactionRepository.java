package com.bank.orion.repository;

import java.util.ArrayList;
import java.util.List;

import com.bank.orion.model.Transaction;
import com.bank.orion.payload.attributes.ExpressionAttributeValues;
import com.bank.orion.payload.transaction.CreateTransactionRequest;
import com.bank.orion.payload.transaction.ListTransactionRequest;
import com.bank.orion.payload.transaction.ReadTransactionRequest;
import com.bank.orion.payload.transaction.UpdateTransactionRequest;
import com.bank.orion.util.responseTransform.CreateResponseTransform;
import com.bank.orion.util.responseTransform.ListResponseTransform;
import com.bank.orion.util.responseTransform.ReadAndUpdateResponseTransform;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class TransactionRepository extends Repository {
	
	public String createTransaction(Transaction Transaction) throws JsonProcessingException {
		initNewDbRequest();

		CreateTransactionRequest creatReq = new CreateTransactionRequest(Transaction);
		stringPayload = mapper.writeValueAsString(creatReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		return CreateResponseTransform.transformCreateResponse(result);
	}

	public Transaction readTransaction(String transactionID) throws JsonProcessingException {
		initNewDbRequest();

		ReadTransactionRequest rdReq = new ReadTransactionRequest(transactionID);
		stringPayload = mapper.writeValueAsString(rdReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Transaction readResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Transaction.class);
		System.out.println(readResponse);

		return readResponse;
	}

	public List<Transaction> listTransaction(String filterExpression, ExpressionAttributeValues listAttrs)
			throws JsonProcessingException {
		initNewDbRequest();

		ListTransactionRequest lstReq = new ListTransactionRequest(filterExpression, listAttrs);
		stringPayload = mapper.writeValueAsString(lstReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);

		List<Transaction> listResponse = ListResponseTransform.transformListResponse(result,
				new TypeReference<ArrayList<Transaction>>() {
				});
		listResponse.forEach(System.out::println);

		return listResponse;
	}

	public Transaction updateTransaction(String transactionID, String updateExpression, ExpressionAttributeValues updateAttrs)
			throws JsonProcessingException {
		initNewDbRequest();

		UpdateTransactionRequest updtReq = new UpdateTransactionRequest(transactionID, updateExpression, updateAttrs);
		stringPayload = mapper.writeValueAsString(updtReq);
		System.out.println(stringPayload);

		strReq.setPayload(stringPayload);
		request.setStringRequest(strReq);
		System.out.println(request);
		result = connection.post(request);
		Transaction updateResponse = ReadAndUpdateResponseTransform.transformRUResponse(result, Transaction.class);
		System.out.println(updateResponse);

		return updateResponse;
	}
}
