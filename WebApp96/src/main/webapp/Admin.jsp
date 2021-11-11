<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Admin Page </title>
</head>


<body style="background-color:powderblue;">
	<p> Welcome </p>
	<p> Add Doctor to the System </p>
	<form action="AdminAdd" method="post">
		username: <input type="text" name="doctor_username"> <br>
		password: <input type="text" name="doctor_password"> <br>
		first name: <input type="text" name="doctor_firstname"> <br>
		last name: <input type="text" name="doctor_lastname"> <br>
		specialty: <input type="text" name="doctor_specialty"> <br> <br>
	<input type="submit" value="Add">
	</form>
	<br> <br> <br>
	<p> Delete Doctor From System </p>
	<form action="adminDelete" method="post">
		username: <input type="text" name="doctor_username0"> <br>
		password: <input type="text" name="doctor_password0"> <br> <br>
	<input type="submit" value="Delete"> <br> <br>
	</form>
	<form action="Exit" method="post">
		<input type="Submit" value="Exit"> <br> <br>
	</form>
</body>
</html>