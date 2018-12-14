package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.DTO.Auditlog;
import com.muteng.dgjs.dao.AuditlogMapper;
import com.muteng.dgjs.service.AuditlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditlogImple implements AuditlogService {

	@Autowired
	private AuditlogMapper auditlogMapper;

	//插入用户行为数据
	@Override
	public void addlog(Auditlog auditlog) {
		Auditlog a =auditlogMapper.selectauditlog(auditlog.getPagenum(),auditlog.getActionnum());
		auditlog.setAction(a.getAction());
		auditlog.setPage(a.getPage());
		auditlogMapper.insertSelective(auditlog); }

	//插入详细用户动作记录
	@Override
	public void adddetailedlog(Auditlog auditlog,String keyword) {
		Auditlog a =auditlogMapper.selectauditlog(auditlog.getPagenum(),auditlog.getActionnum());
		auditlog.setAction(a.getAction());
		auditlog.setPage(a.getPage());
		auditlog.setContent(a.getPage()+"执行了"+a.getAction()+"条件为"+keyword);
		auditlogMapper.insertSelective(auditlog);
	}

}
