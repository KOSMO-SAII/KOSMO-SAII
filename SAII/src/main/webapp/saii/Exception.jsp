<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.container{
	position:absolute;
	height:300px;
	width:300px;
	margin: -200px 0px 0px -200px;
	top:50%;
	left:50%;
	padding: 5px;
}	
</style>
<meta charset="UTF-8">
<title>내장객체 exception</title>
</head>
<body>
<div class="container">
<%
int status=response.getStatus();

if(status==404){//not found
	out.print("404 에러 발생");
	out.print("<br/>파일경로확인하세요");
}else if(status==405){//method not allowed
	out.print("405 에러발생");
	out.print("<br/>요청방식(method를 확인하세요)");
}else if(status==500){//Internal server error 
	out.print("500에러 발생");
	out.print("<br/>소스코드의 오류를 확인하세요");
}
%>
</div>
</body>
</html>