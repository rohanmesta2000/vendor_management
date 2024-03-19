<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
	
	<style >
	
	.body{
	centre}
	
	
	</style>

</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

	
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item" style="margin-left: 10px;margin-top: -10px; font-size: large;"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item" style="margin-left: 900px;margin-top: -10px; font-size: large;"><a class="nav-link" href="login.jsp">Login</a></li>
			</ul>
		</div>

	
</nav>
<body>
    <center>
   
	<span style='color: Green'>${msg}</span>
	<span style='color: Red'>${uniqueError}</span>


	<form action="vendorManage" class="container" method="post">
		<div class="design">
			<label for="name" style="margin-left: -15%">Name</label><br> 
			<input type="text"
				name="name" id="name" placeholder="Enter name"
				value="${entity.name}">
		</div>
		<br>
		<div class="design">
			<label for="location" style="margin-left: -14%">Location</label><br>
			 <input type="text" name="location" id="location" placeholder="Enter location" 
			 svalue="${entity.location}">
		</div>
		<br>
		<div class="design">
			<label for="gstNo" style="margin-left: -10%">GST Number</label><br> 
			<input type="text"
				name="gstNo" id="gstNo" placeholder="Enter GST number"
				onblur="uniqueGSTNo()" value="${entity.gstNo}"><br>
				 <span style='color: red;' id="gstNoExistMsg"></span>
		</div>
		<br>
		<div class="design">
			<label for="companyStartDate" >Company Start Date</label><br> 
			<input
				type="date" name="companyStartDate" id="companyStartDate"
				placeholder="Select start date" value="${entity.companyStartDate}">
		</div>
		<br>
		<div class="design">
			<label for="ownerName" style="margin-left: -10%">Owner Name</label><br> <input type="text"
				name="ownerName" id="ownerName" placeholder="Enter owner name"
				value="${entity.ownerName}">
		</div>
		<br>
		<div class="design" style="width: 20%">
			<label for="serviceType" style="margin-left: -48%">Service Type</label> <select
				class="form-control" id="serviceType" name="serviceType">
				<option selected value=" ">Select service type</option>
				<option value="water"
					<c:if test="${entity.serviceType=='water'}">selected="selected"</c:if>>Water</option>
				<option value="milk"
					<c:if test="${entity.serviceType=='milk'}">selected="selected"</c:if>>Milk</option>
				<option value="coffee"
					<c:if test="${entity.serviceType=='coffee'}">selected="selected"</c:if>>Coffee</option>
				<option value="laptop"
					<c:if test="${entity.serviceType=='laptop'}">selected="selected"</c:if>>Laptop</option>
				<option value="internet"
					<c:if test="${entity.serviceType=='internet'}">selected="selected"</c:if>>Internet</option>
				<option value="food"
					<c:if test="${entity.serviceType=='food'}">selected="selected"</c:if>>Food</option>
			</select>
		</div>
		<br>

		<div class="design">
			<label for="contactNo" style="margin-left: -6%">Contact Number</label><br> <input
				type="number" name="contactNo" id="contactNo"
				placeholder="Enter contact number" onblur="uniqueContactNo()"
				value="${entity.contactNo}"> <br>
				<span style='color: red;'
				id="contactNoExistMsg"></span>
		</div>
		<br>
		<div class="design">
			<label for="alternativeNo" style="margin-left: 1%">Alternative Contact Number</label><br>
			<input type="number" name="alternativeNo" id="alternativeNo"
				placeholder="Enter alternative contact number"
				value="${entity.alternativeNo}">
		</div>
		<br>
		<div class="design">
			<label for="email" style="margin-left: -15%">Email</label><br> <input type="email"
				name="email" id="email" placeholder="Enter email"
				onblur="uniqueEmail()" value="${entity.email}"><br>
				 <span style='color: red;' id="emailExistMsg"></span>
		</div>
		<br>
		<div class="design">
			<label for="website" style="margin-left: -14%">Website</label><br> <input type="text"
				name="website" id="website" placeholder="Enter website"
				onblur="uniqueWebsite()" value="${entity.website}"><br>
				<span style='color: red;' id="websiteExistMsg"></span>
		</div>
		<br>
		<div class="design">
			<button type="submit" class="btn btn-success" value="register" >Register</button>
			<button type="reset" class="btn btn-danger" value="reset">Reset</button>
		</div>
	</form>
</center>

	<script>
		function uniqueEmail() {
			var submitButton = document.getElementById("submitButton");
			console.log("Running uniqueEmail");
			const email = document.getElementById("email").value;
			console.log(email);

			if (email != null && email != "" && email.length < 30) {
				console.log("Email is valid");
				document.getElementById("emailExistMsg").innerHTML = "";
				const xhttp = new XMLHttpRequest();

				xhttp.open("GET",
						"http://localhost:8099/vManagement/uniqueEmail/"
								+ email);
				xhttp.send();

				xhttp.onload = function() {
					document.getElementById("emailExistMsg").innerHTML = this.responseText;

				}
			} else {

				console.log("Invalid email");
				document.getElementById("emailExistMsg").innerHTML = "please enter a valid email"
			}

		}

		function uniqueGSTNo() {
			var submitButton = document.getElementById("submitButton");
			console.log("Running uniqueGSTNo");
			const gstNo = document.getElementById("gstNo").value;
			console.log(gstNo);

			if (gstNo != null && gstNo != "" && gstNo.length < 20) {
				console.log("GSTNo is valid");
				document.getElementById("gstNoExistMsg").innerHTML = "";
				const xhttp = new XMLHttpRequest();

				xhttp.open("GET",
						"http://localhost:8099/vManagement/uniqueGSTNo/"
								+ gstNo);
				xhttp.send();

				xhttp.onload = function() {
					document.getElementById("gstNoExistMsg").innerHTML = this.responseText;

				}
			} else {

				console.log("Invalid gstNo");
				document.getElementById("gstNoExistMsg").innerHTML = "please enter a valid GSTNo"
			}

		}

		function uniqueContactNo() {
			var submitButton = document.getElementById("submitButton");
			console.log("Running uniqueContactNo");
			const contactNo = document.getElementById("contactNo").value;
			console.log(contactNo);

			if (contactNo != "" && contactNo.length == 10) {
				console.log("contactNo is valid");
				document.getElementById("contactNoExistMsg").innerHTML = "";
				const xhttp = new XMLHttpRequest();

				xhttp.open("GET",
						"http://localhost:8099/vManagement/uniqueContactNo/"
								+ contactNo);
				xhttp.send();

				xhttp.onload = function() {
					document.getElementById("contactNoExistMsg").innerHTML = this.responseText;

				}
			} else {

				console.log("Invalid contactNo");
				document.getElementById("contactNoExistMsg").innerHTML = "please enter 10 digit Number"
			}

		}

		function uniqueWebsite() {
			var submitButton = document.getElementById("submitButton");
			console.log("Running uniqueWebsite");
			const website = document.getElementById("website").value;
			console.log(website);

			if (website != null && website != "" && website.length < 20) {
				console.log("website is valid");
				document.getElementById("websiteExistMsg").innerHTML = "";
				const xhttp = new XMLHttpRequest();

				xhttp.open("GET",
						"http://localhost:8099/vManagement/uniqueWebsite/"
								+ website);
				xhttp.send();

				xhttp.onload = function() {
					document.getElementById("websiteExistMsg").innerHTML = this.responseText;

				}
			} else {

				console.log("Invalid website");
				document.getElementById("websiteExistMsg").innerHTML = "please enter a valid website address"
			}

		}
	</script>

</body>




</html>