<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript" src="/js/jquery.min.js"></script>
</head>
<body>
   <h1>登录界面</h1>
   <font color="red">${errorMsg}</font>
   <form action="${pageContext.request.contextPath}/user/login" method="POST">
            用户:<input name="username" id="username"/><br/>
            密码:<input name="password" type="password" id="password"/><br/>
     <label>
         <input type="checkbox" name="remenmberMe" value="1" class="cookiePass"/>记住密码
     </label>
     <input type="submit" value="登录"/>
     <input type="button" value="注册" onclick="enroll()"><br/>
   </form>

<script type="text/javascript">
  /*跳转到enroll(注册页面)*/
  function enroll(){
    var url="${pageContext.request.contextPath}/user/toRegister";
    window.location.href=url;
  }
  function getCookie(){
	  var loginCode=$.cookie("login_code");//获取cookie中的用户名
	  var pwd = $.cookie("pwd");//获取cookie中的登录密码
	  alert(loginCode);
	  alert(pwd);
	  $("username").val(loginCode);
	  //把密码填充到密码文本框
	  $("password").val($.base64.decode(pwd));
	  //把 "记住密码"复选框勾选住
	  $(".cookiePass").prop("checked",true);
  }
  $(function(){
	  getCookie();
  });


</script>

</body>
</html>