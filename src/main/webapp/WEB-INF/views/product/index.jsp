<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>
</head>
<body>

	<h3>Products Page</h3>
	<form:form action="${pageContext.request.contextPath}/product/loadPage" modelAttribute="gameList" method="post">
		<form:input type="hidden" path="currentPage" value="${gameList.currentPage}" />
		<form:input type="hidden" path="limit" value="${gameList.limit}" />
		<form:input type="hidden" path="number_of_page_results" value="${gameList.number_of_page_results}" />
		<form:input type="hidden" path="number_of_total_results" value="${gameList.number_of_total_results}" />
		<form:input type="hidden" path="offset" value="${gameList.offset}" />
		
		<table cellpadding="2" cellspacing="2" border="1">
			<tr>
				<th>GUID</th>
				<th>Photo</th>
				<th>Name</th>
				<th>Rent</th>
			</tr>
			<c:forEach var="game" items="${gameList.results}">
				<tr>
					<td>${game.guid}</td>
					<td><img src="${game.image.icon_url}"></td>
					<td>${game.name}</td>
					<td align="center"><a href="${pageContext.request.contextPath}/cart/buy/${game.guid}">Rent Now</a></td>
				</tr>
			</c:forEach>
		</table>
	
		<table>
			<tr>
				<td><input type="submit" name="previousPage" value="Previous Page" /></td>
				<td>${gameList.currentPage}</td>
				<td><input type="submit" name="nextPage" value="Next Page" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>