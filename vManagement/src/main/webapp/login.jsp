<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In Page</title>
<style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin-top: 30px;
            width:10%;
            padding: 0;
        }

        .navbar {
            background-color: #343a40;
        }

        .navbar-brand {
            color: #ffffff;
            font-weight: bold;
        }

        .navbar-nav .nav-link {
            color: #ffffff;
        }

        form {
            background-color: #ffffff;
            border-radius: 5px;
            padding: 10px;
            box-shadow: 1 1 10px rgba(0, 0, 0, 0.1);
        }

        h3 {
            color: #007bff;
        }

        label {
            font-weight: bold;
        }

        .form-control {
            margin-bottom: 10px;
        }

        .design {
            margin-top: 20px;
        }

        #otpButton, #loginBtn {
            margin-right: 10px;
        }

        #emailMsg, #otpErr {
            font-size: 14px;
        }

        .container {
            max-width: 600px;
        }
    </style>
</head>

<body>
	<!-- <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">SIGN IN </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>

				</ul>
			</div>
		</div>
		
		
	</nav>
	<br> -->
	
	
	
	<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul>`
            <li><a href="index.jsp" style="margin-left: 10px;">Home</a></li>
           <!--  <li><a href="loginfrom.jsp" style="margin-left: 400px;">LogIn</a></li> -->
            <li><a href="registration.jsp" style="margin-left: 100px;">Registration</a></li>
           <!--  <li><a href="#" style="margin-left: 50px;">About</a></li>
            <li><a href="#" style="margin-left:50px;">Service -Avaliable</a></li> -->
        </ul>

    </nav>

</header>
<style>
 body{
            height: 900px;
            width: 100vw;




        }
    
    .navbar ul{
        overflow:auto;
        margin:auto;

    }
    .navbar li{
        list-style: none;
        margin: 10px 10px;
        float:left;
    }
    .navbar li a:hover{
        color: antiquewhite;
    }
    .navbar li a{
        color: aliceblue;
        text-decoration: none;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        font-size: 1.4em;
    }
    .search{
        float: right;
        color: aliceblue;
        padding: 10px;
        margin-top: -9.5vh;
    }
    .search input{
        border: solid blue;
        border-radius: 5px;
    }
    </style>
	<form action="signIn" method="post" class="container">
		<h3 style='text-align: center'>SIGN-IN</h3>
		<div class="row">
			<div class="col">
				<label for="email">Enter mail</label> <input type="email"
					name="email" class="form-control" id="email"
					placeholder="Enter mail" onchange="loginMail()"
					value="${dto.email}"> <span style='color: red;'
					id="emailMsg"></span> <br>
					
					 <label for="otp">OTP</label> <input
					type="number" class="form-control" name="otp" id="otp"
					placeholder="Enter OTP" onchange="validateOTP()" value="${dto.otp}">
				<span style='color: red;' id="otpErr"></span><br>
			</div>
		</div>

		<div class="design">
			<button type="button" id="otpButton" class="btn btn-primary"
				onclick="generateOtp()" disabled="disabled">Generate OTP</button>
			<button type="submit" id="loginBtn" class="btn btn-success"
				disabled="disabled">LogIn</button>
		</div>
	</form>
	<script>
		function loginMail() {
			const email = document.getElementById("email").value;
			const generatedOtp = document.getElementById("otpButton");
			if (email == "" || email == null) {
				document.getElementById("emailMsg").innerHTML = "*Email can't be empty*";
				console.log("Email can't be empty");
				generatedOtp.setAttribute("disabled", "");
			} else if (!email
					.match(/^([a-zA-Z0-9\.]+@+[a-zA-Z]+(\.)+[a-zA-Z]{2,})$/)) {

				document.getElementById("emailMsg").innerHTML = "*Email should be in format*";
				console.log("Email should be in format");
				generatedOtp.setAttribute("disabled", "");
			} else if (email !== "" || email !== null) {

				const xhttp = new XMLHttpRequest();
				xhttp.open("GET",
						"http://localhost:8099/vManagement/loginEmailAjax/"
								+ email);

				xhttp.send();

				xhttp.onload = function() {

					const response = document.getElementById("emailMsg").innerHTML = this.responseText;
					if (response == "") {
						generatedOtp.removeAttribute("disabled");
					} else {
						generatedOtp.setAttribute("disabled", "");
					}

				}
			}
		}

		function generateOtp() {
			const email = document.getElementById("email").value;
			const xhttp = new XMLHttpRequest();
			xhttp.open("GET",
					"http://localhost:8099/vManagement/loginOtpEmailMsg/"
							+ email);
			xhttp.send();

			xhttp.onload = function() {
				document.getElementById("otpErr").innerHTML = this.responseText;
			}
		}

		function validateOTP() {
			const otp = document.getElementById("otp").value;
			const loginButton = document.getElementById("loginBtn");

			if (otp == null || otp == "") {

				console.log("*OTP cannot be empty*");
				document.getElementById("otpErr").innerHTML = "OTP cannot be empty";
			} else if (otp != "") {
				const xhttp = new XMLHttpRequest();
				xhttp.open("GET",
						"http://localhost:8099/vManagement/loginOtpAjax/"
								+ otp);
				xhttp.send();

				xhttp.onload = function() {
					 document.getElementById("otpErr").innerHTML = this.responseText;
					 const response =this.responseText;
					if (response == "OTP Matched") {
						loginButton.removeAttribute("disabled");
					} else {
						loginButton.setAttribute("disabled", "disabled");
					}

				}
			}
		}
	</script>

</body>
</html>