package com.muteng.dgjs.service;

import java.util.Map;

import com.muteng.dgjs.condition.LoginCondition;

public interface Loginservice {

	Object[] doLogin(LoginCondition condition,Map<String,Object> paramMap);
	Object[] isBandingWeixin(Map<String,Object> paramMap);
	Object[] doMessageLogin(LoginCondition condition,Map<String,Object> paramMap);

}
