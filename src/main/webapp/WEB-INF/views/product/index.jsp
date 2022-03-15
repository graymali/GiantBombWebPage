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
	
	<br>
	<a href="${pageContext.request.contextPath }/cart/index">View Cart</a>
	<br><br>
	
	<form:form action="${pageContext.request.contextPath}/product/search" modelAttribute="gameList" method="post">
		<table>
			<tr><td><c:if test="${gameList.searchName != null && gameList.searchName != ''}">Search: ${gameList.searchName}</c:if></td></tr>
			<tr><td><form:input path="searchName" /> <input type="submit" value="Search"/></td></tr>
		</table>
	</form:form>
	
	<form:form action="${pageContext.request.contextPath}/product/loadPage" modelAttribute="gameList" method="post">
		<form:input type="hidden" path="currentPage" value="${gameList.currentPage}" />
		<form:input type="hidden" path="limit" value="${gameList.limit}" />
		<form:input type="hidden" path="number_of_page_results" value="${gameList.number_of_page_results}" />
		<form:input type="hidden" path="number_of_total_results" value="${gameList.number_of_total_results}" />
		<form:input type="hidden" path="offset" value="${gameList.offset}" />
		
		<p>Number of results: ${gameList.number_of_total_results}</p>
		
		<c:if test="${gameList.searchName == null || gameList.searchName == ''}">
			<table>
				<tr>
					<td><input type="submit" name="previousPage" value="Previous Page" /></td>
					<td>${gameList.currentPage}</td>
					<td><input type="submit" name="nextPage" value="Next Page" /></td>
				</tr>
			</table>
		</c:if>
		
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
	
		<c:if test="${gameList.searchName == null || gameList.searchName == ''}">
			<table>
				<tr>
					<td><input type="submit" name="previousPage" value="Previous Page" /></td>
					<td>${gameList.currentPage}</td>
					<td><input type="submit" name="nextPage" value="Next Page" /></td>
				</tr>
			</table>
		</c:if>
	</form:form>
</body>
</html>