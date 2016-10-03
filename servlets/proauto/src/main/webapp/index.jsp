<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>ProAuto - Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=300px, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.center
	{
	  position: absolute;
	  width: 300px;
	  height: 200px;
	  z-index: 15;
	  top: 50%;
	  left: 50%;
	  margin: -100px 0 0 -150px;
	}
  </style>
</head>
<body>

<div class="container center">
  <form method="post" action="AuthenticateUser">
    <div class="form-group">
      <label for="userid">User Id:</label>
      <input type="text" class="form-control" id="userid" placeholder="Enter UserId" name="userid">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>
    
	<input type=submit value="Login">
  </form>
</div>

</body>
</html>

