package com.bank.orion.service;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bank.orion.model.AccountHolder;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.AccountHolderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoginService {

	private AccountHolderRepository acctHolderRepo = new AccountHolderRepository();

	public boolean checkLogin(String userName, String accountPass) {

		String filterExpression = "userName =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(userName);

		List<AccountHolder> listResponse;
		try {
			listResponse = acctHolderRepo.listAccountHolder(filterExpression, listAttrs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return false;
		}

		if (CollectionUtils.isEmpty(listResponse) || !listResponse.get(0).getAccountPass().equals(accountPass)) {
			return false;
		}

		return true;
	}

}
