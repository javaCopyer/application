<%@ page import="java.util.Map" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Map<String,String> m = (Map<String,String>)request.getAttribute("user");
	String userid=m.get("userid");
	String openid=m.get("openid");
	String subsource=m.get("subsource");
	String sharephone=m.get("sharephone");
%>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="/css/css.css" />
		<style>
			img{display: inline-block;}
			.topbg{ width: 100%;height: 150px;overflow: hidden; background: url(/images/img/bgc.png);background-size: 100% 100%;}
			.topbg .topcont{width: 100%;margin-top: 15px;} .topbg .touxiang{float: left;width: 70px;}
			.topbg div{float: left;color: #fff;margin: 0 10px;font-size: 12px;}
			.topbg div p{color: #fff;font-size: 16px;margin: 10px 0 5px 0;} .topbg .icons{float: left;width: 52px;margin-top: 10px;} 
			.shoptitles{padding:15px 0 20px 0;text-align: center;color: #333333;font-size: 18px;}
			.modular1cont{width: 90%;margin: 0 5% 30px 5%;border-left: 3px solid #E83525; height: 56px; background:rgba(255,255,255,1);box-shadow:0px 3px 5px 0px rgba(0,0,0,0.05);}
			.modular1cont div{font-size: 13px;line-height: 20px;width: 75%;float: left;margin: 8px 3%;}
			.modular1cont img{float: left;width: 60px;}
			.modualr2{list-style: none;width: 100%;height: 60px;margin-bottom: 25px;padding: 0 5%;}
			.modualr2 li{float: left;width: 20%;height: 60px; margin-right: 6%;color: #9B9B9B;font-size: 12px;text-align: center;overflow: hidden;}.modualr2 li img{width: 31px;height: 31px; display: inline-block;}
			.modualr2 li:nth-child(4){margin-right: 0;}  
			.modular3{width: 90%; height: 45px;margin:0 5% 15px 5%;border-radius: 23px;background: #FDEAEA;}
			.modular3 .left_info{float: left;width: 60%;padding: 5px 8%; height: 45px;color: #E83525;font-size: 12px;}
			.modular3 .left_info .money1 a{font-size: 18px;color: #E83525;}.modular3 .left_info .money2 a{color: #9B9B9B;font-size: 10px;text-decoration:line-through;}
			.modular3 .left_info .money2 span{border: 1px solid #E83525;padding: 1px 3px;border-radius: 20px;}
			.modular3 .right_info{float: right;width: 40%;font-size: 10px;text-align: center; color: #fff; background: #E83525;height: 45px;border-bottom-right-radius: 23px;border-top-right-radius: 23px;}
			.modular3 .right_info p{font-size: 14px;line-height: 16px;margin:7px 0 0 0; color: #fff;}
			.rizbutton{background: #E83525;color: #fff;border: 0;font-size: 12px; border-radius: 2px;margin-top: 20px;}
			.modalcont{width: 80%;height: auto;overflow: hidden; border-radius: 10px;background: #FFFFFF;margin-left: 10%;}
			.modal_top{width: 100%;height:44px;line-height: 44px;text-align: center;border-bottom: 1px solid #EEEDED;}
			.mui-backdrop-action.mui-backdrop {background-color: rgba(0,0,0,.6);}
			.modalcont .moltext{width: 90%;height: 40px;margin: 10px 0 0 5%;}
			.modalcont .moltext span{float: left;width: 25%;display: inline-block;line-height: 40px;}
			.modalcont .moltext input{float: left;width: 70%;height: 40px; border: 1px solid #E7E7E7;border-radius: 20px;}
			.modalcont .nowbtn{border: 0;width: 90%;margin:15px 5% 0 5%; color: #fff; height: 40px;background: #E83525;border-radius: 20px;}
			.ri_z a{float:right;width:63px;margin-top: 15px; height:25px;background:#E83525;color:#fff;text-align: center;font-size: 14px;line-height: 25px;border-radius:2px;}
			/*列表*/
			.mescroll{position: fixed;top: 0;bottom: 0;height: auto;}
		</style> 
	</head>

	<body style="background: #fff;">
		<div id="mescroll" class="mescroll">
			<div class="topbg">
				<a style="color: #fff;margin: 10px 0 0 3%;" class="mui-pull-left">&nbsp;</a>
				<div class="topcont">
					<img id="headimgurl" class="touxiang" src="" />
					<div id="wxname"></div>
					<img class="icons" src="/images/img/huiyuanicon.png" />
				</div>
			</div>
			<h5 class="shoptitles">会员权益</h5> 
			<div class="modular1cont">
				<div>1、成为会员，每年多赚8000元。<br>  2、20%会员成为月赚1万元的店主。</div>
				<img src="/images/img/quanyi@2x.png" />
			</div>
			<ul class="modualr2">
				<li><img src="/images/work/butie@2x.png" /><br />高额补贴</li>
				<li><img src="/images/work/zhuanshu@2x.png" /><br />专属客服</li>
				<li><img src="/images/work/gangwei@2x.png" /><br />高薪工作岗位</li>
				<li><img src="/images/work/dianzhu@2x.png" /><br />成为店主</li>
			</ul>
			<div class="modular3">
				<div class="left_info">
					<div style="line-height: 16px;" class="money1">￥<a>365</a>.00</div>
					<div class="money2"><span>会员超值价</span> <a>￥1280.00</a></div>
				</div>
				<div style="font-size: 16px;line-height: 45px;" id="topayman" class="right_info">
					成为会员
				</div>
			</div>
			<div style="width: 100%;height: 10px;background: #F0F0F0;"></div>
			<!--列表-->
			<div class="nav_lists">
				<ul class="ul">
					<li id="search-cityname" style="width: 40px;height:30px;overflow: hidden;text-overflow:ellipsis; white-space:nowrap">城市</li>
					<li>生活补贴</li>
					<li>综合工资</li>
					<li>职位信息</li>
				</ul>
			</div>
			<div class="list_icon">
				<!--数据列表-->
				<ul id="worklist" >
						
				</ul>
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
		<link rel="stylesheet" href="/css/mescroll.css" />
		<script src="/js/mescroll.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
		<script type="text/javascript" src="/js/rem.js"></script>
		<script type="text/javascript" src="/js/jquery.cookie.js" ></script>
		<script src="/js/mui.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var headimgurl="<%=m.get("headimgurl") %>";
				if("<%=m.get("headimgurl") %>"=="null"||"<%=m.get("headimgurl") %>"==null){
					headimgurl="/images/Group.png";
				}
				$("#headimgurl").attr("src",headimgurl);
				$("#wxname").html('<p><%=m.get("nickname") %></p><%=m.get("sharephone") %>')
				$("title").html('<%=m.get("nickname") %>')
				
				console.log($.cookie('search_cityname')+"&&&&&"+$.cookie('search_cityid'))
				$("#search-cityname").text(($.cookie("search_cityname")==null||$.cookie("search_cityname")=="")?"城市":$.cookie("search_cityname"));
				window.listcity=$.cookie("search_cityid");
				if($.cookie("search_positionid")!=null){
					window.listposition=JSON.parse($.cookie("search_positionid"));
				}
				console.log(window.listcity);
				console.log(window.listposition);
				//创建MeScroll对象
					var mescroll = new MeScroll("mescroll", {
//						down: {
//							auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
//							callback: downCallback //下拉刷新的回调
//						},
						up: {
							auto: true, //是否在初始化时以上拉加载的方式自动加载第一页数据; 默认false
							isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
							callback: upCallback, //上拉回调,此处可简写; 相当于 callback: function (page) { upCallback(page); }
							clearEmptyId: "worklist", //1.下拉刷新时会自动先清空此列表,再加入数据; 2.无任何数据时会在此列表自动提示空
							page: {
								num: 0, //当前页 默认0,回调之前会加1; 即callback(page)会从1开始
								size: 10 //每页数据条数,默认10
							}
//							toTop:{ //配置回到顶部按钮
//								src : "../res/img/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
//								//offset : 1000
//							}
						}
					});
					
//					/*下拉刷新的回调 */
//					function downCallback(){
//						//联网加载数据
//						getListDataFromNet(1, 10, function(data){
//							//联网成功的回调,隐藏下拉刷新的状态
//							mescroll.endSuccess();
//							//设置列表数据
//							setListData(data.List);
//						}, function(){
//							//联网失败的回调,隐藏下拉刷新的状态
//			                mescroll.endErr();
//						});
//					}
					
					/*上拉加载的回调 page = {num:1, size:10}; num:当前页 从1开始, size:每页数据条数 */
					function upCallback(page){
						//联网加载数据
						getListDataFromNet(page.num, page.size, function(curPageData){
							//联网成功的回调,隐藏下拉刷新和上拉加载的状态;
							//mescroll会根据传的参数,自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
							console.log("page.num="+page.num+", page.size="+page.size+", curPageData.length="+curPageData.List.length);
							
							//方法一(推荐): 后台接口有返回列表的总页数 totalPage
							mescroll.endByPage(curPageData.List.length, curPageData.count); //必传参数(当前页的数据个数, 总页数)
							
							//方法二(推荐): 后台接口有返回列表的总数据量 totalSize
							//mescroll.endBySize(curPageData.length, totalSize); //必传参数(当前页的数据个数, 总数据量)
							
							//方法三(推荐): 您有其他方式知道是否有下一页 hasNext
							//mescroll.endSuccess(curPageData.length, hasNext); //必传参数(当前页的数据个数, 是否有下一页true/false)
							
							//方法四 (不推荐),会存在一个小问题:比如列表共有20条数据,每页加载10条,共2页.如果只根据当前页的数据个数判断,则需翻到第三页才会知道无更多数据,如果传了hasNext,则翻到第二页即可显示无更多数据.
//							mescroll.endSuccess(curPageData.length);
							
							//提示:curPageData.length必传的原因:
							// 1.判断是否有下一页的首要依据: 当传的值小于page.size时,则一定会认为无更多数据.
							// 2.比传入的totalPage, totalSize, hasNext具有更高的判断优先级
							// 3.使配置的noMoreSize生效
							
							//设置列表数据
							setListData(curPageData.List);
						}, function(){
							//联网失败的回调,隐藏下拉刷新和上拉加载的状态;
			                mescroll.endErr();
						});
					}
					
					/*设置列表数据*/
					function setListData(list) {
						var html='';
						for (var i=0;i<list.length;i++) {
							html+='<li>'
							html+='<text data-id="'+list[i].productid+'"><div class="left_info">'
							html+='		<img src="'+list[i].logo+'" />'
							html+='		<span>'+list[i].address+'</span>'
							html+='		<p>年龄'+list[i].minage+'-'+list[i].maxage+'岁</p>'
							html+='	</div>'
							html+='	<div class="mid1">'
							html+='		<h3>'+list[i].title+'</h3>'
							html+='		<div class="mid_nj">'
							if(list[i].livingallowancesAmount==0&&list[i].memberamount==0){
								html+='<p style="background:#fff">&nbsp; </p>'
							}else if(list[i].memberamount==0){
								html+='<p>补贴</p>'
								html+='<span>￥'+list[i].livingallowancesAmount+'</span>'
							}else if(list[i].livingallowancesAmount==0){
								html+='<p>会员</p>'
								html+='<span>￥'+list[i].memberamount+'</span>'
							}else{
								html+='<p>补贴</p>'
								html+='<span>￥'+list[i].livingallowancesAmount+'+'+list[i].memberamount+'</span>'
								html+='<em>会员入职奖</em>'
							}	
							html+='		</div>'
							html+='		<div class="mid_nj1">'
							html+='			<p>工资</p>'
							html+='			<span>￥'+list[i].minsalary+'-'+list[i].maxsalary+'</span>'
							html+='		</div>'
							html+='	</div></text>'
							html+='	<div class="ri_z">'
							html+='		<a data-productid='+list[i].productid+'  class="fl">报名</a>'
							html+='	</div>'
							html+='</li>'
						}
						$("#worklist").append(html);
					}
					
					/*联网加载列表数据
					 在您的实际项目中,请参考官方写法: http://www.mescroll.com/api.html#tagUpCallback
					 请忽略getListDataFromNet的逻辑,这里仅仅是在本地模拟分页数据,本地演示用
					 实际项目以您服务器接口返回的数据为准,无需本地处理分页.
					 * */
					function getListDataFromNet(pageNum,pageSize,successCallback,errorCallback) {
						//延时一秒,模拟联网
		                setTimeout(function () {
		                	var data={};
		                	data.page=pageNum;
		                	data.rows=pageSize;
		                	if(window.listtype=="life"){
								$('.nav_lists .ul li:nth-child(2)').addClass("active").siblings().removeClass("active");
								data.life=1;
							}
							if(window.listtype=="salary"){
								$('.nav_lists .ul li:nth-child(3)').addClass("active").siblings().removeClass("active");
								data.salary=1;
							}
							if(window.listposition!=null){ 
								if(window.listposition.age!=null){ 
									data.age=window.listposition.age;
								}
								if(window.listposition.sex!=null){
									data.Sex=window.listposition.sex;
								}
								if(window.listposition.nation!=null){
									data.nation=window.listposition.nation;
								}
								if(window.listposition.WorkNature!=null){
									data.WorkNature=window.listposition.WorkNature;
								}
								if(window.listposition.tattoo!=null){
									data.tattoo=window.listposition.tattoo;
								}
							}
							//城市
							if(window.listcity!=null&&window.listcity!=""){
								data.cityid=window.listcity;
							}
		                	console.log(data);
		                	$.ajax({
				                type: 'GET',
				                url: '/api/method010',
				                dataType: 'json',
				                data:data,
				                success: function(data){
				                	successCallback(data.obj);
				                },
				                error: errorCallback
				            });
		                },1000)
					}
				
					//列表菜单点击
					$('.nav_lists .ul li').click(function() {
						var index=$(this).index();
						if($(this).attr("class")=="active"){
							$(this).removeClass("active");
							window.listtype="";
							$("#worklist li").remove();
							//重置列表数据
							mescroll.resetUpScroll();
							return false; 
						}
						if(index==1){
				            window.listtype="life";
				            $("#worklist li").remove();
				           mescroll.resetUpScroll();
				        }else if(index==2){
				            window.listtype="salary";
				            $("#worklist li").remove();
				            mescroll.resetUpScroll();
				        }else{//城市和职位信息跳转
				        	$.cookie('selfindex', index);
				        	window.location.href="/api/method062 "
						}
					})
				
				});
				
				//进入详情
				$("#worklist").on("click","text",function(){ 
					window.location.href='/share/workdetail2.jsp?productid='+$(this).data("id")+"&userid=<%=userid %>&subsource=<%=subsource %>&sharephone=<%=sharephone %>&openid=<%=openid %>";
				})
				
				//报名
				$("#worklist").on("click",".fl",function(){ 
					window.productid=$(this).data("productid");
					mui("#signmodal").popover("show");
				}) 
				//确认报名
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
					data.productid=window.productid;
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
			
				//进入购买会员页面
				$("#topayman").click(function(){
					window.location.href='/api/method061?userid=<%=userid %>&openid=<%=openid %>&subsource=<%=subsource %>&sharephone=<%=sharephone %>';
				})
		</script>
	</body>

</html>