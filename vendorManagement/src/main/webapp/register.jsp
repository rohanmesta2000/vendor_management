<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link rel="icon" type="logo/jpg"
	href="https://cdn.dribbble.com/users/9125986/screenshots/16427226/vimiyans_only_logo-01.jpg">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.4.1/css/simple-line-icons.min.css" />

<style>
body {
	font-family: Arial, sans-serif;
	color: #fff;
	background-image: url('https://wallpaperaccess.com/full/744085.png');
	background-color: black;
	background-size: cover;
	background-repeat: no-repeat;
}

.container {
	width: 70%;
	margin: auto;
	border: 2px solid #ccc;
	border-radius: 10px;
	padding: 20px;
	background-color: rgba(0, 0, 0, 0.5);
	animation: fadeIn 1s ease;
}

label {
	font-weight: bold;
}

input[type="text"], input[type="number"], input[type="email"], input[type="tel"],
	input[type="url"], input[type="date"], input[type="option"], select {
	width: 100%;
	padding: 10px;
	border-radius: 5px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	background-color: rgba(229, 227, 227, 0.9);
	color: #000000;
	transition: all 0.3s ease;
}

.btn {
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn-success {
	background-color: #28a745;
	color: #fff;
	border: none;
}

.btn-success:hover {
	background-color: #218838;
}

.btn-danger {
	background-color: #dc3545;
	color: #fff;
	border: none;
}

.btn-danger:hover {
	background-color: #c82333;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(-20px);
}

to {
	opacity: 1;
	transform: translateY(0);
}
}
</style>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<img
			src="https://static.vecteezy.com/system/resources/previews/004/526/847/original/women-protection-rgb-color-icon-protect-girls-against-violence-female-empowerment-women-safety-gender-equality-provide-peace-and-security-isolated-illustration-simple-filled-line-drawing-vector.jpg"
			width="40" height="40" class="d-inline-block align-top" alt="Logo">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
</head>
<body>
	<span style='color: red;'> <c:forEach var="objectErrors"
			items="${errors}"> ${objectErrors.defaultMessage}<br>
		</c:forEach>
	</span>
	<h3 style='color: Green'>${msg}</h3>
	<h3 style='color: red'>${uniqueError}</h3>

