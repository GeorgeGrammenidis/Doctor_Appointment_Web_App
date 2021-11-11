<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Page</title>
</head>
<body style="background-color:powderblue;">
	<p> Welcome </p>
	<form action="ShowApps2" method="post">
		<h1> Show appointments </h1>
		<input type=submit value="Show">
	</form>
	<form action="doctorAvailability" method="post">
		<h1> Register available day </h1>
		Write the month <input type="text" name="month"> <br> <br>
		Write the day <input type="text" name="day"> <br> <br>
		<input type=submit value="Show">
	</form>
	<form action="CancelApps2" method="post">
		<h1> Cancel Appointment </h1>
		Write the month <input type="text" name="month"> <br> <br>
		Write the day <input type="text" name="day"> <br> <br>
		<input type=submit value="Cancel"> <br> <br>
	</form>
	<form action="Exit" method="post">
		<input type="Submit" value="Exit">
	</form>
</body>
</html>