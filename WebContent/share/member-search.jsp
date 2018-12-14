<%@ page import="java.util.Map" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="/css/mui.min.css" rel="stylesheet" />
		<link href="/css/mui.indexedlist.css" rel="stylesheet" />
	    <link rel="stylesheet" type="text/css" href="/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="/css/css.css" />
	    <style>
	    	.mui-bar {
				-webkit-box-shadow: none;
				box-shadow: none;
			}
			.mui-indexed-list-bar a,.mui-indexed-list-bar{background: #fff;}
			.mui-table-view-cell{padding:10px 20px;}
			.mui-indexed-list-group{background: #fff;color: #333333;}
			.mui-table-view-cell:after,.mui-table-view-divider:after,.mui-table-view:before,.mui-table-view-divider:before{background: #fff;}
			.mui-table-view-cell div{width: 23.5%;height: 32px;font-size: 14px; float: left; background: #EEEDED;line-height: 32px;text-align: center;color: #333333;border-radius:4px;margin:0 2% 5px 0;}
			.mui-table-view-cell div:nth-child(4n){margin-right:0}
			.mui-table-view-cell .active{background: #FDEAEA;color: #E83525;}
			.mui-table-view-cell.mui-active{background: 0;}
			.work_btn{width: 100%;height: 40px;line-height: 40px;text-align: center;background: #fff;color: #fff;position: absolute;bottom: 0;z-index: 9999;}
			.work_btn a{float: left;width: 50%;height: 40px;color: #333;}.work_btn .fr{background: #E83525;color: #fff;text-align: center;}
			#mui_position_list .cs{width: 100%;height: auto;padding: 15px 4% 0 4%;overflow: auto;}
			#mui_position_list .cs p{width: 100%;height: 30px;font-size: 14px;color: #333333;}
			#mui_position_list .cs li{width: 23%;height: 32px;font-size: 14px; float: left; background: #EEEDED;line-height: 32px;text-align: center;color: #333333;border-radius:4px;margin:0 2% 5px 0;}
			#mui_position_list .cs .active{background: #FDEAEA;color: #E83525;}
			.modalcont3{width: 100%;height: 110px;background-color: #fff;list-style: none;padding: 0;}
			.modalcont3 li{float: left;text-align: center;width: 50%;font-size: 14px;}
			.modalcont3 li img{width: 45px;margin: 20px 0 3px 0;}img{display: inline-block;}
			.mui-backdrop-action.mui-backdrop {background-color: rgba(0,0,0,.6);}
	    </style>
	</head>

	<body>
		<div style="top: 70px;z-index: 99;" class="b_bgs "></div><!--背景-->
			<div style="padding: 0;" class="mui-content"> 
		<!--城市列表-->
				<div style="z-index: 999;background: #fff;;" id='mui_city_list' class="mui-indexed-list">
					<div style="display: none;" class="mui-indexed-list-search mui-input-row mui-search">
						<input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索机场">
					</div>
					<div id="mui-indexed-list-bar" class="mui-indexed-list-bar">
						
					</div>
					<div class="mui-indexed-list-alert"></div>
					<div style="padding-top: 20px;" class="mui-indexed-list-inner">
						<div class="mui-indexed-list-empty-alert">没有数据</div>
						<ul style="padding-bottom: 40px;" id="city_list" class="mui-table-view">
							<!--<li class="mui-table-view-divider mui-indexed-list-group">热门城市</li>
							<li data-group="A" class="mui-table-view-cell mui-indexed-list-item">
								<div class="active">不限</div><div>2</div><div>2</div><div>4</div><div>5</div><div>6</div><div>7</div><div>8</div>
							</li>-->
						</ul>
					</div>
					<div class="work_btn">
						<a onclick="searchreset()" class="fl">取消</a>
						<a onclick="searchcitysubmit()" class="fr">确定</a>
					</div>
				</div> 
			<!--职位信息-->  
				<div style="z-index: 999;" id='mui_position_list' class="mui-indexed-list">
					<div style="overflow: auto;height: 100%; padding-bottom: 50px;">
						<div class="cs">
							<p>年龄</p>
							<ul id="age">
								
							</ul>
						</div>
						<div class="cs">
							<p>性别</p>
							<ul id="sex">
								<li>不限</li>
								<li>男</li>
								<li>女</li>
							</ul>
						</div>
						<div class="cs">
							<p>民族</p>
							<ul id="minzu">
								
							</ul>
						</div>
						<div class="cs">
							<p>工种</p>
							<ul id="position">
								
							</ul>
						</div>
						<div class="cs">
							<p>纹身烟疤</p>
							<ul id="tattoo"> 
								
							</ul>
						</div>
					</div>
					<div class="work_btn">
						<a onclick="searchreset()" class="fl">取消</a>
						<a onclick="searchsubmit()" class="fr">确定</a>
					</div>
				</div>
			</div>
		<script src="/js/mui.min.js"></script>
		<script src="/js/mui.indexedlist.js"></script>
		<script type="text/javascript" src="/js/rem.js"></script>
		<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
		<script type="text/javascript" src="/js/jquery.cookie.js" ></script>
		<script type="text/javascript">
			mui.init({
				swipeBack:true
			});
			
			function muiajax_callback(data,url,type,callback) {
    			$.ajax({
    				type:type,
    				url:url,
    				data:data,
    				error:function(request){
    					alert("系统错误");
    				},
    				success:function(data){
    					callback(data)
    				}
    			})
    		}
			//加载数据
			$(function(){ 
				citylistfun();//加载 城市数据
				//城市重新加载
				var header = document.querySelector('header.mui-bar');
				var list = document.getElementById('mui_city_list');
				var cityheight= window.screen.availHeight-150;
				$("#mui_city_list").css("height",cityheight+"px")
				$("#mui_position_list").css("height",cityheight+"px");
				//create
				window.indexedList = new mui.IndexedList(list);
				
				var selfindex=$.cookie('selfindex');
				//判断当前显示
				$("#mui_city_list").hide();  
				$("#mui_position_list").hide();
				if(selfindex==0){
					$("#mui_city_list").show(); 
				}else if(selfindex==3){
					$("#mui_position_list").show();
				}
				  
				positionsearch();//查询职位信息
			})
			
			//加载城市数据
			function citylistfun(){  
				muiajax_callback('',"/api/method037","get",function(data){
					console.log(JSON.stringify(data))
					var cityid=$.cookie("search_cityid")==null?[]:$.cookie("search_cityid").split(",");
					var html='';
					html+='<li class="mui-table-view-divider mui-indexed-list-group">热门城市</li>'
					html+='<li data-group="A" class="mui-table-view-cell mui-indexed-list-item">'
					for (var j=0;j<data.obj[data.obj.length-1].热门.length;j++) {
						var htmls='<div ';
						for (var q=0;q<cityid.length;q++) {
							if(cityid[q]==data.obj[data.obj.length-1].热门[j].split("-")[0]){
								htmls='<div class="active" '
							}
						}
						html+=htmls+'data-id="'+data.obj[data.obj.length-1].热门[j].split("-")[0]+'">'+data.obj[data.obj.length-1].热门[j].split("-")[1]+'</div>'
					}
					html+='</li>'
					for (var i=0;i<data.obj.length-1;i++) {
						$("#mui-indexed-list-bar").append('<a>'+data.obj[i].title.toUpperCase()+'</a>')
						html+='<li class="mui-table-view-divider mui-indexed-list-group">'+data.obj[i].title.toUpperCase()+'</li>'
						html+='<li data-group="'+data.obj[i].title.toUpperCase()+'" class="mui-table-view-cell mui-indexed-list-item">'
						for (var l=0;l<data.obj[i].content.length;l++) {
							var htmls='<div ';
							for (var k=0;k<cityid.length;k++) {
								if(cityid[k]==data.obj[i].content[l].split("-")[0]){
									htmls='<div class="active" '
								}
							}
							html+=htmls+'data-id="'+data.obj[i].content[l].split("-")[0]+'">'+data.obj[i].content[l].split("-")[1]+'</div>' 
						}
						html+='</li>'
					}
					$("#city_list").html(html);
				})
			}
			//点击城市
			$("#city_list").on("click","div",function(){
				var nowtext=$(this).text();
				$("#city_list").find("div").each(function(){
					if($(this).text()==nowtext){
						$(this).toggleClass('active');
					}
				})
			})
			//城市信息确认提交
			function searchcitysubmit(){
				var cityid=[];
				$("#city_list").find(".active").each(function(){
					cityid.push($(this).data("id"))
				})
				$.cookie('search_cityname', $("#city_list").find(".active").text());
				$.cookie('search_cityid', cityid.join(","));
				console.log($.cookie('search_cityname')+"&&&&&"+$.cookie('search_cityid')+"%%%%%")
				window.history.go(-1);
			}
			
			//职位信息查询
			function positionsearch(){ 
				var positiondata=null;
				if($.cookie("search_positionid")!=null){
					positiondata=JSON.parse($.cookie("search_positionid"))
				}
				//性别
				if(positiondata==null||positiondata.sex==null){ 
					$("#sex").find("li:nth-child(1)").addClass("active");
				}else{
					$("#sex").find("li").each(function(){
						if($(this).text()==positiondata.sex){
							$(this).addClass("active");
						}
					})
				}  
				muiajax_callback('','/api/method049',"get",function(data){ 
					//年龄
					var agedata=data.obj.年龄范围;
					var agehtml='';
					for (var i=0;i<agedata.length;i+=2) {
						if(positiondata==null||positiondata.age==null){
							agehtml+='<li class="'+(agedata[i]=="不限"?"active":"")+'" data-id="'+agedata[i+1]+'">'+agedata[i]+'</li>'
						}else{  
							var htmls='<li '
							if(positiondata.age.indexOf(agedata[i])!=-1){
								htmls+='<li class="active" '
							}
							agehtml+=htmls+'data-id="'+agedata[i+1]+'">'+agedata[i]+'</li>'
						}
					}
					$("#age").html(agehtml);
					//民族
					typecallback(data.obj.民族,positiondata==null?'':positiondata.nation,function(data){
						$("#minzu").html(data);
					})
					//用工
					typecallback(data.obj.用工性质,positiondata==null?'':positiondata.WorkNature,function(data){
						$("#position").html(data);
					})
					//纹身
					typecallback(data.obj.纹身烟疤,positiondata==null?'':positiondata.tattoo,function(data){
						$("#tattoo").html(data);
					}) 
				})
			}
			function typecallback(data,checkdata,callback){  
				console.log(checkdata)
				var html='';
				if(checkdata==null||checkdata==''){ 
					html+='<li class="active" data-id="-1">不限</li>';
					for (var i=0;i<data.length;i+=2) { 
						html+='<li data-id="'+data[i+1]+'">'+data[i]+'</li>'
					}
				}else{
					html+='<li data-id="-1">不限</li>';
					for (var i=0;i<data.length;i+=2) { 
						var htmls='<li '
						for (var j=0;j<checkdata.split(",").length;j++) {
							if(checkdata.split(",")[j]==data[i+1]){
								htmls='<li class="active" '
							}
						}	
						html+=htmls+'data-id="'+data[i+1]+'">'+data[i]+'</li>'
					}
				}
				callback(html);
			}
			//职位信息点击
			$("#mui_position_list").on("click","li",function(){
				if($(this).text()=="不限"){
					$(this).addClass("active").siblings().removeClass("active");
				}else{
					if($(this).parent().attr("id")=="sex"){
						$(this).addClass("active").siblings().removeClass("active")
					}else{
						$(this).toggleClass('active').parent().find("li:nth-child(1)").removeClass("active");
					}
				}
			})
			
			//职位信息确认提交信息
			function searchsubmit(){
				//年龄
				var age=[];
				$("#age").find(".active").each(function(){
					age.push($(this).text());
				})
				//性别
				var sex=[];
				$("#sex").find(".active").each(function(){
					sex.push($(this).text());
				})
				//少数民族
				var minzu=[];
				$("#minzu").find(".active").each(function(){
					minzu.push($(this).data("id"));
				})
				//工种
				var position=[];
				$("#position").find(".active").each(function(){
					position.push($(this).data("id"));
				})
				//纹身
				var tattoo=[];
				$("#tattoo").find(".active").each(function(){
					tattoo.push($(this).data("id"));
				})
				var data={};
				if(age.join("")!="不限"&&age.join("")!=""){
					data.age=age.join("");
				}
				if(sex.join(",")!="不限"&&sex.join("")!=""){
					data.sex=sex.join(",");
				}
				if(minzu.join(",")!="-1"&&minzu.join("")!=""){
					data.nation=minzu.join(",");
				}
				if(position.join(",")!="-1"&&position.join("")!=""){
					data.WorkNature=position.join(",");
				}
				if(tattoo.join(",")!="-1"&&tattoo.join("")!=""){
					data.tattoo=tattoo.join(",");
				}
				console.log(JSON.stringify(data));
				$.cookie("search_positionid",JSON.stringify(data)); 
				window.history.go(-1);
			}
			//取消
			function searchreset(){
				window.history.go(-1);
			}
		</script>
	</body>

</html>