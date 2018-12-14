package com.muteng.dgjs.vo;

import com.alibaba.fastjson.JSONArray;

import lombok.Data;

@Data
public class BasicUserInfoResponse {
	private String code;
	private String value;
	private JSONArray names;
}
