<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Login Page</h3>
	
	<form method="post" action="usercheck">
		
		<c:remove var="message" scope="session" />
		<div class="container">
			<label><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="userName" required> <br>

			<label><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" required> <br>

			<button type="submit">Login</button>
			<button type="reset" class="cancelbtn">Cancel</button>


		</div>


	</form>

	
</body>
</html>