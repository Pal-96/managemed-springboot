<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/stripe-style.css">
</head>
<body>
<section>
    <p>Forgot to add something to your cart? Shop around then come back to pay!</p>
    <form action="/home" method="post">
			<button type="submit" onclick="handleCancel()">Home</button>
		</form>
  </section>
<script src="./js/viewcart.js"></script>
</body>
</html>