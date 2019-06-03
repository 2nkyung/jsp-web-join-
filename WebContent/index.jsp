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
<form method="post" action="/">
<table border="1">
	<tr>
		<th>아이디</th>
		<td><input type="text" name="mi_id" id="mi_id"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="mi_pwd" id="mi_pwd"></td>
	</tr>
	<tr>
		<td colspan="2"><button>로그인</button></td>
	</tr>
</table>
<input type="hidden" name="cmd" value="login">
</form>
<a href="/views/member/join">회원가입</a>
</body>
</html>