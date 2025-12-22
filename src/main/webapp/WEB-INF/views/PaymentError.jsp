<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.managemed.managemedapp.dao.*"%>
	<%@ page import="com.managemed.managemedapp.service.*"%>
	<%@ page import="com.managemed.managemedapp.security.*"%>
	<%@ page import="com.managemed.managemedapp.util.*"%>
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
    <p>Session Timed Out. Please try again!</p>
    <form action="/home" method="get">
			<button type="submit">Home</button>
		</form>
  </section>

</body>
</html>