<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 70px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">Product Management</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <!-- Add any additional navbar links here -->
        </ul>
    </div>
</nav>

<h2>Admin page</h2>
<div class="container mt-5">
    <button class="btn btn-primary mb-3" id="viewProductBtn">View Product</button>
    <button class="btn btn-primary mb-3" id="viewVendorBtn">View Vendor</button>
    <div id="productTableDiv" style="display: none;">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Vendor Name</th>
                    <th>Category</th>
                    <th>Product Name</th>
                    <th>Product Price</th>
                    <th>Delivery Charge</th>
                    <th>Description</th>
                    <th>Available</th>
                    <th>Action</th> <!-- Added Action column -->
                </tr>
            </thead>
            <tbody id="productTableBody">
                <!-- Product details will be populated here using AJAX -->
            </tbody>
        </table>
    </div>
    <div id="vendorTableDiv" style="display: none;">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Profile Picture</th>
                    <th>Owner Name</th>
                    <th>Email</th>
                    <th>Contact Number</th>
                    <th>Alternative Number</th>
                    <th>Vendor Name</th>
                    <th>Address</th>
                    <th>GST Number</th>
                    <th>Start Date</th>
                    <th>Pincode</th>
                    <th>Website</th>
                    <th>Action</th> <!-- Added Action column -->
                </tr>
            </thead>
            <tbody id="vendorTableBody">
                <!-- Vendor details will be populated here using AJAX -->
            </tbody>
        </table>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="vendorModal" tabindex="-1" aria-labelledby="vendorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="vendorModalLabel">Vendor Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="vendorDetailsDiv">
                <!-- Vendor details will be populated here -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- <!-- product --> 

