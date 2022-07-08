<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${list }" var="m"> <!-- 이 객체가 가지고있는 한 행을 m이라고 읽겠다 -->
${m.memberId } : ${m.memberName } : ${m.memberAuthor }<br> <!-- vo객체에 선언한대로 -->
	</c:forEach>
</body>
</html>