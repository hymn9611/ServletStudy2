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
		<%
			String num = request.getParameter("bookNumber");
			Object obj = request.getAttribute("dto");
			BankBookDTO bankBookDTO = (BankBookDTO)obj;
		%>
		
		<h3>num : <%= num %></h3>
		<!-- 주석 알아보기 팁 : ctrl + shift + / -->
		<!-- jsp까지 살아서 못오는 데이터를 request에 담아서 왔다. -->
		<h3>Name : <%= bankBookDTO.getBookName() %></h3>
		
	</body>
</html>