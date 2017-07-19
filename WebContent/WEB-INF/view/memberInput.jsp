<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri= "http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
</head>
<body>
<H4>회원가입</H4>
<FORM  ACTION="memProc.do"  METHOD="POST" enctype="multipart/form-data">
		<input type=radio name="type" value="${member.type}teacher">선생님<form:errors path="member.type"/>
		<input type=radio name="type" value="${member.type}student">학생<form:errors path="member.type"/><BR/>		
	이름: <INPUT TYPE=text NAME="name" value="${member.name}" > <form:errors path="member.name"/> <BR/> 
	ID : <INPUT TYPE=text NAME="id" value="${member.id}"> <form:errors path="member.id"/><BR/>
	비밀번호 : <INPUT TYPE=password NAME="pw" value="${member.pw}"> <form:errors path="member.pw"/> <BR/>
	전화번호 : <INPUT TYPE=text NAME="phone"  value="${member.phone}"> <form:errors path="member.phone"/><BR>
	이메일 : <INPUT TYPE=text NAME="mail"  value="${member.mail}"> <form:errors path="member.mail"/><BR>

	<INPUT TYPE="SUBMIT" VALUE="가입">
	<INPUT TYPE="RESET" VALUE="지우기">
</FORM>
</body>
</html>