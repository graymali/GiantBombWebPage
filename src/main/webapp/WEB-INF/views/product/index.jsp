<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>
</head>
<body>

	<h3>Products Page</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>GUID</th>
			<th>Photo</th>
			<th>Name</th>
			<th>Buy</th>
		</tr>
		<c:forEach var="game" items="${gameList.results }">
			<tr>
				<td>${game.guid }</td>
				<td><img src="${game.image.icon_url }"></td>
				<td>${game.name }</td>
				<td align="center">
					<a href="${pageContext.request.contextPath }/cart/buy/${game.guid}">Buy Now</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>