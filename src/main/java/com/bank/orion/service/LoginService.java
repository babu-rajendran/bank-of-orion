package com.bank.orion.service;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.bank.orion.model.AccountHolder;
import com.bank.orion.payload.attributes.AttributeJ;
import com.bank.orion.repository.AccountHolderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoginService {

	private AccountHolderRepository acctHolderRepo = new AccountHolderRepository();

	public AccountHolder checkLogin(String userName, String accountPass) {

		String filterExpression = "userName =:a";
		AttributeJ listAttrs = new AttributeJ();
		listAttrs.setA(userName);

		List<AccountHolder> listResponse;
		try {
			listResponse = acctHolderRepo.listAccountHolder(filterExpression, listAttrs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}

		AccountHolder user = listResponse.get(0);
		if (CollectionUtils.isEmpty(listResponse) || !user.getAccountPass().equals(accountPass)) {
			return null;
		}

		return user;
	}

}
