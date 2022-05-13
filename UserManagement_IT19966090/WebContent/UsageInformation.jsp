<%@page import="com.UsageInformation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/UsageInformation.js"></script>
</head>
<body>
<div class="container">

		<div class="row">
			<div class="col-6">
				<h1>User Management V10.1</h1>
				<form id="formUsageInformation" name="formUsageInformation">
					User Name: <input id="userName" name="userName"
						type="text" class="form-control form-control-sm"> <br>
					Address: <input id="address"
						name="address" type="text"
						class="form-control form-control-sm"> <br> 
					Number of Units: <input
						id="noOfUnit" name="noOfUnit" type="text"
						class="form-control form-control-sm"> <br> 
					Month:
					<input id="month" name="month" type="text"
						class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hideUsageInformationIDSave" name="hideUsageInformationIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divUsageInformationGrid">
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>