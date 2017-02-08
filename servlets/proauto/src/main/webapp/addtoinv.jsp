<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	function customLoad() {
	}
	$(document).ready(function() {

		$("#date").datepicker();
	});
</script>
    </jsp:attribute>
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
				
			
			<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="pageHeadings">Inventory</div>
					<br>
					<div class="formDiv">
					<label>Add item to inventory</label>
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="addtoinvform" role="form" action="addtoinv" method="post">  
		
		
		      <input type="hidden" name="regType" value="inventory">
			  <input type="hidden" name="parid" id="parid" value="${purchase.id}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		   
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="particular">Particular:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="particular"
											name="particular" value="">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="code">Code:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="code"
											name="code" value="">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="make">Make:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="make"
											name="make" value="">
			    	 	</div>
		    	 	 </div>
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="quantity">Quantity:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="quantity"
											name="quantity" value="">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="unit">Unit:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="unit"
											name="unit" value="">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="date" class="control-label">Date:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="date"
											name="date" readonly="true" value="">
			    	 	</div>
			    	</div>
			    	<div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="authoured">Authoured:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="authoured"
											name="authoured" value="">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="desc">Desc:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="desc"
											name="desc" value="">
			    	 	</div>
		    	 	 </div>
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="type">Type:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="type"
											name="type" value="">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="repo">Repository:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" readonly="readonly" id="repo"
											name="repo" value="">
			    	 	</div>
		    	 	 </div>
		    	</div>
		    </form>
		    </div>
		    </div>
		    </jstl:if>
</div>
</jsp:body>
</t:layout>

