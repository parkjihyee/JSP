<%@page import="co.micol.prj.emp.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<h3>사원목록</h3>
<a href="/TestWeb/empInsert">사원등록</a><br>

<form>
	<input name="departmentId">
	<button>검색</button>
</form>
 
	<table border>
		<thead><tr><th>사번</th><th>이름</th><th>이메일</th><th>직무</th><th>입사년도</th></tr>
		</thead>
		<tbody>
<c:forEach var="vo" items="${list}"> <%--주석 --%>
			<tr>
				<td>${vo.employeeId}</td>
				<td><a href="empUpdate?employeeId=${vo.employeeId}">${vo.lastName}</a></td>
				<td>${vo.email}</td>
				<td>${vo.jobId}</td>
				<td>${vo.hireDate}</td>
			</tr>
</c:forEach>		
		</tbody>
	</table>
</body>
</html>