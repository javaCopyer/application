package com.muteng.dgjs.service.impl;


import com.muteng.dgjs.DTO.Relation;
import com.muteng.dgjs.DTO.Share;
import com.muteng.dgjs.dao.ShareMapper;
import com.muteng.dgjs.service.ShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShareServiceImpl implements ShareService {
	@Resource
	private ShareMapper shareMapper;

	@Override
	public Share selectShare(Integer sharetype) {
		return this.shareMapper.selectShare(sharetype);
	}

	@Override
	public void insertrelation(Relation relation) { shareMapper.insertSelective(relation); }

    @Override
    public Relation selectrelation(String openid) {
		return this.shareMapper.selectrelation(openid);
    }
}
