package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.Bank;
import com.muteng.dgjs.domain.BankCard;

public interface BankCardService {
	
	List<BankCard> getBankcardByUserid(Long userid);
	
	String updateBackcard(Map<String,Object> map);

	void updateOtherBankcardByUserid(Long userid);

	void updateBankcardByUserid(Long userid,String cardnum);

	void addBankcardByUserid(Map<String,Object> map);

    List<Bank> selectbank();

	List<BankCard> getgetBankcardInfoByUserid(Long userid);

	List<Integer> selectBankTel(Object tel);

	List<String> selectBankByFuzzy(String bname);

	BankCard selectbankcardBycardnum(String bankCard);

	String getBankcardBybankCard(String bankcard);
}
