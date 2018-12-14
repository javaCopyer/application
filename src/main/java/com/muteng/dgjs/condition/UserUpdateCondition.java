package com.muteng.dgjs.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserUpdateCondition {

	@ApiModelProperty("用户ID")
	private Long id;
	@ApiModelProperty("姓名")
	private String name;
	@ApiModelProperty("昵称")
	private String nick;
//	//没用到性别
//	@ApiModelProperty("性别")
//	private Integer sex;
	@ApiModelProperty("学历")
	private String education;
	@ApiModelProperty("工作")
	private String job;
	@ApiModelProperty("电话号码")
	private String phone;
	@ApiModelProperty("民族")
	private String minzu;
	@ApiModelProperty("地址")
	private String address;
	
}
