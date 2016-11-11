<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:body>
<div class="container">
		<br>
			<jstl:if test="${pageContext.request.userPrincipal.name == null}">
						<div class="col-md-12 col-sm-12 col-xs-12">
		 	 		<div class="row">
	 	  		   		<div class="col-sm-2 col-md-2">
		    			</div>
		    			<div class="col-sm-7 col-md-7">
		      				
		    			</div>
		   		 		<div class="col-sm-3 col-md-3">
							<p>
								You are not logged in.  | <a
									href="${pageContext.request.contextPath}/login.jsp">Please Login</a>
							</p>
		    	 		</div>
	 	  			</div>
	 	  		</div>
			</jstl:if>
			<jstl:if test="${pageContext.request.userPrincipal.name != null}">
			
			
		<div class="pageHeadings"> Customer Registration</div>
		<form role="form" action="Registrations" method="post">  
		    <input type="hidden" name="regType" value="customer">
			<div
						class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
			   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cName">Customer Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cName"
									name="cName">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<textarea class="form-control" id="cAddress" name="cAddress"></textarea>
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cEmail">Email:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cEmail"
									name="cEmail">
		    	 	</div>
  	  		   </div>
			   <br>
 	  		 
 	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cFirstContact">First Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cFirstContact"
									name="cFirstContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cSecondContact">Second Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cSecondContact"
									name="cSecondContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cThirdContact">Third Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cThirdContact"
									name="cThirdContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4">
						<input type=submit value="Register">
	    	 		</div>
	  			</div>
	  			<br>
  	  		</div>
		</form>	
		
		</jstl:if>
</div>
</jsp:body>
</t:layout>

