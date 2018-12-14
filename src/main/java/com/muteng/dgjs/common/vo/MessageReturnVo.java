package com.muteng.dgjs.common.vo;

import lombok.Data;

@Data
public class MessageReturnVo {

	private String taskId;//任务ID
	private Integer overage;//当前余额
	private Integer mobileCount;//成功的号码数量
	private Integer status;//发送的状态代码,0表示成功，其它为失败
	private String desc;//发送说明

}
