<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Form</title>
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
	width: 35%;
	border: 2px solid #ccc;
	border-radius: 10px;
	padding: 20px;
	background-color: rgba(0, 0, 0, 0.5);
	animation: fadeIn 1s ease;
}

label {
	font-weight: bold;
}

input[type="email"], input[type="number"] {
	width: 100%;
	padding: 10px;
	border-radius: 5px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	background-color: rgba(229, 227, 227, 0.685);
	color: #000000;
	transition: all 0.3s ease;
}

.btn {
	margin-top: 10px;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn-primary {
	background-color: #007bff;
	color: #fff;
	border: none;
}

.btn-primary:hover {
	background-color: #0056b3;
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
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<img
				src="https://static.vecteezy.com/system/resources/previews/004/526/847/original/women-protection-rgb-color-icon-protect-girls-against-violence-female-empowerment-women-safety-gender-equality-provide-peace-and-security-isolated-illustration-simple-filled-line-drawing-vector.jpg"
				width="40" height="40" class="d-inline-block align-top" alt="Logo">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
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

	<div class="admin-form">
        <h3 style="text-align: center;color:#a80f0f;">Admin Login Form</h3>
        <form action="adminLogin" method="get">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label> 
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" onchange="adminLoginMail()" /> 
                <span id="emailErrMsg" style="color: red;"></span>
            </div>
            
            <div class="mb-3">
                <label for="password" class="form-label">Password</label> 
                <input type="password" class="form-control" id="password" name="password" placeholder="Please enter your password" minlength="8" onchange="validatePassword" /> 
                <span id="passwordErrMsg" style="color: red;"></span>
            </div>
            <div class="input-box">
                <button type="submit" class="btn btn-primary" id="loginBtn" >Log in</button>
            </div>
        </form>
    </div>

</body>
<footer>
	<span>&copy; 2024 Vendor Management Project</span><br>
</footer>
</html>