<%@ page import="java.util.Map" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Map<String,String> m = (Map<String,String>)request.getAttribute("user");
	String openid=m.get("openid");
	String userid=m.get("userid");
	String loginname=m.get("sharephone");
	String productid=m.get("productid");
	String subsource=m.get("subsource");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="/css/mui.min.css" rel="stylesheet" />
		<style>
			ul{padding: 0;margin: 0;list-style: none;}
			.modular1{width: 94%;margin: 17px 3%;padding: 15px; height: auto;padding-bottom: 15px; background: #fff;box-shadow:0px 0px 12px 0px rgba(0,0,0,0.1);border-radius:5px;}
			.modular1 h5{font-size: 18px;color: #1F1F1F;margin-bottom: 15px;}
			.modular1 h6{color: #1F1F1F;font-size: 14px;margin-bottom: 8px;}.modular1 h6 img{width: 14px;margin-right: 5px;}
			.mui-slider img{width: 100%;height: 150px;}.mui-slider{height: 150px;}
			.mui-slider-indicator{bottom: 0;background: #fff;opacity: 0.6;height: 25px; text-align: right;line-height: 25px;}
			.mui-slider-indicator ul{float: left;width: 70%;height: 25px;overflow: hidden;}
			.mui-slider-indicator ul li{float: left;margin-left: 5px;font-size: 12px;color: #E83525;line-height: 28px;}.mui-slider-indicator ul li img{width: 10px;height: 8px;margin-right: 3px;}
			.work_jg{width: 100%;height: 10px;background: #FAFAFA;}
			.modular2{width: 100%;height: auto;background: #fff;padding: 10px 3%;}
			.modular2 h5{color: #F23030;font-size: 18px;margin-bottom: 15px;}
			.modular2 h6{color: #9B9B9B;font-size: 12px;margin-bottom: 12px;}.modular2 h6 a{color: #E83525;font-size: 14px;}
			.modular2 h6 span{background: #FDEAEA;padding: 3px 4px; border-radius: 5px;color:#E83525;}
			.modular3{width: 100%;height: auto;background: #fff;padding: 15px 2%;display: none;}
			.modular3 .title{width: 100%;height: 27px;line-height: 27px;}
			.modular3 .title img{width: 20px;float: left;margin-right: 5px;}
			.modular3 .title a{float: left;color: #333333;font-size: 18px;}
			.modular3 .title line{width: 60%;float: left;height: 1px;background: #EEEDED;margin: 14px 0 0 6px;}
			.modular3 .content{font-size: 14px;line-height: 26px;color: #1F1F1F;width: 100%;padding: 10px 2% 0 2%;}
			.detail_btm{width: 100%;height: 45px;line-height: 45px;background: #fff;position: fixed;z-index: 999;bottom: 0;}
			.detail_btm .pull-left{float: left;width: 50%;height: 45px;border-top: 1px solid #EEEDED; font-size: 14px;color: #5B5D67;line-height: 45px;}
			.detail_btm .pull-left img{width: 17px;float: left;margin: 12px 5px 0 35%;}
			.detail_btm .pull-right{float: left;width: 50%;height: 45px;color: #fff;font-size: 16px;line-height: 45px;text-align: center; background: linear-gradient(#FF5C5C, #EA2323);}
			.wxtouxaing{height: 90px;}.wxtouxaing .tx{float: left;width: 60px;margin: 5px 3%;}.wxtouxaing div{float: left;color: #9B9B9B;font-size: 14px;width:65%;overflow:hidden;}
			.wxtouxaing div p{font-size: 18px;color: #333333;margin: 15px 0 8px 0;}.wxtouxaing .right{float: right;width: 7px;margin-top: 30px;}
			.modalcont{width: 80%;height: auto;overflow: hidden; border-radius: 10px;background: #FFFFFF;margin-left: 10%;}
			.modal_top{width: 100%;height:44px;line-height: 44px;text-align: center;border-bottom: 1px solid #EEEDED;}
			.my_btn{background: #fff;border-radius: 0;border: 0;font-size: 16px; color: #333333;width: 50%;float: left;height: 42px;text-align: center;}
			.my_btn2{background:#E83525;color: #FFFFFF;border: 0;}
			.mui-backdrop-action.mui-backdrop {background-color: rgba(0,0,0,.6);}
			.modalcont .moltext{width: 90%;height: 40px;margin: 10px 0 0 5%;}
			.modalcont .moltext span{float: left;width: 25%;display: inline-block;line-height: 40px;}
			.modalcont .moltext input{float: left;width: 70%;height: 40px; border: 1px solid #E7E7E7;border-radius: 20px;}
			.modalcont .nowbtn{border: 0;width: 90%;margin:15px 5% 0 5%; color: #fff; height: 40px;background: #E83525;border-radius: 20px;}
		</style>
	</head>
	<body style="background: #fff;">
		<div class="modular1">
			<h5>岗位信息</h5>
			<h6><img src="/images/workdetail/danwei@2x.png" /> 工作单位：<text id="factory"></text></h6>
			<h6><img src="/images/workdetail/gongzi@2x.png" /> 综合工资：<text id="salary"></text>元</h6>
			<h6><img src="/images/workdetail/nianling@2x.png" /> 年龄：<text id="agecont"></text></h6>
			<h6><img src="/images/workdetail/dizhi@2x.png" /> 地址：<text id="address"></text></h6>
		</div>
		<div id="slider" class="mui-slider" >
			<div id="bannerslide" class="mui-slider-group mui-slider-loop">
				
			</div>
			<div  class="mui-slider-indicator">
				<text id="bannerinca">
					
				</text>
				<ul id="taglist">
					
				</ul>
			</div>
		</div>
		<div class="work_jg"></div>
		<div class="modular2">
			<h5>奖励</h5>
			<h6><span>生活补贴</span><text id="lifesubsidy"></text></h6>
			<h6><span>会员奖励</span><text id="membersubsidy"></text></h6>
		</div>
		<div class="work_jg"></div>
		<div id="tomemberck" class="modular2 wxtouxaing">
			<img id="headimgurl" class="tx" src="" />
			<div id="usercont"></div>
			<img id="tomember" class="right" src="/images/workdetail/gengduo@2x.png" />
		</div> 
		<div class="work_jg"></div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>工资待遇</a>
				<line></line>
			</div>
			<div id="det-content1" class="content">
				
			</div>
		</div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>吃住安排</a>
				<line></line>
			</div>
			<div id="det-content2" class="content">
				
			</div>
		</div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>岗位要求</a>
				<line></line>
			</div>
			<div id="det-content3" class="content">
				
			</div>
		</div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>工作内容</a>
				<line></line>
			</div>
			<div id="det-content4" class="content">
				
			</div>
		</div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>面试</a>
				<line></line>
			</div>
			<div id="det-content5" class="content">
				
			</div>
		</div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>合同</a>
				<line></line>
			</div>
			<div id="det-content6" class="content">
				
			</div>
		</div>
		<div class="modular3">
			<div class="title">
				<img src="/images/workdetail/leimu@2x.png" />
				<a>企业介绍</a>
				<line></line>
			</div>
			<div id="det-content7" class="content">
				
			</div>
		</div>
		<!--底部-->
		<div style="width: 100%;height: 45px;"></div>
		<div class="detail_btm">
			<a href="#customermodal">
			<div class="pull-left">
				<img src="/images/workdetail/zixun@2x.png" />咨询
			</div></a>
			<a href="#signmodal"><div class="pull-right">立即报名</div></a>
		</div>
		<!--咨询弹窗-->
		<div style="bottom:35%;" id="customermodal" class="mui-popover mui-popover-action mui-popover-bottom"> 
			<div class="modalcont">
				<div class="modal_top">电话咨询</div>
				<div style="text-align: center;margin: 28px 0;color: #3e3e3e;font-size: 22px;">400-669-0979</div>
				<div style="width: 100%;height: 42px;">
					<a href="#customermodal"><button type="button" class="mui-btn mui-btn-primary my_btn" onclick="return false;">取消</button></a>
					<a href="tel:400-669-0979"><button type="button" class="mui-btn mui-btn-primary my_btn my_btn2">呼叫</button></a>
				</div>
			</div> 
		</div> 
		<!--报名弹窗-->
		<div style="bottom:35%;" id="signmodal" class="mui-popover mui-popover-action mui-popover-bottom"> 
			<div style="padding-bottom: 20px;" class="modalcont">
				<div class="modal_top">立即报名</div>
				<div class="moltext">
					<span>姓名</span>
					<input id="name" type="text" maxlength="10" />
				</div>
				<div class="moltext">
					<span>手机号</span>
					<input id="loginname" type="tel" maxlength="11" />
				</div>
				<input id="submit-success" type="button" class="btn nowbtn" value="立即报名" />
			</div> 
		</div> 
		<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
		<script src="/js/mui.min.js"></script>
		<script type="text/javascript">
				$(function(){
					//关注
					guanzhufun();
					
					var data={productid:"<%=productid %>",phone:"<%=loginname %>"} 
					console.log(data);
					$.ajax({
				         type: "get",
				         url: "/api/method014",
				         data:data,
				         error: function(request) {
				             alert("系统错误");
				         },
				         success: function(data) {
				         	console.log(data);
							var listinfo=data.obj.基本信息;//基本信息
							$("title").html(listinfo.longtitle);//标题
							$("#factory").html(listinfo.factoryname)//工作单位
							$("#salary").html(listinfo.minsalary+"-"+listinfo.maxsalary)//综合工资
							$("#agecont").html('男性年龄'+listinfo.minage+'-'+listinfo.maxage+',女性年龄'+listinfo.womanminage+'-'+listinfo.womanmaxage)//年龄
							$("#address").html(listinfo.address)//地址
							//查询banner图片
							var photodata=listinfo.pictures.split(",");
							//最后一张
							$("#bannerslide").prepend('<div class="mui-slider-item mui-slider-item-duplicate">'
							+'<a ><img src="'+photodata[photodata.length-1]+'"></a></div>');
							for(var i=0;i<photodata.length;i++){
								//轮播图
								$("#bannerslide").append('<div class="mui-slider-item">'
						     	+'<a ><img src="'+photodata[i]+'"></a></div>');
								//下边圆点
								if(i==0){
									$("#bannerinca").append('<div class="mui-indicator mui-active"></div>')
								}else{
									$("#bannerinca").append('<div class="mui-indicator"></div>')
								}
							}
							//第一张
							$("#bannerslide").append('<div class="mui-slider-item mui-slider-item-duplicate">'
							+'<a><img src="'+photodata[0]+'"></a></div>');
							//banner轮播(初始化轮播)
							var slider = document.getElementById("slider");
							document.getElementById("slider").classList.remove("mui-hidden");
							mui("#slider").slider({
								interval: 2000
							});
							//标签
							for (var k=0;k<data.obj.标签.length;k++) {
								if(k==3){
									break;
								}
								$("#taglist").append('<li><img src="/images/workdetail/dui@2x.png" />'+data.obj.标签[k].name+'</li>')
							}
							if(listinfo.lifecontent==0&&listinfo.memberamount==0){
								if(listinfo.inviterAmount==0){
									$("#lifesubsidy").parents(".modular2").remove();
								}
								var htmla='';
								if(listinfo.lifecontent!=0||listinfo.memberamount!=0){ 
								$("#lifesubsidy").parent().hide(); 
								htmla+='<h5>奖励</h5>'
								}
								
								//htmla+='<h6><span>邀请奖励</span><text><a> ￥'+listinfo.inviterAmount+'</a></text></h6>'
								$("#lifesubsidy").parents(".modular2").html(htmla);
							}
							if(listinfo.lifecontent==0){ 
								$("#lifesubsidy").parent().hide(); 
							}else{
								//生活补贴
								$("#lifesubsidy").html('<a>￥'+listinfo.lifecontent+'</a>入职'+listinfo.day+'个打卡日');//生活补贴
							}
							if(listinfo.memberamount==0){ 
								$("#membersubsidy").parent().hide(); 
							}else{
								var memhtml='';
								if(listinfo.lifecontent==0){
									memhtml='<a> ￥'+listinfo.memberamount+'</a>会员入职额外奖励';
								}else{
									memhtml='<a>￥'+listinfo.lifecontent+'+￥'+listinfo.memberamount+'</a>会员入职额外奖励';
								}
								if(data.obj.用户信息.usertype!=8&&data.obj.用户信息.usertype!=9){
									memhtml+='<a onclick="linkpage(\'member\',\'member.html\',\'product\',\'Product_01\')" style="text-decoration:underline">成为会员</a>';
								}
								$("#membersubsidy").html(memhtml) //会员奖励
							}
							window.usertype=data.obj.用户信息.usertype;//用户状态
							//工资待遇
							$("#det-content1").html(data.obj.compensationwelfare);
							//吃住安排
							$("#det-content2").html(data.obj.吃住条件);
							//岗位要求
							$("#det-content3").html(data.obj.职位要求);
							//工作内容
							$("#det-content4").html(data.obj.jobcontent);
							//面试
							$("#det-content5").html(data.obj.interviewsituation);
							//合同
							$("#det-content6").html(data.obj.compactions);
							//企业介绍
							$("#det-content7").html(listinfo.factorycontent);
							if(listinfo.lifecontent==0){ 
								$("#lifesubsidy").parents(".modular2").hide(); 
							}
							if(listinfo.factorycontent!=null&&listinfo.factorycontent!=""){
								$("#det-content7").parents(".modular3").show()
							}
							if(data.obj.compactions!=null&&data.obj.compactions!=""){
								$("#det-content6").parents(".modular3").show()
							}
							if(data.obj.interviewsituation!=null&&data.obj.interviewsituation!=""){
								$("#det-content5").parents(".modular3").show()
							}
							if(data.obj.jobcontent!=null&&data.obj.jobcontent!=""){
								$("#det-content4").parents(".modular3").show()
							}
							if(data.obj.职位要求!=null&&data.obj.职位要求!=""){
								$("#det-content3").parents(".modular3").show()
							}
							if(data.obj.吃住条件!=null&&data.obj.吃住条件!=""){ 
								$("#det-content2").parents(".modular3").show()
							}
							if(data.obj.compensationwelfare!=null&&data.obj.compensationwelfare!=""){
								$("#det-content1").parents(".modular3").show()
							}
							//用户信息
							$("#headimgurl").attr("src",data.obj.用户信息.headimgurl==null?"/images/Group.png":data.obj.用户信息.headimgurl);
							$("#usercont").html("<p>"+data.obj.用户信息.name+"</p><%=loginname %>")
							if(data.obj.用户信息.usertype!=8&&data.obj.用户信息.usertype!=9){
								$("#tomember").hide();
							}
							window.userid=data.obj.用户信息.id;
							window.usertype=data.obj.用户信息.usertype;
							
						}
					})
				})
				//关注
				function guanzhufun(){
					var data={};
					data.openid="<%=openid %>"
					data.inviteruserid="<%=userid%>";
					data.productid="<%=productid%>";
					data.type=1;
					$.ajax({
				         type: "get",
				         url: "/api/method015",
				         data:data
				   })
				}
				
				//报名
				$("#submit-success").click(function(){
					if(!(/^[\u4E00-\u9FA5]{1,10}$/.test($("#name").val().trim()))){
						alert("名字请输入1-10个汉字");
						return;
					}
					if(!(/^1\d{10}$/.test($("#loginname").val().trim()))){
						alert("请输入正确手机号");
						return;
					}
					var data={};
					data.name=$("#name").val().trim();
					data.loginname=$("#loginname").val().trim();
					data.inviteduserid="<%=userid%>";
					data.productid="<%=productid%>";
					data.ordertype="招工";
					data.source="app";
					data.subsource="<%=subsource %>";
					console.log(data);
					$.ajax({
				         type: "post",
				         url: "/api/method031",
				         data:data,
				         error: function(request) {
				             alert("系统错误");
				         },
				         success: function(data) {
							console.log(JSON.stringify(data));
							alert(data.msg);
							if(data.code==1){
								mui("#signmodal").popover("hide");
								window.location.href='/share/success.jsp'
							}
						}
				   })
				})
				
			//进入店铺
			$("#tomemberck").click(function(){
				if(window.usertype==8||window.usertype==9){
					window.location.href='/api/method063?userid='+window.userid+'&openid=<%=openid %>&sharephone=<%=loginname %>';
				}
			})
		</script>
	</body>

</html>