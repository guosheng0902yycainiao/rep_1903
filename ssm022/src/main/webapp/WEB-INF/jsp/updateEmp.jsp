<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工信息</title>
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
  <!-- 在页面渲染数据到一个table中 -->
   <h2 align="center">修改员工信息</h2>
      <form:form modelAttribute="emp" name="updateForm"
      action="${pageContext.request.contextPath }/emp" method="POST">
        <input type="hidden" name="_method" value="PUT"/>
        <p align="center">
          <form:errors path="*" cssStyle="color:red">
          </form:errors>
        </p>
        <form:hidden value="${emp.empno}" path="empno"/>
        <table>
          <tr>
             <td>姓名</td>
             <td><form:input path="ename" value="${emp.ename}"/></td>
          </tr>
          <tr>
             <td>性别</td>
             <td>
               <!-- 依据模型属性自动选择 -->
               <form:radiobuttons path="esex" items="${map}"/>
               <%-- <label>
                 <input type="radio" name="esex" value="男" <c:if test="${e.esex eq '男'}">checked</c:if>/>男
               </label>
               <label>
                 <input type="radio" name="esex" value="女" <c:if test="${e.esex eq '女'}">checked</c:if>/>女
               </label> --%>
             </td>
         </tr>
         <tr>
             <td>年龄</td>
             <td><form:input path="eage" value="${emp.eage}"/></td>
         </tr>
         <tr>
             <td>薪资</td>
             <td><form:input path="esalary" value="${emp.esalary}"/></td>
         </tr>
         <tr>
             <td>部门</td>
             <td>
               <form:select path="dept.deptno" items="${depts}" itemLabel="dname"
               itemValue="deptno">
               </form:select>
               <%-- <select name="deptno">
                 <option value="d001" <c:if test="${e.deptno eq 'd001'}">selected</c:if>>java开发部</option>
                 <option value="d002" <c:if test="${e.deptno eq 'd002'}">selected</c:if>>市场部</option>
                 <option value="d003" <c:if test="${e.deptno eq 'd003'}">selected</c:if>>后勤部</option>
                 <option value="d004" <c:if test="${e.deptno eq 'd004'}">selected</c:if>>企划部</option>
                 <option value=null <c:if test="${e.deptno==null}">selected</c:if>></option>
               </select> --%>
             </td>
         </tr>
         <tr>
             <td>经理</td>
             <td>
               <form:select path="mgr.empno" items="${mgrs}" itemLabel="ename"
               itemValue="empno">
               </form:select>
               <%-- <select name="mgrno">
                 <option value="e001" <c:if test="${e.mgrno eq 'e001'}">selected</c:if>>熊大</option>
                 <option value="e002" <c:if test="${e.mgrno eq 'e002'}">selected</c:if>>熊二</option>
                 <option value="e003" <c:if test="${e.mgrno eq 'e003'}">selected</c:if>>张三</option>
                 <option value="e004" <c:if test="${e.mgrno eq 'e004'}">selected</c:if>>李四</option>
                 <option value="e007" <c:if test="${e.mgrno eq 'e007'}">selected</c:if>>孙七</option>
                 <option value="e009" <c:if test="${e.mgrno eq 'e009'}">selected</c:if>>邓九</option>
                 <option value="e010" <c:if test="${e.mgrno eq 'e010'}">selected</c:if>>常十</option>
                 <option value="e011" <c:if test="${e.mgrno eq 'e011'}">selected</c:if>>萧十一郎</option>
                 <option value=null <c:if test="${e.mgrno==null}">selected</c:if>></option>
               </select> --%>
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
          function doReset(){
  	        document.updateForm.ename.value="";
  	        var ck=document.getElementById("esex2");
            ck.checked=true;
  	        document.updateForm.eage.value="";
  	        document.updateForm.esalary.value="";
  	        document.updateForm.deptno.value="d001";
  	        document.updateForm.mgrno.value="e001";
         }
     </script>
</body>
</html>