<div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel">Product Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="productDetailsDiv">
                <!-- Product details will be populated here -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#viewProductBtn').click(function () {
            $.ajax({
                type: 'GET',
                url: 'viewproduct', // URL mapped to your Spring MVC controller method for product details
                dataType: 'json',
                success: function (data) {
                    $('#productTableBody').empty(); // Clear existing table rows
                    $.each(data, function (index, product) {
                        $('#productTableBody').append('<tr>' +
                            '<td>' + product.id + '</td>' +
                            '<td>' + product.vendorName + '</td>' +
                            '<td>' + product.category + '</td>' +
                            '<td>' + product.productName + '</td>' +
                            '<td>' + product.productPrice + '</td>' +
                            '<td>' + product.deliveryCharge + '</td>' +
                            '<td>' + product.description + '</td>' +
                            '<td>' + product.available + '</td>' +
                            '<td><button class="btn btn-primary btn-sm viewProductBtn">View</button></td>' +
                            '</tr>');
                    });
                    $('#productTableDiv').show(); // Show the product table
                    $('#vendorTableDiv').hide(); // Hide the vendor table
                },
                error: function (xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        });

        $('#viewVendorBtn').click(function () {
            $.ajax({
                type: 'GET',
                url: 'viewVendorDetails', // URL mapped to your Spring MVC controller method for vendor details
                dataType: 'json',
                success: function (data) {
                    $('#vendorTableBody').empty(); // Clear existing table rows
                    $.each(data, function (index, vendor) {
                        $('#vendorTableBody').append('<tr>' +
                            '<td>' + vendor.id + '</td>' +
                            '<td><img src="' + vendor.imagePath + '" alt="Profile Picture" width="50" height="50"></td>' +
                            '<td>' + vendor.ownerName + '</td>' +
                            '<td>' + vendor.email + '</td>' +
                            '<td>' + vendor.contactNo + '</td>' +
                            '<td>' + vendor.alternativeNo + '</td>' +
                            '<td>' + vendor.vendorName + '</td>' +
                            '<td>' + vendor.address + '</td>' +
                            '<td>' + vendor.gstNo + '</td>' +
                            '<td>' + vendor.companyStartDate + '</td>' +
                            '<td>' + vendor.pincode + '</td>' +
                            '<td>' + vendor.website + '</td>' +
                            '<td><button class="btn btn-primary btn-sm viewVendorBtn">View</button></td>' +
                            '</tr>');
                    });
                    $('#productTableDiv').hide(); // Hide the product table
                    $('#vendorTableDiv').show(); // Show the vendor table
                },
                error: function (xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        });

        // Click event handler for dynamically added "View" buttons in the vendor table
        $(document).on('click', '.viewVendorBtn', function () {
            var rowData = $(this).closest('tr').find('td'); // Get all <td> elements in the clicked row
            populateVendorDetails(rowData); // Function to populate the vendor details
        });

        // Click event handler for dynamically added "View" buttons in the product table
        $(document).on('click', '.viewProductBtn', function () {
            var rowData = $(this).closest('tr').find('td'); // Get all <td> elements in the clicked row
            populateProductDetails(rowData); // Function to populate the product details
        });

        // Function to populate the vendor details in the modal
       function populateVendorDetails(rowData) {
    var vendorDetails = '<div>';
    vendorDetails += '<p><strong>ID:</strong> ' + $(rowData[0]).text() + '</p>';
    vendorDetails += '<p><strong>Image Path:</strong> ' + $(rowData[1]).find('img').attr('src') + '</p>';
    vendorDetails += '<p><strong>Owner Name:</strong> ' + $(rowData[2]).text() + '</p>';
    vendorDetails += '<p><strong>Email:</strong> ' + $(rowData[3]).text() + '</p>';
    vendorDetails += '<p><strong>Contact Number:</strong> ' + $(rowData[4]).text() + '</p>';
    vendorDetails += '<p><strong>Alternative Number:</strong> ' + $(rowData[5]).text() + '</p>';
    vendorDetails += '<p><strong>Vendor Name:</strong> ' + $(rowData[6]).text() + '</p>';
    vendorDetails += '<p><strong>Address:</strong> ' + $(rowData[7]).text() + '</p>';
    vendorDetails += '<p><strong>GST Number:</strong> ' + $(rowData[8]).text() + '</p>';
    vendorDetails += '<p><strong>Start Date:</strong> ' + $(rowData[9]).text() + '</p>';
    vendorDetails += '<p><strong>Pincode:</strong> ' + $(rowData[10]).text() + '</p>';
    vendorDetails += '<p><strong>Website:</strong> ' + $(rowData[11]).text() + '</p>';
    // Add more vendor details if needed
    vendorDetails += '</div>';

    $('#vendorDetailsDiv').html(vendorDetails);

    // Show the popup modal
    $('#vendorModal').modal('show');
}


        // Function to populate the product details in the modal
// Function to populate the product details in the modal
function populateProductDetails(rowData) {
    var productDetails = '<div>';
    productDetails += '<p><strong>ID:</strong> ' + $(rowData[0]).text() + '</p>';
    productDetails += '<p><strong>Vendor Name:</strong> ' + $(rowData[1]).text() + '</p>';
    productDetails += '<p><strong>Category:</strong> ' + $(rowData[2]).text() + '</p>';
    productDetails += '<p><strong>Product Name:</strong> ' + $(rowData[3]).text() + '</p>';
    productDetails += '<p><strong>Product Price:</strong> ' + $(rowData[4]).text() + '</p>';
    productDetails += '<p><strong>Delivery Charge:</strong> ' + $(rowData[5]).text() + '</p>';
    productDetails += '<p><strong>Description:</strong> ' + $(rowData[6]).text() + '</p>';
    productDetails += '<p><strong>Available:</strong> ' + $(rowData[7]).text() + '</p>';
    
    // Additional input fields for quantity and delivery date
    productDetails += '<label for="quantity">Quantity:</label>';
    productDetails += '<input type="number" id="quantity" name="quantity"><br>';
    productDetails += '<label for="deliveryDate">Delivery Date:</label>';
    productDetails += '<input type="date" id="deliveryDate" name="deliveryDate"><br>';

    // Add more product details if needed
    productDetails += '</div>';

    $('#productDetailsDiv').html(productDetails);

    // Show the popup modal
    $('#productModal').modal('show');
}


        // Close the popup form when the close button is clicked
        $('.close').click(function () {
            $('#vendorModal').modal('hide');
        });

        // Close the popup form when clicked outside of it
        $(window).click(function (e) {
            if (e.target == $('#vendorModal')[0]) {
                $('#vendorModal').modal('hide');
            }
        });
    });
</script>

</body>
</html>
