<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加员工</title>
<style type="text/css">
   table{
      width:240px;
      border:3px solid #ccc;
      border-collapse: collapse;/*表格内部单元格合并*/
      margin: auto;/*表格居中*/
      margin-top: 20px;
   }
   teble td{
       border:1px solid #ccc;
   }
   a{
      color:black;
      text-decoration: none;
   }
   a:hover{
      color:red;
   }
</style>
</head>
<body>
   <h2 align="center">添加员工</h2>
   <!-- 使用spring表单标签,表单中必须要有modelAttribute -->
      <form:form modelAttribute="emp"
       action="${pageContext.request.contextPath }/emp"
        method="POST" name="addForm">
        <%-- <p align="center">
          <!-- 显示  *显示所有  ename显示姓名 -->
          <form:errors path="*" cssStyle="color:red"/>
        </p> --%>
        <form:hidden path="empno"/>
        <table>
          <tr>
             <td>姓名</td>
             <td>
             <form:input path="ename"/>
            <%--  <form:errors path="ename"/> --%>
             </td>
          </tr>
          <tr>
             <td>性别</td>
             <td>
               
               <!-- <label>
                 <input type="radio" name="esex" value="男"checked/>男
               </label>
               <label>
                 <input type="radio" name="esex" value="女"/>女
               </label> -->
               <form:radiobuttons path="esex" items="${map}"/>
               <%-- <form:errors path="esex"/> --%>
             </td>
         </tr>
         <tr>
             <td>年龄</td>
             <td>
             <form:input path="eage"/>
             <%-- <form:errors path="eage"/> --%>
             </td>
             
         </tr>
         <tr>
             <td>薪资</td>
             <td>
             <form:input path="esalary"/>
             <%-- <form:errors path="esalary"/> --%>
             </td>
         </tr>
         <tr>
             <td>部门</td>
             <td>
               <!-- <select name="deptno">
                 <option value="d001">java开发部</option>
                 <option value="d002">市场部</option>
                 <option value="d003">后勤部</option>
                 <option value="d004">企划部</option>
                 itemLabel给用户看的  应该是dname
                 itemValue提交给后台服务器的数据 应该是deptno
               </select> -->
               <form:select path="dept.deptno" items="${depts}" itemLabel="dname" itemValue="deptno">
               </form:select>
               <%-- <form:errors path="deptno"/> --%>
             </td>
         </tr>
         <tr>
             <td>经理</td>
             <td>
               <!-- 
               select * from t_emp
               where empno in(select mgrno from t_emp) 
               select distinct,m.*
               from t_emp e inner join t_emp m on e.mgrno=m.empno
               
                -->
               <!-- <select name="mgrno">
                 <option value="e001">熊大</option>
                 <option value="e002">熊二</option>
                 <option value="e009">邓九</option>
                 <option value="e010">常十</option>
                 <option value="e011">萧十一郎</option>
               </select> -->
               <form:select path="mgr.empno" items="${mgrs}" itemLabel="ename" itemValue="empno">
               </form:select>
               <%-- <form:errors path="mgrno"/> --%>
             </td>
         </tr>
         <tr align="center">
           <td colspan="2">
             <input type="submit" value="提交" name="submit">
             <input type="button" value="重置" onclick="doReset()" >
             <a href="${pageContext.request.contextPath }/emp/conditionList">返回</a>
           </td>
         </tr>
       </table>
     </form:form>
     <script type="text/javascript">
       //性别默认选中男
       var ck=document.getElementById("esex2");
       ck.checked=true;
       //表单重置函数
       function doReset(){
    	   document.addForm.ename.value="";
    	   var ck=document.getElementById("esex2");
           ck.checked=true;
    	   document.addForm.eage.value="";
    	   document.addForm.esalary.value="";
    	   document.addForm.deptno.value="d001";
    	   document.addForm.mgrno.value="e001";
       }
     </script>
</body>
</html>