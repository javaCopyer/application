<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <link href="/css/mui.min.css" rel="stylesheet" />
        <title>报名成功</title>
    </head>
    <body style="background: #fff;">
    	<div style="width: 100%;text-align: center;">
	    	<img style="margin: 120px 0 20px 0;width: 40%;" src="/images/banner.png" />
	    	<div style="color: #E83525;font-size: 16px;">报名成功！</div>
	    	<div style="color: #666666;margin-top: 10px;font-size: 12px;">请您保持电话畅通，稍后您的专<br />属客服会跟您取得联系。</div>
	    	<input onclick="todownload()" style="width: 45%;height: 40px;border: 0;margin-top: 40px; border-radius: 20px;background: #E83525;color: #fff;font-size: 14px;" type="button"  value="下载APP" />
    	</div>
    	<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
		<script src="/js/mui.min.js"></script>
		<script>
			function todownload(){
				window.location.href='/share/download.html'
			}
		</script>
 	</body>
</html>