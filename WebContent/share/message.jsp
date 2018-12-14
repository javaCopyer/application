<%@ page  import="java.util.Map"  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Map<String,String> m = (Map<String,String>)request.getAttribute("user");
	String yaoqingma=m.get("yaoqingma");
	String subsource=m.get("subsource");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>邀请好友</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" href="/css/swiper.min.css">
		<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
		<script type="text/javascript" src="/js/swiper.min.js" ></script>
		<link rel="stylesheet" href="/css/bootstrap.min.css" />
		<script type="text/javascript" src="/js/bootstrap.min.js" ></script>
		<style>
			a,input{padding: 0;}
		     html, body {
		      position: relative;
		      height: 100%;
		    }
		    body {
		      font-size: 14px;
		      color:#000;
		      background: #F45151;
		      margin: 0;
		      padding: 0;
		    }
		    .swiper-container {
		      width: 100%;
		      height: 100%;
		    }
		    .swiper-slide {
		      text-align: center;
		      font-size: 18px;
		    }
		    .topimg{width: 100%;height: 60px;}
		    .topimg .img1{width: 25%;float: left;margin-top: 5px;}
		    .topimg .img2{width: 50%;float: left;margin-top: 20px;}
		    .texts{width: 80%;margin: 0 10% 8px 10%;height: 42px; background:rgba(255,255,255,.4);border-radius:20px;}
  			.texts .lft{float: left;width: 65%;padding: 0; padding-left: 15px; outline: none;font-size: 14px; color: #fff; border: 0;border-radius: 0; background: 0; height: 32px;border-right: 1px solid rgba(255,255,255,.5);margin-top: 4px;}
  			.texts .lft::-webkit-input-placeholder{color: #fff;}
  			.texts .lft1{border: 0;width: 100%;height: 40px;line-height: 40px;margin: 0;}
  			.texts .rgt{float: left;padding: 0;color: #fff;outline: none;width: 30%;overflow: hidden; display: block; font-size: 14px; text-align: center;border: 0;background: 0;height: 40px; line-height: 40px;}
  			.texts .rgt img{width: 70%; height: 30px;}
  			.btn{width: 80%;padding: 0;color: #F45151;font-size: 14px; height: 40px;background: #fff;outline: none;box-shadow:0px 4px 15px 0px rgba(132,34,34,0.26);border-radius:20px;margin: 0 10% 10px 10%;border: 0;}
			.xiala{text-align: center;margin-top: 15px;}
		</style>
	</head>
	<body>
		<!-- Swiper -->
	  <div class="swiper-container">
	    <div class="swiper-wrapper">
	    	<!--第一版-->
	      <div style="background: url(/images/banner2.png) #F45151;background-size: 100%;background-repeat: no-repeat;background-position:0 -45px" class="swiper-slide">
	      	<div class="topimg">
	      		<img class="img1" src="/images/icon.png" />
	      		<img class="img2" src="/images/wenzi.png" />
	      	</div>
	      	<div style="width: 100%;height: 295px"></div>
	      	<div class="texts">
	      		<input id="loginname" maxlength="11" class="lft lft1"  type="tel" placeholder="请输入手机号" />
	      	</div>
	      	<div style="display: none;" id="row-img-captcha" class="texts">
	      		<input class="lft"  id="input-captcha-img" type="tel" placeholder="图片验证码" />
	      		<div class="rgt">
	      			<img id="img-captcha" src="" />
	      		</div>
	      	</div>
	      	<div class="texts">
	      		<input class="lft"  id="captcha" type="tel" placeholder="请输入验证码" />
	      		<input class="rgt" id="getcode" type="button" value="获取验证码" />
	      	</div>
	      	<input id="submit-click" type="button" class="btn" value="获取更多职位信息" />
	      	<img class="xiala" src="../images/xiala.png" />
	      </div>
	      <!--第二版-->
	      <div class="swiper-slide">
	      	<img src="/images/btmbanner2.png" width="100%" />
	      	<input type="button" class="btn totop" value="获取更多职位信息" />
	      </div>
	    </div>
	    <!-- Add Pagination -->
	  </div>
	  <!--弹框-->
	  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div style="margin: 150px 15%;" class="modal-dialog">
			<div style="width: 100%; background: url(../images/success.png);box-shadow: 0 0 0; background-size: 100%;height: 265px;border: 0;" class="modal-content">
				<input onclick="window.location.href='/share/download.html'" style="margin-top: 200px;background: #F45353;height: 40px;color: #fff;" type="button" class="btn" value="立即下载"  />
			</div>
		</div>
	</div>
	 <script>
	    var swiper = new Swiper('.swiper-container', {
	      direction: 'vertical',
	      pagination: {
	        el: '.swiper-pagination',
	        clickable: true,
	      },
	    });
	    //跳转
	    $(".swiper-container").on("click",".xiala",function(){
    		swiper.slideTo(1);
    	})
		$(".swiper-container").on("click",".totop",function(){
    		swiper.slideTo(-1);
    	})
		
		 window.alert = function(name){
			 var iframe = document.createElement("IFRAME");
			 iframe.id="iframes";
			 iframe.style.display="none";
			 iframe.setAttribute("src", 'data:text/html,');
			 document.documentElement.appendChild(iframe);
			 window.frames[0].window.alert(name);
			 iframe.parentNode.removeChild(iframe);
			}
		
		//输入手机号显示图片验证码：
			$("#loginname").keyup(function(){
				if($(this).val().length==11){
					$("#row-img-captcha").show();
					 changeImgCaptcha();
					 $("#getcode").attr("disabled",false),
					 $("#getcode").val("获取验证码");
					 clearTimeout(times);
					 wait=60; 
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
						alert("系统错误")
					}
				});
				
			}
			$("#img-captcha").click(function(){
				changeImgCaptcha();
			});
			
			var wait=60;
			var times;
			function time(o) {
			  if (wait == 0) {
			   o.removeAttribute("disabled");   
			   o.value="获取验证码";
			   wait = 60;
			  } else { 
			 
			   o.setAttribute("disabled", true);
			   o.value="重新发送(" + wait + ")";
			   wait--;
			   times=setTimeout(function() {
			    time(o)
			   },1000)
			  }
			 }
			//获取验证码
			$("#getcode").click(function(){
		        if(!(/^1\d{10}$/.test($("#loginname").val().trim()))){ 
			        alert("手机号码有误，请重填");  
			        return false; 
			    } 
			    var imgCaptcha=$("#input-captcha-img").val().trim();
			    if(imgCaptcha==""||imgCaptcha.length<4){
			    	alert("图片验证码有误，请重填");  
			    	changeImgCaptcha();
			        return false;
			    }
			    time($("#getcode")[0]);//倒计时
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
							alert("短信已送达");  
						}else{
							alert(data.msg);
							changeImgCaptcha();
						}
					}
				});
				
			});
			
			//注册
			$("#submit-click").click(function(){
				if(!(/^1\d{10}$/.test($("#loginname").val().trim()))){ 
			        alert("手机号码有误，请重填");  
			        return false; 
			    } 
			    var imgCaptcha=$("#input-captcha-img").val().trim();
			    if(imgCaptcha==""||imgCaptcha.length<4){
			    	alert("图片验证码有误，请重填");  
			        return false;
			    }
				var data={};
				data['loginname']=$("#loginname").val().trim();
				data['smsMessage']=$("#captcha").val().trim();
				data['subsource']="<%=subsource %>"
				data['yaoqingma']="<%=yaoqingma %>";
				console.log(data);
				$.ajax({ 
					type: "post", 
					url:'/api/method059',
					data:data,
					error: function(request) {
						mui.toast("系统错误");
					},
					success: function(data) { 
						console.log(data);
						if(data.code==1){   
							$("#myModal").modal("show");
						}else{
							alert(data.obj.msg);
						}
					}
				});
			})
		
	 </script>
	</body>
</html>
