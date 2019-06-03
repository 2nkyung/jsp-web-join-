<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member" method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="miName" id="miName"><br>
		아이디 : <input type="text" name="miId" id="miId"><br>
		 비밀번호: <input type="password" name="miPwd" id="miPwd"><br> 
		 이메일: <input type="email" name="miEmail"><br> 
		 당신의 성별 : 남<input type="radio" name="miTrans" value="1"> ,여<input type="radio"
			name="miTrans" value="2"><br> 
			생년월일 : <input type="text"	name="miBirth"><br> 
			<br> 우편번호 : <input type="text" name="miZipcode"><br> 
			주소 : <input type="text"	name="miAddr1"><br> 
			상세주소 : <input type="text"	name="miAddr2"><br> 
			nick : <input type="text" name="miNick"><br>
			 etc : <input type="text" name="miEtc"><br>
		file : <input type='file' name="miImg">
		<button type="submit">회원 가입</button>

	</form>

</body>
</html>