<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
	<div><h1>로 그 인</h1></div>
	<div>
		<form id="frm" action = "memberLogin.do" method="post">
			<div>
				<table>
					<tr>
						<th width="150">아이디</th>
						<td width="200">
							<input type="text" id="memberId" name="memberId" 
							required="required" placeholder="Enter your Id">
					</tr>
					<tr>
						<th width="150">패스워드</th>
						<td width="200">
							<input type="password" id="memberPassword" name="memberPassword" 
							required="required" placeholder="Enter your Password">
					</tr>
				</table>
			</div><br>
			<div>
				<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">&nbsp;&nbsp;&nbsp;
				<input type="button" value="홈 가기" onclick="location.href='main.do'">&nbsp;&nbsp;&nbsp;
			</div>
		</form>
	</div>
</div>
</body>
</html>