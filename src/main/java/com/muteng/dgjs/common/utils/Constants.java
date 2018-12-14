package com.muteng.dgjs.common.utils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class Constants {
	
	

	private Constants() {
	};
	
	// 公共数据信息 001(邀请活动返现金额)
	public static final String INVITEDCODE = "001";
	// 邀请注册金额
	public static final String REGISTER = "002";
	// 三人组团
	public static final String GROUPCODE1 = "003";
	// 六人组团
	public static final String GROUPCODE2 = "004";
	// 九人组团
	public static final String GROUPCODE3 = "005";

	public static final String CODETYPE = "邀请";
	// 普通咨询模板
	public static final String CONSULT1 = "006";
	// 订单咨询模板
	public static final String CONSULT2 = "007";

	// 三人以下组团
	public static final String GROUPCODE0 = "008";

	public static final String TEMPDIR = "upload/temp/";
	public static final String UPLOAD = "upload/";
	public static final String SESSION_USER = "SESSION_USER";
	public static final String ROLE = "ROLE";
	public static final String SESSION_MOBILE_USER = "SESSION_MOBILE_USER";
	public static final String SESSION_MOBILE_APP_CAPTCHA = "SESSION_MOBILE_APP_CAPTCHA";
	public static final String SESSION_MOBILE_APP_CAPTCHA_TIME = "SESSION_MOBILE_APP_CAPTCHA_TIME";
	public static final int SESSION_TIMEOUT = 60 * 60;

	public static final String[] sexdesc = { "未知", "男", "女" };
	public static final String[] typedesc = { "", "全职", "兼职" };

	// 1 注册送， 2 邀请好友注册送，3 签到送，4 抢红包，5 邀请报名进厂，6 邀请报名培训 7 每日理财，8提醒，9注册填好友邀请码
	public static final int[] account_reason = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	public static final String[] account_comment = { "", "注册成功赠送理财基金", "邀请@注册成功", "签到成功", "抢红包", "@报名进厂成功", "@报名培训成功",
			"每日理财收益", "提现-到@", "注册填好友邀请码", "邀请@安装成功", "报销", "生活补贴", "介绍费", "邀请费", "组团", "体检", "代理费" };
	public static final int[] account_type = { 0, 1, 2, 3 };// 1 不可提现金额 2 可提现金额
															// 3 支出 0 取消
	public static final int[] account_status = { 0, 1, 2, 3 };// 1 申请处理 2处理中 3
																// 处理成功 4 处理失败
	public static final String[] account_status_desc = { "", "申请处理", "处理中", "处理成功", "处理失败" };// 1申请处理
	// 3
	// 处理成功
	// 4
	// 处理失败
	public static final int[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

	public static final String[] real_number = {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }; 
	
	public static final String[] job_requirements_name = {"学历","少数民族","英文汉字","简单算术","纹身烟疤" }; 

	public static final Map<String, Object> proCodeAndIdMap = new HashMap<String, Object>();

	static {
		proCodeAndIdMap.put("9115308755048", 190L);// 达方
		proCodeAndIdMap.put("9031938920424", 180L);// 仁宝
		proCodeAndIdMap.put("9111303735256", 186L);// 世硕
		proCodeAndIdMap.put("9115312265024", 179L);// 9115312265024,佳世达
		proCodeAndIdMap.put("4851866404253888", 179L);// 4851866404253888,佳世达
		proCodeAndIdMap.put("9199645719776", 187L);// 9199645719776,纬视晶
		proCodeAndIdMap.put("9256019659320", 194L);// 9256019659320,龙飞
		proCodeAndIdMap.put("9115316005392", 191L);// 9115316005392,金像电子
		proCodeAndIdMap.put("9031940689952", 188L);// 9031940689952,瑞仪光电
		proCodeAndIdMap.put("9107869952104", 195L);// 9107869952104,达富电脑
		proCodeAndIdMap.put("9287014462696", 182L);// 9287014462696,固锝电子
		proCodeAndIdMap.put("9110455328944", 192L);// 9110455328944,中达
		proCodeAndIdMap.put("9325561646832", 184L);// 9325561646832,安特
		proCodeAndIdMap.put("9111301218600", 185L);// 9111301218600,紫翔电子
		proCodeAndIdMap.put("9226839011424", 180L);// 9226839011424,仁宝
		proCodeAndIdMap.put("9226852181416", 178L);// 9226852181416,华硕
		proCodeAndIdMap.put("9110472555680", 193L);// 9110472555680,大智资讯
		proCodeAndIdMap.put("9258074947672", 189L);// 9258074947672,富士康
		proCodeAndIdMap.put("9006271542712", 183L);// 9006271542712,璨宇光学
		proCodeAndIdMap.put("4851873160339584", 181L);// 4851873160339584,好孩子
		proCodeAndIdMap.put("9183920493712", 279L);// 9183920493712,沪士
		proCodeAndIdMap.put("9183959135888", 282L);// 9183959135888,富港电子
		proCodeAndIdMap.put("9180984676592", 284L);// 9180984676592,日本坚田
		proCodeAndIdMap.put("9193603628464", 283L);// 9193603628464,日本电波
		proCodeAndIdMap.put("9115305299128", 285L);// 9115305299128,乐轩科技
		proCodeAndIdMap.put("9184446842152", 286L);// 9184446842152,美昌
		proCodeAndIdMap.put("9184465802072", 287L);// 9184465802072,四海

		proCodeAndIdMap.put("4867785724522624", 231L);// 德邦
		proCodeAndIdMap.put("4828873962369152", 97L);// 美团外卖
		proCodeAndIdMap.put("4851156519436352", 54L);// 华宝
		proCodeAndIdMap.put("4791381596749952", 52L);// 英华达
		proCodeAndIdMap.put("4851178973864128", 46L);// 熊猫
		proCodeAndIdMap.put("4791379239981184", 106L);// LG化学
		proCodeAndIdMap.put("4791367705505920", 174L);// 夏普
		proCodeAndIdMap.put("4791385501769792", 200L);// 邦奇

	}

	public static Map<String, Object> proMap = new HashMap<String, Object>();
	static {
		proMap.put("阜阳+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("阜阳+客服", new String[] { "212", "243", "114" });
		proMap.put("阜阳+司机", new String[] { "242", "262" });
		proMap.put("阜阳+保安", new String[] { "171", "169" });
		proMap.put("阜阳+快递员", new String[] { "226", "172", "245" });
		proMap.put("阜阳+分拣员", new String[] { "71", "155", "156" });
		proMap.put("阜阳+送餐员", new String[] { "97", "170" });
		proMap.put("阜阳+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("南阳+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("南阳+客服", new String[] { "212", "243", "114" });
		proMap.put("南阳+司机", new String[] { "242", "262" });
		proMap.put("南阳+保安", new String[] { "171", "169" });
		proMap.put("南阳+快递员", new String[] { "226", "172", "245" });
		proMap.put("南阳+分拣员", new String[] { "71", "155", "156" });
		proMap.put("南阳+送餐员", new String[] { "97", "170" });
		proMap.put("南阳+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("洛阳+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("洛阳+客服", new String[] { "212", "243", "114" });
		proMap.put("洛阳+司机", new String[] { "242", "262" });
		proMap.put("洛阳+保安", new String[] { "171", "169" });
		proMap.put("洛阳+快递员", new String[] { "226", "172", "245" });
		proMap.put("洛阳+分拣员", new String[] { "71", "155", "156" });
		proMap.put("洛阳+送餐员", new String[] { "97", "170" });
		proMap.put("洛阳+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("宿州+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("宿州+客服", new String[] { "212", "243", "114" });
		proMap.put("宿州+司机", new String[] { "242", "262" });
		proMap.put("宿州+保安", new String[] { "171", "169" });
		proMap.put("宿州+快递员", new String[] { "226", "172", "245" });
		proMap.put("宿州+分拣员", new String[] { "71", "155", "156" });
		proMap.put("宿州+送餐员", new String[] { "97", "170" });
		proMap.put("宿州+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("马鞍山+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("马鞍山+客服", new String[] { "212", "243", "114" });
		proMap.put("马鞍山+司机", new String[] { "242", "262" });
		proMap.put("马鞍山+保安", new String[] { "171", "169" });
		proMap.put("马鞍山+快递员", new String[] { "226", "172", "245" });
		proMap.put("马鞍山+分拣员", new String[] { "71", "155", "156" });
		proMap.put("马鞍山+送餐员", new String[] { "97", "170" });
		proMap.put("马鞍山+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("淮北+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("淮北+客服", new String[] { "212", "243", "114" });
		proMap.put("淮北+司机", new String[] { "242", "262" });
		proMap.put("淮北+保安", new String[] { "171", "169" });
		proMap.put("淮北+快递员", new String[] { "226", "172", "245" });
		proMap.put("淮北+分拣员", new String[] { "71", "155", "156" });
		proMap.put("淮北+送餐员", new String[] { "97", "170" });
		proMap.put("淮北+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("安庆+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("安庆+客服", new String[] { "212", "243", "114" });
		proMap.put("安庆+司机", new String[] { "242", "262" });
		proMap.put("安庆+保安", new String[] { "171", "169" });
		proMap.put("安庆+快递员", new String[] { "226", "172", "245" });
		proMap.put("安庆+分拣员", new String[] { "71", "155", "156" });
		proMap.put("安庆+送餐员", new String[] { "97", "170" });
		proMap.put("安庆+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("新乡+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("新乡+客服", new String[] { "212", "243", "114" });
		proMap.put("新乡+司机", new String[] { "242", "262" });
		proMap.put("新乡+保安", new String[] { "171", "169" });
		proMap.put("新乡+快递员", new String[] { "226", "172", "245" });
		proMap.put("新乡+分拣员", new String[] { "71", "155", "156" });
		proMap.put("新乡+送餐员", new String[] { "97", "170" });
		proMap.put("新乡+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("南京+普工", new String[] { "52", "54", "46", "106" });
		proMap.put("南京+司机", new String[] { "242", "262", "263", "265" });
		proMap.put("南京+送餐员", new String[] { "97", "170", "254", "250" });
		proMap.put("南京+客服", new String[] { "212", "243", "244", "246" });

		proMap.put("苏州+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("苏州+客服", new String[] { "212", "243", "114" });
		proMap.put("苏州+司机", new String[] { "242", "262" });
		proMap.put("苏州+保安", new String[] { "171", "169" });
		proMap.put("苏州+快递员", new String[] { "226", "172", "245" });
		proMap.put("苏州+分拣员", new String[] { "71", "155", "156" });
		proMap.put("苏州+送餐员", new String[] { "97", "170" });
		proMap.put("苏州+服务员", new String[] { "124", "291", "274", "273" });

		proMap.put("淮安+热门", new String[] { "52", "54", "46", "106", "178", "179", "188", "122", "201", "105" });
		proMap.put("淮安+客服", new String[] { "212", "243", "114" });
		proMap.put("淮安+司机", new String[] { "242", "262" });
		proMap.put("淮安+保安", new String[] { "171", "169" });
		proMap.put("淮安+快递员", new String[] { "226", "172", "245" });
		proMap.put("淮安+分拣员", new String[] { "71", "155", "156" });
		proMap.put("淮安+送餐员", new String[] { "97", "170" });
		proMap.put("淮安+服务员", new String[] { "124", "291", "274", "273" });

	}
	
	//获取权限信息
	public static List<Map<String, Object>> permissionList;
	
	// 1 pos机 2 支付宝，3 微信，4 现金，5 网银，
	public static final String[] paytype = { "POS机", "支付宝", "微信", "现金", "网银" };

	public static final String[] percentagepaytype = { "", "一次性", "月提成", "季提成" };
	/////////// 短信//////////////////
	public static final String posturl = "http://sms.chanzor.com:8001/sms.aspx";
	public static final String account = "lanzhipei-tz";
	public static final String password = "663070";
	///////////////////////////////

	// public static String wowifi_url = "http://112.84.178.83:9090";
	public static final String wowifi_url = "http://112.84.178.88:9292";

	public static final String wowifi_url_gettoken = wowifi_url + "/thdinf/getToken";
	public static final String wowifi_url_getuser = wowifi_url + "/thdinf/qryUser";
	public static final String wowifi_url_pay = wowifi_url + "/thdinf/alipay";
	public static final String wowifi_parnterId = "1";
	public static final String wowifi_parnterCode = "LZP";
	public static final String wowifi_parnterSecret = "7324DE11756F209FC2404D6FDEB969ED";
	public static int wowifi_timer = 0;
	public static String wowifi_token = null;
	public static final String wowifi_backurl = "http://172.20.10.3/wowifinotify";
	public static final Map<String, Object> wowifi_error_code = new HashMap<String, Object>();

	public static final String aliIDcardhost = "https://dm-51.data.aliyun.com";
	public static final String aliIDcardpath = "/rest/160601/ocr/ocr_idcard.json";
	public static final String aliIDcardmethod = "POST";
	public static final String aliIDcardauthorization = "Authorization";
	public static final String aliIDcardappcode = "APPCODE 963c93cef90c410b8cdd46e32308961d";

	public static final String aliOssAccessKeyId = "LTAIxoOlsbzg6ZWr";
	public static final String aliOssAccessKeySecret = "kM1oF9GXebCl71vHYPAuzAGe4m21ME";
	public static final String aliOssendpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	public static final String aliOssUrl = "http://lanzhipei.oss-cn-hangzhou.aliyuncs.com";
	public static final String aliOssbucket = "lanzhipei";

	// 1客服 2普通用户 3vip用户(代理) 4渠道用户 5销售 6销售经理
	public static final int[] usertype = { 0, 1, 2, 3, 4, 5, 6 };

	// 1客服 2普通用户 3vip用户(代理) 4渠道用户 5销售 6销售经理
	public static final String[] usertypedesc = { "", "客服", "普通用户", "代理", "渠道", "销售", "销售经理" };

	// 0：取消 1:报名，2:已缴学费，返费确认中，3：收到返费
	public static final int[] orderstatus = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	public static final String[] orderstatusdesc = { "已取消", "已报名", "已缴学费-返费确认中", "收到返费-结束", "未缴学费", "离职", "系统回滚" };
	public static final int[] orderworkstatus = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13,14};// 1：报名，2：已进厂，返费确认中
	public static final String[] orderworkstatusdesc = { "已取消", "已报名", "面试", "面试通过", "入职-返费确认中", "收到返费-结束", "拒绝面试",
			"面试未通过", "放弃入职", "离职", "供应商拖欠", "系统回滚", "申请离职","邀约成功","放弃面试"};

	// 1：培训产品，2：招工产品
	public static final int[] producttype = { 0, 1, 2 };
	// 40:修改产品名称,41:修改劳务公司,42:修改转单客服,43:修改商务,44:,修改姓名,45:,修改性别,46:修改年龄,47:修改学历,48:修改籍贯,49:修改身份证号,50:修改出生日期,
	//51:修改名族,52:修改纹身,53:修改残疾,54:修改证书,55:修改微信号,56:修改qq,57:修改订单状态,58:添加备忘录,59:添加录音

	public static final String[] typeid = { "62", "63", "64", "65", "66", "67", "68",
			"69", "70", "71", "72", "73", "74","75","76","77","78","79","80","81","82","83"};

	// 状态
	// 1申请处理，2处理中，3处理成功，4处理失败
	public static final int[] accountstatus = { 0, 1, 2, 3, 4 };

	public static final char[] yaoqingma = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'k', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z' };

	public static final String[] productstatus = { "下架", "上架", "删除" };

	public static final String key = "3tea8ZS4LH6dZxYyO61RxpV4hO8H/eMb";// lanzhipeiyaoshangshi
	public static final String weixinappid = "wx9cba0b6cd46c723c";
	public static final String weixinappsecret = "d1cb3c98602a6b922bcd33a6a7989995";
	public static String weixinticket = null;
	public static final String weixinurl = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String[] redpacketdesc = { "手气不错，领到红包", "领红包娶媳妇儿，易如反掌", "红包领到手，吃穿没烦恼", "红包领的好，打工工资高",
			"换个姿势，金额会更高", "抢红包还得靠颜值爆表", "少壮不努力,长大抢红包", "春眠不觉晓,醒来抢红包", "红星闪闪亮,照我抢红包", "夜夜思君不见君，原来君在抢红包",
			"亲朋好友如相问，就说我在抢红包", "待到山花烂漫时，他在使劲抢红包" };
	// '小时返费','日返费','分段返费-日'
	public static final String[] returnfeeType = { "小时返费", "日返费", "分段返费-日" };
	// "自然日","打开日"
	public static final String[] returnfeeDayType = { "自然日", "打卡日" };
	public static final double profit = 0.000136;
	static {
		wowifi_error_code.put("0", "接口调用正常");
		wowifi_error_code.put("WBOSS-08-0001", "携带的参数 丢失");
		wowifi_error_code.put("WBOSS-08-0002", "签名校验不正确");
		wowifi_error_code.put("WBOSS-08-0003", "令牌错误");
		wowifi_error_code.put("WBOSS-08-0004", "令牌已失效");
		wowifi_error_code.put("WBOSS-08-0005", "客户不存在");
		wowifi_error_code.put("WBOSS-08-0006", "合作方信息错误");
		wowifi_error_code.put("WBOSS-08-0000", "系统错误");
		wowifi_error_code.put("WBOSS-00-0001", "数据库操作异常");

	}
	// 双向回拨API账号信息
	public static final String callcenter_address = "apiusertest.emic.com.cn";
	public static final String callcenter_version = "20170405";
	public static final String callcenter_nonumber = "02566600117";
	public static final String callcenter_accountSid = "da25aee72841c073cf3648d33caa9b33";
	public static final String callcenter_authToken = "1b60a8237e9f4ed5def2e72507656064";
	public static final String callcenter_subAccountSid = "5976132e9cb71a2399d92acafd281e6a";
	public static final String callcenter_subAccountToken = "dd7fb9a697512edb3eb97de968150f39";
	public static final String callcenter_appId = "58c1b7eedb4320e86fc3e941daad2e9c";
	// 网络方式API账号信息
	public static final String callcenterOnline_address = "apiusertest.emic.com.cn";
	public static final String callcenterOnline_version = "20170405";
	public static final String callcenterOnline_nonumber = "02566683884";
	public static final String callcenterOnline_accountSid = "5d0b10ec8145386f283beb74e7091a6f";
	public static final String callcenterOnline_authToken = "21285f02f127c3288c65e7975f4ea944";
	public static final String callcenterOnline_subAccountSid = "28c0dc7dda0c393ebfe182d866c70b42";
	public static final String callcenterOnline_subAccountToken = "4134196939942c371599683a5effad22";
	public static final String callcenterOnline_appId = "574cdf46eb194f51c568975391920882";
	public static final String[] callcenterstate = { "呼叫挂机", "呼叫失败" };
	public static final String[] calltype = { "营销客服", "转单客服" };

	public static final String bi_ip = "121.40.74.18";
	public static final String bi_user = "root";
	public static final String bi_pwd = "1qaz@WSX";
	
	public static Long getOrderNO(long id) {
		String currenttime = System.currentTimeMillis() + "";
		currenttime = currenttime.substring(0, 10);
		return Long.valueOf(currenttime) + id;
	}
	
	//2000 招工和培训订单 收款凭证 2100 招工和培训订单 付款凭证 2200 招工和培训订单 收款单 2300 招工和培训订单  付款单
	public static final String[] finance_type = {"收款凭证","付款凭证","收款单据","付款单据"};
	public static final String[] finance_document_type = {"招工培训订单","购买会员订单"};

	
	public static final Map<String,String> mapping_document_type = new HashMap<String,String>();
	static {
		mapping_document_type.put("招工培训订单-收款凭证", "2000");
		mapping_document_type.put("招工培训订单-付款凭证", "2100");
		mapping_document_type.put("招工培训订单-收款单据", "2200");
		mapping_document_type.put("招工培训订单-付款单据", "2300");

		mapping_document_type.put("购买会员订单-收款凭证", "3000");
		mapping_document_type.put("购买会员订单-付款凭证", "3100");
		mapping_document_type.put("购买会员订单-收款单据", "3200");
		mapping_document_type.put("购买会员订单-付款单据", "3300");
	}
	

	/**
	 * 拷贝属性
	 * 
	 * @param source
	 * @param target
	 */
	@SuppressWarnings("all")
	public static void copyProperties(Map<String, String[]> source, Map target) {
		Set<String> set = source.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			target.put(key, source.get(key)[0]);
		}
	}

	/**
	 * 计算签名
	 * 
	 * @return
	 */

	public static String getSign(String[] value) {
		Collections.sort(Arrays.asList(value), new Comparator<String>() {
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		// 按字典顺序排序
		String str = StringUtils.join(value);
		PasswordED pd = new PasswordED();
		// 计算md5
		return pd.getMd5OfStr(str);
	}

	/**
	 * map 转 字符串 用&分开
	 * 
	 * @param map
	 * @return
	 */
	public static String map2Str(Map<String, String> map) {
		Iterator<String> it = map.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			sb.append(key + "=" + value);
			sb.append("&");
		}
		String param = sb.toString();
		param = param.substring(0, param.length() - 1);
		return param;
	}

	/**
	 * 将map转换成对象
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static <T> T map2Object(Class<T> clazz, Map<String, Object> map) {
		T instance = null;
		try {
			instance = clazz.newInstance();
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Field field = clazz.getDeclaredField(key);
				// 将成员变量设置为private
				field.setAccessible(true);
				field.set(instance, map.get(key));
			}

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * 将对象转换成map
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static void object2Map(Map<String, String> map, Object instance) {
		try {
			Class<?> clazz = instance.getClass();
			Field[] fields = clazz.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				// 允许方法私有变量
				fields[i].setAccessible(true);
				map.put(name, String.valueOf(fields[i].get(instance)));
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
                  System.out.println(Constants.orderworkstatusdesc[13]);
	}
}
