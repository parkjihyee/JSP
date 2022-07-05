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
<form name="frm" action="DeptInsert" method="post">
	부서 번호<input name="departmentId"><br>
	부서 명<input name="departmentName">
	<button type="button" onclick="validationForm()">부서 등록</button>
</form>
</body>
</html>