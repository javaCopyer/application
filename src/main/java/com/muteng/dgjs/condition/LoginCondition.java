package com.muteng.dgjs.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginCondition {
	
	@ApiModelProperty("用户名")
	private String loginname;
	@ApiModelProperty("图片验证码")
	private String pic;
	@ApiModelProperty("邀请码")
	private String invitation;
	@ApiModelProperty("短信验证码")
	private String smsMessage;
	@ApiModelProperty("weixinopenid")
	private String weixinopenid;
	@ApiModelProperty("subsource")
	private String subsource;
	@ApiModelProperty("source")
	private String source;

}
