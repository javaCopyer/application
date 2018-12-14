package com.muteng.dgjs.service.impl;

import java.util.List;

import com.muteng.dgjs.DTO.Account2;
import com.muteng.dgjs.DTO.Paymentvoucher;
import com.muteng.dgjs.domain.OrdersPaylog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.AccountMapper;
import com.muteng.dgjs.domain.Account;
import com.muteng.dgjs.service.AccountService;

@Service
public class AccountServiceImple implements AccountService{

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public List<Account2> getAccounts(Long userid) {
		return  this.accountMapper.queryAccount(userid);
	}

	@Override
	public int insertAccount(Account account) {
		return accountMapper.insertSelective(account);
	}

	@Override
	public void addpaym(Paymentvoucher pm) {
		accountMapper.addpaym(pm);
	}

	@Override
	public void addop(OrdersPaylog op) {
		accountMapper.addop(op);
	}


}
