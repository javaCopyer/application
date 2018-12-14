<%@ page import="java.util.Map" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Map<String,String> m = (Map<String,String>)request.getAttribute("user");
	String openid = m.get("openid");
	String userid= m.get("userid");
	String subsource=m.get("subsource");
	String sharephone=m.get("sharephone");
%>
<html>

	<head>
		<meta charset="utf-8" />
		<title>会员权益</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" type="text/css" href="/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="/css/css.css" />
		<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
		<link href="/css/mui.min.css" rel="stylesheet" />
		<style>
			.modalcont{width: 80%;height: auto;overflow: hidden; border-radius: 10px;background: #FFFFFF;margin-left: 10%;}
			.modal_top{width: 100%;height:44px;line-height: 44px;text-align: center;border-bottom: 1px solid #EEEDED;}
			.mui-backdrop-action.mui-backdrop {background-color: rgba(0,0,0,.6);}
			.modalcont .moltext{width: 90%;height: 40px;margin: 10px 0 0 5%;border: 1px solid #CCCCCC;border-radius: 20px;}
			.modalcont .moltext .lft{float: left;width: 65%;padding: 0; padding-left: 15px; outline: none;font-size: 14px; color: #4d4d4d; border: 0;border-radius: 0; background: 0; height: 28px;border-right: 1px solid #E83525;margin-top: 6px;}
  			.modalcont .moltext .lft::-webkit-input-placeholder{color: #fff;}
  			.modalcont .moltext .lft1{border: 0;width: 100%;}
  			.modalcont .moltext .rgt{float: left;padding: 0;color: #E83525;outline: none;width: 30%;overflow: hidden;  font-size: 14px; text-align: center;border: 0;background: 0;height: 40px; line-height: 40px;}
  			.modalcont .moltext .rgt img{width: 70%; height: 30px;margin: 4px 15%;}
			.modalcont .nowbtn{border: 0;width: 40%;margin:10px 4% 0 0; color: #fff; height: 40px;background:#E83525;border-radius: 20px;}
		</style>
	</head>

	<body style="background: #fff;">
		<div class="bay_banner"></div>
		<p class="qy_lao">会员权益</p>
		<div class="hyq">
			<ul>
				<li>
					<a href="#">
						<img src="/images/img/huiyuan1.png" />
						<div>
							<p>成为会员</p>
							<span>一年挣10万左右</span>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="/images/img/fanfei.png" />
						<div>
							<p>高额返费</p>
							<span>返费每年拿5次，1500*5次=7500元</span>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="/images/img/yaoqing1.png" />
						<div>
							<p>邀请越多奖励越多</p>
							<span>邀请工友300个人，300*2元=600元</span>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="/images/img/dianzhu1.png" />
						<div>
							<p>优先升级为店主</p>
							<span>店主优挣60个人，60*600元=36000元</span>
						</div>
					</a>
				</li>
				<li>
					<a>
						<img src="/images/img/gongzi1.png" />
						<div>
							<p>高额工资</p>
							<span>每年6万元左右（每月工资5000元左右）</span>
						</div>
					</a>
				</li>
			</ul>
		</div>
		<a href="#signmodal"><div class="cwhu">
			成为会员365元/<span style="text-decoration: line-through;">1280</span>元优惠价
		</div></a>
		<!--购买会员-->
		<div class="fex hide">
			<div class="faad">
				<div class="fad_tit">
					<p>确认购买</p>
					<img src="/images/img/guanbis.png" />
				</div>
				<ul>
					<li>
						<a>
							<p>产品信息</p>
							<span>会员服务</span>
						</a>
					</li>
					<li >
						<a>
							<p>支付方式</p>
							<img src="/images/img/bankcard_19.png" class="rip" />
							<span>微信</span>
							<img src="/images/img/weixins.png" / class="wxs">
						</a>
					</li>
				</ul>
				<div id="membersuccess" class="lj">
					<a>立即成为会员</a>
				</div>
			</div>
		</div>
		<!--报名弹窗-->
		<div style="bottom:35%;" id="signmodal" class="mui-popover mui-popover-action mui-popover-bottom"> 
			<div style="padding-bottom: 20px;" class="modalcont">
				<div class="modal_top">请验证手机号</div>
				<div class="moltext">
					<input class="lft lft1" id="loginname" type="tel" maxlength="11" placeholder="请输入手机号" />
				</div>
				<div style="display: none;" id="row-img-captcha" class="moltext">
					<input class="lft" id="input-captcha-img" type="text" placeholder="图片验证码" maxlength="4" />
					<div  class="rgt">
						<img id="img-captcha" src="" />
					</div>
				</div>
				<div class="moltext">
					<input class="lft" id="captcha" type="text" placeholder="短信验证码"  maxlength="4" />
					<input class="rgt" id="getcode" type="button" value="获取验证码" />
				</div>
				<a href="#signmodal"><input style="background: #E7E7E7;color: #808080;margin-left: 8%;" type="button" class="btn nowbtn" value="取消" /></a>
				<input id="submit-success" type="button" class="btn nowbtn" value="确定" />
			</div> 
		</div> 
		<script type="text/javascript" src="/js/rem.js"></script>
		<script src="/js/mui.min.js"></script>
		<script>
			//取消
			$('.fex .faad .fad_tit img').click(function() {
				$('.fex').hide();
			})
			//输入手机号显示图片验证码：
			$("#loginname").keyup(function(){
				if($(this).val().length==11){
					$("#row-img-captcha").show();
					 changeImgCaptcha();
				}
			});
			//点击更换验证码图片
			function changeImgCaptcha(){
				$.ajax({
					type:'get',//HTTP请求类型
					url:'/api/method002',
					data:{phone:$("#loginname").val().trim()},
					success:function(data){
						$('#img-captcha').attr("src",data.obj.url); 
						$("#input-captcha-img").val("");
					},
					error:function(request){
						//异常处理；
						mui.toast("系统错误")
					}
				});
				
			}
			$("#img-captcha").click(function(){
				changeImgCaptcha();
			});
			
			var wait=60;
			function time(o) {
			  if (wait == 0) {
			   o.removeAttribute("disabled");   
			   o.value="获取验证码";
			   wait = 60;
			  } else { 
			 
			   o.setAttribute("disabled", true);
			   o.value="重新发送(" + wait + ")";
			   wait--;
			   setTimeout(function() {
			    time(o)
			   },1000)
			  }
			 }
			//获取验证码
			$("#getcode").click(function(){
		        if(!(/^1\d{10}$/.test($("#loginname").val().trim()))){ 
			        mui.toast("手机号码有误，请重填");  
			        return false; 
			    } 
			    var imgCaptcha=$("#input-captcha-img").val().trim();
			    if(imgCaptcha==""||imgCaptcha.length<4){
			    	mui.toast("图片验证码有误，请重填");  
			    	changeImgCaptcha();
			        return false;
			    }
				var data={};
				data['phone']=$("#loginname").val().trim();
				data["piccaptcha"]=imgCaptcha; 
				console.log(data)
				$.ajax({
					type: "get",
					url:'/api/method003', 
					data:data,
					error: function(request) {
						mui.toast("系统错误");
					},
					success: function(data) {
						console.log(data)
						if(data.code==1){//成功
							mui.toast("已发送成功");  
							 time($("#getcode")[0]);//倒计时
						}else{
							mui.toast(data.msg);
							changeImgCaptcha();
						}
					}
				});
				
			});
			
			//确认输入手机号码获取订单支付信息
			$("#submit-success").click(function(){
				if(!(/^1\d{10}$/.test($("#loginname").val().trim()))){ 
			        mui.toast("手机号码有误，请重填");  
			        return false; 
			    } 
			    var imgCaptcha=$("#input-captcha-img").val().trim();
			    if(imgCaptcha==""||imgCaptcha.length<4){
			    	mui.toast("图片验证码有误，请重填");  
			        return false;
			    }
				var data={};
				data.sharephone="<%=sharephone %>";
				data.loginname=$("#loginname").val().trim();
				data.smsMessage=$("#captcha").val().trim();
				data.userId="<%=userid %>";
				data.source="app";
				data.subsource="<%=subsource %>";
				data.openid="<%=openid %>";
				data.loginname=$("#loginname").val().trim();
				data.payType=2;
				$.ajax({ 
					type: "post", 
					url:'/api/wxpay',
					data:data,
					error: function(request) {
						mui.toast("系统错误");
					},
					success: function(data) { 
						data=JSON.parse(data)
						if(data.code==1){   
							window.apppaydata=data.obj;
							mui("#signmodal").popover("hide");
							$('.fex').show();
						}else{
							mui.toast(data.msg);
						}
					}
				});
			})
			
			//微信内h5支付
			$("#membersuccess").click(function() {
				var data=window.apppaydata;
				WeixinJSBridge.invoke(
				  'getBrandWCPayRequest', {
					"appId": data.appId, //公众号名称，由商户传入     
					"timeStamp": data.timeStamp, //时间戳，自1970年以来的秒数     
					"nonceStr": data.nonceStr, //随机串     
					"package": data.package,
					"signType": data.signType, //微信签名方式：     
					"paySign": data.paySign //微信签名 
				},
				function(res) {
					//alert(JSON.stringify(res))
					if(res.err_msg == "get_brand_wcpay_request:ok") { //支付成功
						window.location.href = '/share/paysuccess.jsp';
					}
				});
			})
//			if (typeof WeixinJSBridge == "undefined"){
//			   if( document.addEventListener ){
//			       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
//			   }else if (document.attachEvent){
//			       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
//			       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
//			   }
//			}else{
//			   onBridgeReady();
//			}
		</script>
	</body>

</html>