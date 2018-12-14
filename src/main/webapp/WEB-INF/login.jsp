<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<%
	String error = (String)request.getAttribute("error");
	String zhmc = (String)session.getAttribute("zhmc");
	String zhmm = (String)session.getAttribute("zhmm");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AI Factory Monitoring Platform</title>
<base href="<%=basePath%>">
 <link rel="stylesheet" href="assets/css/reset.css"  type="text/css" >
 <link rel="stylesheet" href="assets/css/supersized.css" type="text/css">
 <link rel="stylesheet" href="assets/css/style.css" type="text/css">
 <style> 
/* .xtqh{ background:#000;padding:5px;color:#F00; 
filter:alpha(Opacity=50);-moz-opacity:0.50;opacity: 0.50
}  */
.div-a{ background:#000;padding:5px;color:#F00; width:100%;height:100%;position:absolute;
filter:alpha(Opacity=20);-moz-opacity:0.20;opacity: 0.20}   

/* CSS注释说明：这里对CSS代码换行是为了让代码在此我要中显示完整，换行后CSS效果不受影响 */ 
</style> 
</head>
<body>
	<div class="page-container"  style="background-size:cover;clear:both;position:absolute;left:0px; top:0px;"></div>	 <!-- 第一层 -->	
	<div class="div-a" style="clear:both;"> </div>		 <!-- 第二层 -->	
	<div style="clear:both;position:absolute;top: 40%;left: 50%;width:100%; transform:translate(-50%,-50%); text-align: center;"> <!-- 第三层 -->	
			<h1 style="color: white">智 慧 工 厂 监 测 平 台</h1>
			<br>
			<span style="color: white;font-size: 20px">AI Factory Monitoring Platform</span>
			<form action="login" method="post">
<!-- 				<div class="error"> -->
					<span style="color: red;font-size: 20px;">${error }</span><br/>
					<span style="color: red;font-size: 20px;">${errorZW }</span>
<!-- 				</div> -->
				<input type="text" name="zhbm" id="zhbm" class="username" placeholder="用户名(Username)">
				<input type="password" name="zhmm" id="zhmm" class="password" placeholder="密码(Password)">
				<button type="submit">登录(Login)</button>
			</form>
	</div>					
</body>
<script src="assets/js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script src="assets/js/supersized.3.2.7.min.js" type="text/javascript"></script>
<script src="assets/js/supersized-init.js" type="text/javascript"></script>
<script src="assets/js/scripts.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var zhmc = "<%=zhmc%>";
		var zhmm ="<%=zhmm%>";
		if(zhmc!=null&&zhmc!="" &&zhmc!="null"){
			$("#zhbm").val(zhmc);
			$("#zhmm").val(zhmm);
		}
	})
</script>
</html>