<%@page import="co.micol.prj.dept.DeptVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function validationForm(){
	if(frm.departmentId.value == ""){
		alert("부서 번호 입력");
		return;
	}
	frm.submit(); // form전송(=submit버튼 클릭)
}

</script>

</head>
<body>
<% DeptVO dept = (DeptVO)request.getAttribute("dept"); %>

<form name="frm" action="DeptUpdate" method="post">
	부서 번호<input name="departmentId" value="<%=dept.getDepartmentId()%>"><br>
	부서 명<input name="departmentName" value="<%=dept.getDepartmentName()%>">
	<button type="button" onclick="validationForm()">부서 등록</button>
</form>
</body>
</html>