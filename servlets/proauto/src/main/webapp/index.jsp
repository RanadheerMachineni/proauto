<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
	<jsp:body>
<div class="formcenter container">
	<jstl:if test="${empty role}">
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
  </jstl:if>
</div>    
</jsp:body>
</t:layout>

