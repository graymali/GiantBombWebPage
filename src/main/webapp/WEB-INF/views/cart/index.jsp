<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Page</title>
</head>
<body>

	<h3>Cart Page</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Option</th>
			<th>GUID</th>
			<th>Photo</th>
			<th>Name</th>
			<th>Quantity</th>
		</tr>
		<c:forEach var="item" items="${sessionScope.cart }">
			<tr>
				<td align="center"><a
					href="${pageContext.request.contextPath }/cart/remove/${item.game.guid }"
					onclick="return confirm('Are you sure?')">Remove</a></td>
				<td>${item.game.guid }</td>
				<td><img src="${item.game.image.icon_url }"></td>
				<td>${item.game.name }</td>
				<td>${item.quantity }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="${pageContext.request.contextPath }/product">Continue Shopping</a>

</body>
</html>