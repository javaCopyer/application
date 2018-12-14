package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.Account2;
import com.muteng.dgjs.DTO.DzRevenue;
import com.muteng.dgjs.DTO.Paymentvoucher;
import com.muteng.dgjs.domain.Account;
import com.muteng.dgjs.domain.OrdersPaylog;

public interface AccountService {

	List<Account2> getAccounts(Long userid);
	
	int insertAccount(Account account);

    void addpaym(Paymentvoucher pm);

	void addop(OrdersPaylog op);
}
