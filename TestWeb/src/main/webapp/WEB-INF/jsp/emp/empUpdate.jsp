<%@page import="co.micol.prj.emp.EmpVO"%>
<%@page import="co.micol.prj.dept.DeptVO"%>
<%@page import="co.micol.prj.emp.JobsVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empInsert.jsp</title>
<script>
function validateForm(){
	if(window.document.frm.employeeId.value == ""){
		alert("사번 입력");
		frm.employeeId.focus();
		return false;
	}
	
	if(frm.lastName.value == ""){
		alert("이름 입력");
		frm.lastName.focus();
		return false;
	}
	
	if(frm.email.value == ""){
		alert("이메일 입력");
		frm.email.focus();
		return false;
	}
	var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(emailRule.test(frm.email.value)==false){
		alert("이메일 방식");
		frm.email.focus();
		return false;
	}
	
	
	if(frm.jobId.value == ""){
		alert("부서 입력");
		frm.jobId.focus();
		return false;
	}

	
	if(frm.hireDate.value == ""){
		alert("입사일 입력");
		frm.hireDate.focus();
		return false;
	}
	return true; // 하나라도 값에 이상이 있으면 false가 나온다?
}
</script>

<style>
	form > label{
		display: inline-block;
		width: 20%;
		background-color: beige;
		text-align: center;
	}
	

</style>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" /><br>
<h4>사원 등록</h4>	
<%
	EmpVO vo = (EmpVO)request.getAttribute("emp");
%>
	<form name="frm" action="empInsert" method="post" onsubmit="return validateForm()">
	<label for="empId">사원번호</label>
	<input type="text" name="employeeId" id="empId" readonly="readonly" value=" ${vo.getEmployeeId()}"><br> 
	
	<label for="empName">사원이름</label>
	<input type="text" name="lastName" id="empName" value="${vo.getLastName()}"><br>
	
	<label for="empEmail">이메일</label>
	<input type="text" name="email" id="empEmail" value="${vo.getEmail()}"><br>
	
	<label for="jobId">부서이름</label>
	<select name="jobId">
	<% ArrayList<JobsVO> list = (ArrayList<JobsVO>)request.getAttribute("jobs");
	   for(JobsVO jobs : list) { %>
	   <option value="<%=jobs.getJobId()%>"> <%=jobs.getJobTitle()%>
	   <%--> <%if(jobs.getJobId().equals(vo.getJobId()) ){ selected="selected" <%}%>> --%> 
	<% } %>
	</select><br>
	
	
	<label for="hireDate">입사일</label>
	<input type="date" name="hireDate" id="hireDate" value="<%=vo.getHireDate()%>"><br>
	
	   <c:forEach items="${depts}" var="dept">
	   <input type="radio" name="departmentId" value="${dept.getDepartmentId()}"
	   <c:if test="dept.getDepartmentId() == vo.getDepartmentId()">checked="checked"</c:if>> 
				  ${dept.getDepartmentName()}
		</c:forEach>
		
	<button>저장</button>
	<button type="button" onclick="empDelete()">삭제</button>
	</form>
	<script>
	function empDelete(){
		location.href="empDelete?employeeId=<%=vo.getEmployeeId()%>";
	}
	
		document.querySelector("[name=departmentId][value='<%=vo.getDepartmentId()%>']").checked=true;
		document.getElementsByName("jobId")[0].value="<%=vo.getJobId()%>";
	</script>
</body>
</html>