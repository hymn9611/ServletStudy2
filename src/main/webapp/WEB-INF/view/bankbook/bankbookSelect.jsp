<%@page import="com.iu.s1.bankbook.BankBookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h1>BankBook Select Page</h1>
		
		<h3>num : ${requestScope.dto.bookNumber}</h3>
		<!-- 주석 알아보기 팁 : ctrl + shift + / -->
		<!-- jsp까지 살아서 못오는 데이터를 request에 담아서 왔다. -->
		<h3>Name : ${requestScope.dto.bookName}</h3>
		<h3>Count : ${requestScope.count}</h3>
		<h3>Name : ${name}</h3>
		<h3>SE : ${sessionScope.se}</h3>
		<!-- BankBookController에는 se키를 갖고 있는 scope명이 두 개 있다. 그 중에서 생명주기가 짧은게 선택된다. -->
		<h3>SE : ${se}</h3> 
		
	</body>
</html>