<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
	<link rel="stylesheet" type="text/css" href="css/custom.css">
	<title>Test Project</title>
</head>

<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-2 offset-sm-1">
					<div class="space">
						<button type="button" class="btn btn-success" data-toggle="modal" data-target="#formModal">Add New User</button>
					</div>
				</div>
			</div>
			<div class="row justify-content-sm-center">
				<h2 class="head">Stored User List</h2> </div>
			<div class="row justify-content-sm-center">
				<div class="col-sm-12 col-md-8 col-lg-6">
					<table class="table table-striped" id="table">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<!-- modal to use-->
		<div class="modal" id="formModal" tabindex="-1" role="dialog" aria-labelledby="formModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<div class="modal-title">
							<div class="container">
								<div class="row">
									<div class="col-sm-10">
										<h2 class="">Add New User</h2> </div>
									<div class="col-sm-2">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group row">
								<div class="col-sm-3">
									<label for="fname" class="col-form-label">First Name :</label>
								</div>
								<div class="col-sm-9">
									<input type="text" name="fname" id="fname" class="form-control" placeholder="First Name"> </div>
							</div>
							<div class="form-group row">
								<div class="col-sm-3">
									<label for="lname" class="col-form-label">Last Name :</label>
								</div>
								<div class="col-sm-9">
									<input type="text" name="lname" id="lname" class="form-control" placeholder="Last Name"> </div>
							</div>
							<div class="form-group row">
								<div class="col-sm-3">
									<label for="email" class="col-form-label">Email :</label>
								</div>
								<div class="col-sm-9">
									<input type="email" name="email" id="email" class="form-control" placeholder="Email"> </div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-success" id="addbutton" onclick="addfunction">Add</button>
						<div class="loader" style="display:none;" id="addbuttonloader"></div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>