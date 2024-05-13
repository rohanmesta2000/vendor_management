<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendor management</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: black;
	color: white;
	background-image:
		url('https://img.freepik.com/free-vector/hand-painted-watercolor-pastel-sky-background_23-2148902771.jpg?size=626&ext=jpg&ga=GA1.1.632798143.1711584000&semt=sph');
	background-size: cover;
	background-repeat: no-repeat;
}

footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: rgb(42, 40, 40);
	color: white;
	text-align: center;
	padding: 10px;
}

.social img {
	height: 23px;
	width: 23px;
	margin: 5px;
}

h4 {
	color: rgb(195, 195, 195);
}
</Style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

	<div class="collapse navbar-collapse justify-content-between"
		id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
			</li>

			<li class="nav-item"><a class="nav-link" href="admin.jsp">Admin</a>
			</li>

		</ul>
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> <%= session.getAttribute("loggedInEmail") %>
			</a>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item"
						href="views?email=${loggedInEmail}">View</a></li>
					<li><a class="dropdown-item" href="update.jsp">Update</a></li>
				</ul></li>
		</ul>
	</div>
	</div>
</nav>
</head>
<body>
	<h3 style="color: #07ee39; text-align: center;">Vendor Management</h3>
	<form action="lSuccess" method="post"></form>
	<h3 style='color: green; text-align: center;'>Login Successful</h3>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
<footer>
	<span>&copy; 2024 Vendor Management Project</span><br>
</footer>
</html>