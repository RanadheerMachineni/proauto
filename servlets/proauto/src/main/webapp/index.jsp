<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
	<jsp:body>
<div class="container">
	<jstl:if test="${empty role}">
	  <form role="form" method="post" action="AuthenticateUser">
		  <div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
		   	<br></br>
		  	<div class="row">
				<div class="col-sm-4 col-md-4">
		      		<label for="userid">User Id:</label>
		    	</div>
		   		 <div class="col-sm-6 col-md-6">
		      		<input type="text" class="form-control" id="userid"
								placeholder="Enter UserId" name="userid">
		    	</div>
		  	</div>
		  	<br></br>
		  	<div class="row">
				<div class="col-sm-4 col-md-4">
		      		<label for="pwd">Password:</label>
		    	</div>
		   		 <div class="col-sm-6 col-md-6">
		      		<input type="password" class="form-control" id="password"
								placeholder="Enter password" name="password">
		    	</div>
		  	</div>
		  	<br></br>
		  	<div class="row">
		   		 <div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4">
					<input type=submit value="Login">
		    	 </div>
		  	</div>
		  </div>
	  </form>			
  </jstl:if>
</div>    
</jsp:body>
</t:layout>

