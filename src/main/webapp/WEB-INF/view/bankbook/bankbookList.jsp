<%@page import="com.iu.s1.bankbook.BankBookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	
	<body>
		<h1>Bankbook List Page by JSTL</h1>
		
		<table>
			<tr>
				<th>일련번호</th><th>제품명</th><th>이자율</th>
			</tr>
			
			
		<%-- <%
			/* request에 담아놓은 데이터를 여기서 사용 */
			Object obj = request.getAttribute("list");
			ArrayList<BankBookDTO> ar = (ArrayList<BankBookDTO>)obj;
			for(BankBookDTO dto: ar){
		%>
			<tr>
				<td><%= dto.getBookNumber() %></td>
				<td><a href="./bankbookSelect.do?bookNumber=<%= dto.getBookNumber()%>"><%=dto.getBookName() %></a></td>
				<td><%= dto.getBookRate() %></td>
			</tr>
		<%}%> --%>
		
			<c:forEach items="${list}" var="dto" varStatus="i">
			<tr>
				<td>${pageScope.dto.bookNumber}</td>
				<td><a href="./bankbookSelect.do?bookNumber=${dto.bookNumber}"> ${dto.bookName}</a></td>
				<%-- <td>${dto.bookName}</td> --%>
				<td>${dto.bookRate}</td>
				<td>
					<p>현재 아이템 : ${i.current}</p>
					<p>인덱스 번호 : ${i.index}</p>
					<p>순서 번호 : ${i.count}</p>
					<p>처음 ? : ${i.first}</p>
					<p>마지막? : ${i.last}</p>
				</td>
			</tr>
			
			</c:forEach>
		</table>
		
		<div>
			<c:forEach begin="1" end="10" step="1" var="num" varStatus="i">
				<button>${num} : ${i.begin} : ${i.end} : ${i.step}</button>				
			</c:forEach>
		</div>
		
		<a href="./bankbookInsert.do">WRITE</a>
	</body>
</html>