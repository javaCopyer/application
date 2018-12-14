<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.muteng.dgjs.common.utils.PasswordED" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    PasswordED passwordED =new PasswordED();

    String pass = request.getParameter("pass");
    String depass=passwordED.decPassword(pass);
    System.out.println("jsp   pass="+pass);
    System.out.println("jsp   depass="+depass);
    String appid="wx4797099c5b4642a1";

    //获取cookie对象
    Cookie[] cookies = request.getCookies();

    if (null != cookies && cookies.length > 0) {
        for (Cookie c : cookies) {
            if ("openid".equals(c.getName())) {
                System.out.println("find cookie");
                String backpass = passwordED.encPassword("{"+ passwordED.decPassword(pass)+(",openid=" + "\""+c.getValue())+"\""+"}");
                System.out.println("jsp  backpass="+backpass);
                String producturl = "http://workbazaar.lanlingzhifu.cn/api/method058?backpass=" + URLEncoder.encode(backpass, "UTF-8");
                String enproducturl=URLEncoder.encode(producturl, "UTF-8");
                response.sendRedirect( "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid + "&redirect_uri=" + enproducturl + "&response_type=code&scope=snsapi_base" + "&state=" + 1 + "#wechat_redirect");
                break;
            } else {
                System.out.println("not find cookie");
                String backpass = passwordED.encPassword("{"+depass+"}");
                String producturl = "http://workbazaar.lanlingzhifu.cn/api/method054?backpass=" +URLEncoder.encode(backpass, "UTF-8");
                String enproducturl=URLEncoder.encode(producturl, "UTF-8");
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid + "&redirect_uri=" + enproducturl + "&response_type=code&scope=snsapi_userinfo" + "&state=" + 1 + "#wechat_redirect");
                break;
            }
        }
    } else {
        String backpass = passwordED.encPassword("{"+depass+"}");
        String producturl = "http://workbazaar.lanlingzhifu.cn/api/method054?backpass=" +URLEncoder.encode(backpass, "UTF-8");
        String enproducturl=URLEncoder.encode(producturl, "UTF-8");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid + "&redirect_uri=" + enproducturl + "&response_type=code&scope=snsapi_userinfo" + "&state=" + 1 + "#wechat_redirect");
    }


%>


<html>
<head>

</head>
<body>
</body>
</html>