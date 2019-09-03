<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<script src="/js/jquery.min.js" ></script>
<link rel="stylesheet" href="/css/list.css"/>
</head>
<body>
   <!-- 在页面渲染数据到一个table中 -->
   <h2 align="center">员工列表Windows</h2>
   <h3 align="center">欢迎<shiro:principal/></h3>
   <h3 align="center"><a href="${pageContext.request.contextPath}/user/logout">退出登录</a></h3>
   <shiro:hasPermission name="emp:add">
     <h3 align="center">
       <!-- /工程名/webdemo 
                      跳转到添加工程的页面
        -->
       <a href="${pageContext.request.contextPath }/emp/addEmpTo" >添加员工</a>
     </h3>
   </shiro:hasPermission>
   <!-- 使用js操作表单发送切换页面的请求 -->
   <div align="center">
     <form name="searchForm" action="${pageContext.request.contextPath }/emp/conditionList" method="post">
        <input name="pageNo" type="hidden" value="1"/>
        <input name="cd" value="${cd}"/>
        <input type="submit" value="搜索"/>
     </form>
   </div>
   <input type="hidden" name="empno" value="${e.empno}"/>
   <table>
      <tr>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>薪资</th>
        <th>部门</th>
        <th>经理</th>
        <th>操作</th>
      </tr>
      <c:forEach items="${pageBean.list}" var="e">
        <tr>
          <td>${e.ename}</td>
          <td>${e.esex}</td>
          <td>${e.eage}</td>
          <td>${e.esalary}</td>
          <td>${e.dept.dname}</td>
          <td>${e.mgr.ename}</td>
          <th>
            <shiro:hasRole name="管理员">
              <a href="javascript:delEmp('${e.empno}');">删除</a>
              <a href="${pageContext.request.contextPath }/emp/update?empno=${e.empno}&cd=${cd}&pageNo=${pageBean.pageNo}">修改</a>
            </shiro:hasRole>
          </th>
        </tr>
      </c:forEach>
   </table>
   <p align="center">
      <a href="javascript:changePage(${pageBean.first});"><input type="button" value="首页"/></a>
      <a href="javascript:changePage(${pageBean.previous});"><input type="button" value="上一页"/></a>
      <c:forEach begin="1" end="${pageBean.last}" var="no">
        <c:if test="${pageBean.pageNo==no}">
           <span class="on">
             <a href="javascript:changePage(${no});">${no}</a>
           </span>
        </c:if>
        <c:if test="${pageBean.pageNo!=no}">
          <span>
             <a href="javascript:changePage(${no});">${no}</a>
          </span>
        </c:if>
      </c:forEach>
      <a href="javascript:changePage(${pageBean.next});"><input type="button" value="下一页"/></a>
      <a href="javascript:changePage(${pageBean.last});"><input type="button" value="尾页"/></a>
   </p>
   <!-- restful删除请求表单 -->
   <div style="display: none">
      <form name="delForm" 
      action="${pageContext.request.contextPath }/emp/" method="POST">
      <input type="hidden" name="_method" value="DELETE"/>
      </form>
   </div>
   <div style='width:0;height: 0;display: none' id='dd'>
      
   </div>
   <script type="text/javascript">
      function changePage(no){
    	  //location.href="${pageContext.request.contextPath}/emp/list?pageNo="+no+"&pageSize=10";
    	  //修改发送的页码
    	  document.searchForm.pageNo.value=no;
    	  //发送请求
    	  document.searchForm.submit();
      }
      //删除员工
      function delEmp(empno){
    	 var url=document.delForm.action;
    	 document.delForm.action=url+empno;
    	 //提交表单
    	 document.delForm.submit();
      }
   </script>
   <script type="text/javascript">
   $(function(){
	      //var mm = $("<img src='${pageContext.request.contextPath}/pic/aaa.jpg'/>");
	      $("#dd").show();
	       $("#dd").animate({
	    	  width:"480px",
	    	  height:"480px",
	      },2000);
        });
   </script>
</body>
</html>