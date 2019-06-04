<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>처음 페이지</title>
</head>
<body>
<form method="post" action="/mem/login">
<table border="1">
	<tr>
		<th>아이디</th>
		<td><input type="text" name="id" id="miId"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="miPwd" id="miPwd"></td>
	</tr>
	<tr>
		<td colspan="2"><button>로그인</button></td>
	</tr>
</table>
</form>

<button onclick="singup()">회원가입</button>
<script >
function singup(){
	location.href="/member/sign.jsp";
}

</script>
</body>
</html>