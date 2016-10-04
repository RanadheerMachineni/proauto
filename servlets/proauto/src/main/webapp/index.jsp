<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout>
	<jsp:body>
<div class="formcenter container">
  <form method="post" action="AuthenticateUser">
    <div class="form-group">
      <label for="userid">User Id:</label>
      <input type="text" class="form-control" id="userid"
						placeholder="Enter UserId" name="userid">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password"
						placeholder="Enter password" name="password">
    </div>
    
	<input type=submit value="Login">
  </form>
</div>    
</jsp:body>
</t:layout>