<!-- 	<div class="registration-form" id="emailVerify">
		<form id="forgotPasswordForm" action="#" method="post">
			<h4 class="text-center" style="font-weight: bolder;">Email
				Confirmation Authentication</h4>
			<div class="form-group">
				<label for="email" class="form-label">EmailID:</label> <input
					type="email" id="email" name="email" class="form-control"
					onchange="onEmail()" placeholder="enter your email"
					required="required" /> <span id="emailError" style="color: red;"></span>
				<span id="emailSuccess" style="color: green;"></span>
			</div>
			<button type="button" class="btn btn-primary btn-block mx-auto mt-3"
				id="checkEmail" disabled="disabled">Generate OTP</button>
			<div class="form-group mt-3" style="display: none;" id="otpSection">
				<label for="otp" class="form-label">Enter OTP:</label> <input
					type="text" placeholder="enter OTP" class="form-control" id="otp"
					name="otp" required> <span id="otpError"
					style="color: red;"></span>
				<button type="button" class="btn btn-primary btn-block mt-3"
					id="validateOTP">Validate OTP</button>
			</div>
		</form>
	</div> -->

	<div class="registration-form" id="signupForm">

		<form action="vendorRegister" class="container" method="post"
			onsubmit="return validateForm();">
			<h3 style='text-align: center; color: #07ee39;'>Register here</h3>
			<h5 style="color: black;">Personal details</h5>
			<div class="row">
				<div class="col-md-6">
					<div class="design">
						<label for="ownerName">Owner Name</label><br> <input
							type="text" name="ownerName" id="ownerName"
							placeholder="Enter owner name" value="${entity.ownerName}">
					</div>
					<br>
					<div class="design">
						<label for="contactNo">Contact Number</label><br> <input
							type="number" name="contactNo" id="contactNo"
							placeholder="Enter contact number" value="${entity.contactNo}"
							onchange="uniqueContactNo()"> <span style='color: red;'
							id="contactNoExistMsg"></span>
					</div>
					<br>
				</div>


				<div class="col">
					<div class="design">
						<label for="email">Email</label><br> <input type="email"
							name="email" id="email" placeholder="Enter email"
							value="${entity.email}" onchange="uniqueEmail()"> <span
							style='color: red;' id="signupEmail"></span>
					</div>
					<br>

					<div class="design">
						<label for="alternativeNo">Alternative Contact Number</label><br>
						<input type="number" name="alternativeNo" id="alternativeNo"
							placeholder="Enter alternative contact number"
							value="${entity.alternativeNo}" onchange="uniqueAlternativeNo()">
						<span style='color: red;' id="alternativeNoExistMsg"></span>
					</div>
					<br>
				</div>
			</div>



			<h5 style="color: black;">Business details</h5>
			<div class="row">
				<div class="col-md-6">
					<div class="design">
						<label for="ownerName">Vendor Name</label><br> <input
							type="text" name="vendorName" id="vendorName"
							placeholder="Enter vendorName name" value="${entity.vendorName}">
					</div>
					<br>

					<div class="design">
						<label for="gstNo">GST Number</label><br> <input type="text"
							name="gstNo" id="gstNo" placeholder="Enter GST number"
							value="${entity.gstNo}" onchange="uniqueGstNo()"> <span
							style='color: red;' id="gstNoExistMsg"></span>
					</div>
					<br>
					<div class="design">
						<label for="companyStartDate">Company Start Date</label><br>
						<input type="date" name="companyStartDate" id="companyStartDate"
							placeholder="Select start date"
							value="${entity.companyStartDate}">
					</div>
					<br>
				</div>
				<div class="col">
					<div class="design">
						<label for="address">Address</label><br> <input type="text"
							name="address" id="address" placeholder="Enter address "
							value="${entity.address}">
					</div>
					<br>

					<div class="design">
						<label for="website">Website</label><br> <input type="text"
							name="website" id="website" placeholder="Enter website"
							value="${entity.website}" onchange="uniqueWebsite()"> <span
							style='color: red;' id="websiteExistMsg"></span>
					</div>
					<br>

					<div class="design">
						<label for="pincode">Pincode</label><br> <input type="number"
							name="pincode" id="pincode" placeholder="Enter pincode"
							value="${entity.pincode}" onchange="uniqueWebsite()"> <span
							style='color: red;' id="pincodeExistMsg"></span>
					</div>
					<br>

				</div>
			</div>
			<div class="design">
				<button type="submit" class="btn btn-success" value="send"
					id="submitButton">Register</button>
				<button type="reset" class="btn btn-danger" value="reset">Clear</button>
			</div>

		</form>
	</div>
	

	
	<script>
	
	function uniqueGstNo() {
		var submitButton = document.getElementById("submitButton");
		console.log("Running uniqueGstNo");
		const gstNo = document.getElementById("gstNo").value;
		console.log(gstNo);

		if (gstNo != null && gstNo != "" && gstNo.length < 30) {
			console.log("GstNo is valid.");
			document.getElementById("gstNoExistMsg").innerHTML = "";
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET", "http://localhost:8099/vendorManagement/gstNoAjax/"
					+ gstNo);
			xhttp.send();

			xhttp.onload = function() {
				document.getElementById("gstNoExistMsg").innerHTML = this.responseText;

			}

		} else {
			console.log("Invalid gstNo");
			document.getElementById("gstNoExistMsg").innerHTML = "please enter valid gstNo.";

		}

	}

	function uniqueContactNo() {
		var submitButton = document.getElementById("submitButton");
		console.log("Running uniqueContactNo");
		const contactNo = document.getElementById("contactNo").value;
		console.log(contactNo);

		if (contactNo != null && contactNo != "" && contactNo.length == 10) {
			console.log("contactNo is valid.");
			document.getElementById("contactNoExistMsg").innerHTML = "";
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET",
					"http://localhost:8099/vendorManagement/contactNoAjax/"
							+ contactNo);
			xhttp.send();

			xhttp.onload = function() {
				document.getElementById("contactNoExistMsg").innerHTML = this.responseText;

			}

		} else {
			console.log("Invalid contactNo");
			document.getElementById("contactNoExistMsg").innerHTML = "please enter valid contactNo.";
		}

	}

	function uniqueAlternativeNo() {
		var submitButton = document.getElementById("submitButton");
		console.log("Running uniqueAlternativeNo");
		const alternativeNo = document.getElementById("alternativeNo").value;
		console.log(alternativeNo);

		if (alternativeNo != null && alternativeNo != ""
				&& alternativeNo.length == 10) {
			console.log("AlternativeNo is valid.");
			document.getElementById("alternativeNoExistMsg").innerHTML = "";
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET",
					"http://localhost:8099/vendorManagement/alternativeNoAjax/"
							+ alternativeNo);
			xhttp.send();

			xhttp.onload = function() {
				document.getElementById("alternativeNoExistMsg").innerHTML = this.responseText;

			}

		} else {
			console.log("Invalid alternativeNo");
			document.getElementById("alternativeNoExistMsg").innerHTML = "please enter valid alternativeNo.";
		}

	}

	function uniqueEmail() {
		var submitButton = document.getElementById("submitButton");
		console.log("Running uniqueEmail");
		const email = document.getElementById("email").value;
		console.log(email);

		if (email != null && email != "" && email.length < 30) {
			console.log("Email is valid.");
			document.getElementById("signupEmail").innerHTML = "";
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET", "http://localhost:8099/vendorManagement/emailAjax/"
					+ email);
			xhttp.send();

			xhttp.onload = function() {
				document.getElementById("signupEmail").innerHTML = this.responseText;

			}

		} else {
			console.log("Invalid email");
			document.getElementById("signupEmail").innerHTML = "please enter valid email.";

		}

	}
	
	function uniqueWebsite() {
		var submitButton = document.getElementById("submitButton");
		console.log("Running uniqueWebsite");
		const website = document.getElementById("website").value;
		console.log(website);

		if (website != null && website != "" && website.length < 300) {
			console.log("Website is valid.");
			document.getElementById("websiteExistMsg").innerHTML = "";
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET", "http://localhost:8099/vendorManagement/websiteAjax/" + website);
			xhttp.send();

			xhttp.onload = function() {
				document.getElementById("websiteExistMsg").innerHTML = this.responseText;

			}

		} else {
			console.log("Invalid Website");
			document.getElementById("websiteExistMsg").innerHTML = "please enter valid Website.";

		}

	}
</script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>

</html>