<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Page</title>
</head>
<body style="background-color:powderblue;">
	<p> Welcome </p>
	<form action="ShowApps" method="post">
		<h1> Show previous appointments </h1>
		<input type=submit value="Show">
	</form>
	<form action="PresentApps" method="post">
		<h1> Show available appointments </h1>
		Write the specialty you require <input type="text" name="specialty"> <br> <br>
		<input type=submit value="Show">
	</form>
	 <h1> Cancel an appointment </h1>
	<form action="CancelApps" method="post">
		Write the month of the appointment: <input type="text" name="month"> <br> <br> 
		Write the day of the appointment: <input type="text" name="day"> <br> <br> 
	<input type="submit" value="Cancel"> <br> <br>
	</form>
	 <h1> Make appointment </h1>
	<form action="MakeApp" method="post">
		Write the month:  <input type="text" name="month"> <br> <br> 
		Write the day: <input type="text" name="day"> <br> <br> 
		Write the doctor id: <input type="text" name="id"> <br> <br> 
	<input type="submit" value="Make"> <br> <br>
	</form>
	<form action="Exit" method="post">
		<input type="Submit" value="Exit">
	</form>
</body>
</html>