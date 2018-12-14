package com.muteng.dgjs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.muteng.dgjs.DTO.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.BankCardMapper;
import com.muteng.dgjs.domain.BankCard;
import com.muteng.dgjs.service.BankCardService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankCardServiceImpl implements BankCardService{

	@Autowired
	private BankCardMapper bankCardMapper;
	
	@Override
	public List<BankCard> getBankcardByUserid(Long userid) {
		return  this.bankCardMapper.queryByUserid(userid);
	}

	@Override
	public String updateBackcard(Map<String, Object> map) {
		BankCard bankCard = this.bankCardMapper.queryOne(map);
		if(bankCard != null){
			bankCard.setStatus(0);
			this.bankCardMapper.updateByPrimaryKey(bankCard);
		}else{
			return "无此银行卡";
		}
		
		return "解绑成功";
	}

	@Override
	public void updateBankcardByUserid(Long userid,String cardnum) {
		this.bankCardMapper.updateBankcardByUserid(userid,cardnum);
	}

	@Override
	public void updateOtherBankcardByUserid(Long userid) {
		this.bankCardMapper.updateOtherBankcardByUserid(userid);
	}

	@Override
	public void addBankcardByUserid(Map<String,Object> map) {
		this.bankCardMapper.addBankcardByUserid(map);
	}

	@Override
	public List<Bank> selectbank() {
		return this.bankCardMapper.selectbank();
	}

	@Override
	public List<BankCard> getgetBankcardInfoByUserid(Long userid) {
		return this.bankCardMapper.getgetBankcardInfoByUserid(userid);
	}

	@Override
	public List<Integer> selectBankTel(Object tel) {
		return this.bankCardMapper.selectBankTel(tel);
	}

	@Override
	public List<String> selectBankByFuzzy(String bname) {
//		String id = "%" + bname + "%";
		return this.bankCardMapper.selectBankByFuzzy(bname);
	}

	@Override
	public BankCard selectbankcardBycardnum(String bankCard) {
		return this.bankCardMapper.selectBankBynum(bankCard);
	}

	@Override
	public String getBankcardBybankCard(String bankcard) {
		return this.bankCardMapper.getBankcardBybankCard(bankcard);
	}
}
