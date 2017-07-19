<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<form>
이름: ${member.name}<BR/> 
ID : ${member.id}<BR/>
비밀번호 : ${member.passwd}<BR/>
주소 : ${member.addr}<BR/>
자기소개<br>
${member.memo}

</form>