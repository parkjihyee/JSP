<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empInsert.jsp</title>
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
사원 등록	
	<form action="insertEmp" name="empFrm" >
	<label for="empId">사원번호</label>
	<input type="text" name="empId" id="empId"><br> 
	
	<label for="empName">사원이름</label>
	<input type="text" name="empName" id="empName"><br>
	
	<label for="empEmail">이메일</label>
	<input type="text" name="empEmail" id="empEmail"><br>
	
	<label for="hireDate">입사일</label>
	<input type="date" name="hireDate" id="hireDate"><br>
	
	<input type="submit" value="저장"> <br>
	</form>

</body>
</html>