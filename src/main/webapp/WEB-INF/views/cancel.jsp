<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.managemed.managemedapp.dao.*"%>
	<%@ page import="com.managemed.managemedapp.service.*"%>
	<%@ page import="com.managemed.managemedapp.security.*"%>
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
	dao.restoreStock(username);
	%>
	
<section>
    <p>Forgot to add something to your cart? Shop around then come back to pay!</p>
    <form action="/home" method="post">
			<button type="submit" onclick="handleCancel()">Home</button>
		</form>
  </section>
<script src="./js/viewcart.js"></script>
</body>
</html>