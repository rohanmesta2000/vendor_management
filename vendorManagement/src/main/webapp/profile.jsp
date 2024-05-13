<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" integrity="..." crossorigin="anonymous" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


<style>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: Raleway, sans-serif;
}
  body {
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
}
footer {
    position: fixed;
    bottom: 0;
    width: 100%;
}

.footer-btn {
    color: white;
}

.footer-btn:hover {
    text-decoration: none;
}

.btn-floating {
    border-radius: 50%;
}

.btn-floating i {
    vertical-align: middle;
}

.btn-floating.m-1 {
    margin: 0 0.5rem;
}
.dropdown-menu {
    right: 0; 
    min-width: auto; 
    z-index: 999; 
} 

 .profile-photo {
	width: 50px; /* Adjust as needed */
	height: 50px; /* Adjust as needed */
	border-radius: 50%; /* Makes the image round */
	margin-right: 5px; /* Adds space between the image and email */
} 
.container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

.design {
    margin-bottom: 15px;
}

.design label {
    display: block;
    font-weight: bold;
    color: #333;
}

.design input[type="text"],
.design input[type="number"],
.design textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box; /* Ensure that padding and border are included in the element's total width and height */
}

.design textarea {
    resize: vertical; /* Allow vertical resizing */
}

.design .btn {
    padding: 10px 20px;
    margin-right: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.design .btn.btn-success {
    background-color: #28a745;
    color: #fff;
}

.design .btn.btn-danger {
    background-color: #dc3545;
    color: #fff;
}

.row {
    display: flex;
    flex-wrap: wrap;
}

.col {
    flex: 1; 
    margin-left: 10px;
}

@media screen and (max-width: 768px) {
    .col {
        flex-basis: 100%; 
        margin-left: 0;
        margin-top: 10px; 
    }
}
</style>
</head>
<body>


<div class="modern-navbar">
    <nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
        <div class="container-fluid">
         <a class="navbar-brand" href="#">
                  <img src="https://tse4.mm.bing.net/th?id=OIP.3bKZlDVBAtAh8DitadnD4AHaHa&pid=Api&P=0&h=180" width="40" height="40" class="d-inline-block align-top" alt="Logo">
         
           </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="#" onclick="toggleForm()">Add Product</a>
                    </li>
                </ul>
                 <ul class="navbar-nav">
                 <input type="hidden" name="email" value="${email}">
                <li class="nav-item"><a class="nav-link" href="#" onclick="toggleTable()">View All Products</a></li>
                </ul>
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto">
                
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            <img class="profile-photo" src="display?email=${email}" alt="User Photo"  <%= session.getAttribute("email") %>>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                            <li class="dropdown-item">
                                <img class="profile-photo" src="display?email=${email}" alt="User Photo"><br>
                                ${email}
                            </li>
                            <li><a class="dropdown-item" href="profile.jsp">Home</a></li>
                            <li><a class="dropdown-item" href="editprofilepage?email=${email}">Update</a></li>
          
                            <li><a class="dropdown-item" href="index.jsp">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div id="productFormContainer" style="display: none;">
    <form action="saveProduct" class="container" method="post" onsubmit="return validateForm();">
    <input type="hidden" name="email" value="${email}">
    
        <h3 style='text-align: center; color: #07ee39;'>Add Product</h3>
        <div class="row">
            <div class="col-md-6">
                <div class="design">
                <label for="category">Category</label><br>
                <select name="category" id="category">
                <option value="choose">Choose</option>
                    <option value="electronics">Electronics</option>
                    <option value="clothing">Clothing</option>
                    <option value="books">Books</option>
                    <option value="food">Food</option>
                    <option value="appliances">Appliances</option>
                    <option value="furniture">Furniture</option>
                </select>
            </div>
            <br>
                <div class="design">
                    <label for="productName">Product Name</label><br>
                    <input type="text" name="productName" id="productName" placeholder="Enter product name">
                </div>
                <br>
                <div class="design">
                    <label for="productPrice">Product Price</label><br>
                    <input type="number" name="productPrice" id="productPrice" placeholder="Enter product price">
                </div>
                <br>
            </div>
            <div class="col">
                <div class="design">
                    <label for="deliveryCharge">Delivery Charge</label><br>
                    <input type="number" name="deliveryCharge" id="deliveryCharge" placeholder="Enter delivery charge">
                </div>
                <br>
                <div class="design">
                    <label for="description">Description</label><br>
                    <textarea name="description" id="description" placeholder="Enter description" rows="3"></textarea>
                </div>
                <br>
                
                <div class="design">
                    <label for="available">Available</label><br>
                    <input type="text" name="available" id="available" placeholder="Available or not">
                </div>
                <br>
            </div>
        </div>
        <div class="design">
            <button type="submit" class="btn btn-success" value="send">Save Product</button>
            <button type="reset" class="btn btn-danger" value="reset">Clear</button>
        </div>
    </form>
   
</div>
<div>
  <%-- <form action="viewAll" method="post">
        <div class="col text-end">
					<input type="hidden" id="email" name="email" value="${email}">
            <button type="submit" class="btn btn-primary">View All</button>
        </div>
    </div>  --%>
<form id="getproductDetails" action="getproductDetails"
					method="post">
					<input type="hidden" id="email" name="email" value="${email}">
					<button class="btn btn-primary" type="submit" id="viewProductsBtn"
						onclick="toggleTable()">View All Products</button>
</form>

<div id="tableContainer" >

    <div class="container">
        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Category</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Product Price</th>
                    <th scope="col">Delivery Charge</th>
                    <th scope="col">Description</th>
                    <th scope="col">Available</th>
                    <th scope="col">Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.category}</td>
                        <td>${product.productName}</td>
                        <td>${product.productPrice}</td>
                        <td>${product.deliveryCharge}</td>
                        <td>${product.description}</td>
                        <td>${product.available}</td>
                         <td>
                      <form action="updateProduct" method="post">
                      <input type="hidden" name="id" value="${product.id}">
                      <button type="submit" class="btn btn-primary">Update</button>
                     </form>
                     </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    // Function to toggle the visibility of the product form and hide the table
    function toggleForm() {
        var formContainer = document.getElementById('productFormContainer');
        var tableContainer = document.getElementById('tableContainer');
        
        // Hide the table container
        tableContainer.style.display = 'none';
        
        // Toggle the form container
        if (formContainer.style.display === 'none') {
            formContainer.style.display = 'block';
        } else {
            formContainer.style.display = 'none';
        }
    }

    // Function to toggle the visibility of the table and hide the form
    function toggleTable() {
        var formContainer = document.getElementById('productFormContainer');
        var tableContainer = document.getElementById('tableContainer');
        
        // Hide the form container
        formContainer.style.display = 'none';
        
        // Toggle the table container
        if (tableContainer.style.display === 'none') {
            tableContainer.style.display = 'block';
        } else {
            tableContainer.style.display = 'none';
        }
    }
</script>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>