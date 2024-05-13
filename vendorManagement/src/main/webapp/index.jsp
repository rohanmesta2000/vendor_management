<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendor Management</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="icon" type="logo/jpg"
	href="https://cdn.dribbble.com/users/9125986/screenshots/16427226/vimiyans_only_logo-01.jpg">
<link rel="icon" type="logo/jpg"
	href="https://cdn.dribbble.com/users/9125986/screenshots/16427226/vimiyans_only_logo-01.jpg">

<style>
@
keyframes slideInLeft { 0% {
	transform: translateX(-100%);
}

100
%
{
transform
:
translateX(
0
);
}
}
h1 {
	animation: 1s ease-out 0s 1 slideInLeft;
	background: black;
	padding: 10px;
}

body {
	font-family: Arial, sans-serif;
	background-color: black;
	color: white;
	background-image: url('https://wallpaperaccess.com/full/744085.png');
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

.center {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 40%;
	height: 380px;
}

.navbar .nav-link {
	color: white;
}

.navbar-nav.ml-auto {
	margin-right: 0;
}
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<img
			src="https://cdn.dribbble.com/users/9125986/screenshots/16427226/vimiyans_only_logo-01.jpg"
			width="40" height="40" class="d-inline-block align-top" alt="Logo">
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="admin/adminLoginPage">Admin</a>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login/Register</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
</head>
<body>
	<h3 style="color: #07ee39; text-align: center;">Vendor Management</h3>
	
	<h4 style="color: purple">A vendor management project using Java
		and Spring is a web application designed to streamline the process of
		managing vendor relationships within an organization. It includes
		features such as storing vendor details, tracking contracts,
		evaluating performance, facilitating communication, and generating
		reports.</h4>
	<form action="info" method="post"></form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
<footer>
	<span>&copy; 2024 Vendor Management Project</span><br>
</footer>

</html>