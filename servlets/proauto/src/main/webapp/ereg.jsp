<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
	<jsp:body>
<div class="container">
	<jstl:if test="${empty role}">
		<p>You are not logged in. Go to the <a
						href="${pageContext.request.contextPath}/index.jsp">Login</a>.</p>
	</jstl:if>
	<jstl:if test="${not empty role}">
		<div class="pageHeadings"> Employee Registration</div>
		<form role="form" action="Registrations" method="post">  
			<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
			   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empName">Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="empName" name="empName">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empPassword">Password:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="empPassword" name="empPassword">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empEmail">Email:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="empEmail" name="empEmail">
		    	 	</div>
  	  		   </div>
			   <br>
 	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empRole">Role:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<select name="empRole">  
								<option>jobcard</option>  
								<option>costing</option>
								<option>dms</option>
								<option>admin</option>  
						</select>  
		    	 	</div>
  	  		   </div>
  	  		   <br>
				<div class="row">
		   		 <div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4">
					<input type=submit value="Register">
		    	 </div>
		  	</div>
  	  		</div>
		</form>	
	</jstl:if>
</div>
</jsp:body>
</t:layout>

