package com.muteng.dgjs.DTO;

import java.util.Date;

import lombok.Data;
@Data
public class OrderuserPaymentvoucherDTO {
    private Long id;
    private Long userid;
    private Long orderid;
    private Long orderlistid;
    private String name;
    private String loginname;
    private String ordertype;
    private String accessusername;
    private String dimission;
    private Date dimissiontime;
    private String sex;
    private Date createtime;
    private Integer processstatus;
    
    private Date thiswayday;
    private String paytype;
	private Integer isrecharge;
	private Integer day;
	private Integer price;
	private String orderusername;
	private String paymentvouchername;
	private String content;
	private String daystatus;
	private String stagename;
	private String property;
	private Integer computeday;
	private String paymentvouchercode;
	private String peopletype;
}
