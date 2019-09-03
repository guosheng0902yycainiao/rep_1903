<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
</head>
<body>

欢迎你,<shiro:principal/>!<br/>

<shiro:hasRole name="管理员">
         具备管理员角色才能看到这句话<br/>
</shiro:hasRole>

<shiro:hasRole name="普通用户">
        具备普通用户角色才能看到这句话<br/>
</shiro:hasRole>

<shiro:hasPermission name="emp:create">
        具备emp:create权限才能看到这句话<br/>
</shiro:hasPermission>

<shiro:hasPermission name="emp:update">
        具备emp:update权限才能看到这句话<br/>
</shiro:hasPermission>
<br/>

<shiro:hasPermission name="{emp:update,emp:*}">
        具备emp:update,emp:*权限才能看到这句话<br/>
</shiro:hasPermission>

<a href="teacher">需要emp:create权限才能访问</a><br/>
<a href="logout">安全退出</a>


</body>
</html>