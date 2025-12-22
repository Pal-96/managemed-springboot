<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.managemed.managemedapp.dao.*"%>
	<%@ page import="com.managemed.managemedapp.service.*"%>
	<%@ page import="com.managemed.managemedapp.security.*"%>
	<%@ page import="com.managemed.managemedapp.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/stripe-style.css">
</head>
<body>
	<%
	DAOImpl dao = DAOImpl.getInstance();
	String token = CookieUtil.getToken(request);
	String username = JWTUtil.getUsername(token);
	int result = dao.proceedSale(username);
	%>
	<section>
		<p>Hooray! Payment successful. Thank you for shopping with ManageMed!
		We would be happy to hear from you.</p>
		<p>
			<a href="mailto:orders@example.com">pal.sayantika26@gmail.com</a>
		</p>
		<form action="/home" method="get">
			<button type="submit">Home</button>
		</form>
	</section>
	<script src="./js/viewcart.js"></script>
</body>
</html>