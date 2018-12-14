<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<head>
		<meta charset="utf-8" />
		<title>会员权益</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" type="text/css" href="/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="/css/css.css" />
		<script type="text/javascript" src="/js/jquery-2.1.1.js" ></script>
	</head>

	<body class="bobg">
		<div class="zf_sy">
			<p>支付剩余时间</p>
			<span>请在15分钟内完成支付</span>
			<em id="btn"></em>
		</div>
		<div class="zf_lable">
			<p>会员服务优惠</p>
			<span>￥365元</span>
		</div>
		<div class="lable_fil">
			<ul>
				<li>
					<p>选择支付方式</p>
				</li>
				<li>
					<img src="/images/img/weixins.png" class="logs"/>
					<p>微信</p>
					<img src="/images/img/xuanzh.png" class="actsa"/>
				</li>
				<li>
					<img src="/images/img/zhifubaox.png" class="logs"/>
					<p>支付宝</p>
					<img src="/images/img/xuanzh.png" class="actsa"/>
				</li>
			</ul>
		</div>
		<div class="pays"><a href="#">立即支付</a></div>
		<script type="text/javascript" src="/js/rem.js"></script>
		<script type="text/javascript" src="/js/js.js"></script>
		<script>
        var x = 15,interval;
        window.onload = function() {
            var d = new Date("1111/1/1,0:" + x + ":0");
            interval = setInterval(function() {
                var m = d.getMinutes();
                var s = d.getSeconds();
                m = m < 10 ? "0" + m : m;
                s = s < 10 ? "0" + s : s;
                btn.innerHTML = m + ":" + s;
                if (m == 0 && s == 0) {
                    clearInterval(interval);
                    return;
                }
                d.setSeconds(s - 1);
            }, 1000);
        }
    </script>
	</body>

</html>