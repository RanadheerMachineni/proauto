<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	//$( function() {
	//} );
	function customLoad() {
	}

	$(document)
			.ready(
					function() {
						$("#poDate").datepicker();

						
						$('#poRegForm').validate(
								{
									rules : {
										poNumber : {
											minlength : 2,
											required : true
										},
										quantity : {
											number : true
										},
										poDate : {
											required : true
										},
										poSender : {
											required : true
										},
										poSenderDetails : {
											minlength : 2,
											required : true
										},
										matNo : {
											required : true
										},
										matDesc : {
											required : true
										}
									},
									highlight : function(element) {
										$(element).closest('.control-group')
												.removeClass('success')
												.addClass('error');
									},
									success : function(element) {
										element.text('OK!').addClass('valid')
												.closest('.control-group')
												.removeClass('error').addClass(
														'success');
									}
								});

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
					<div class="col-md-10 col-sm-10 col-xs-10">
					<div class="pageHeadings"> Purchase Order</div>
					<br>
					<div class="formDiv">
					<jstl:if
							test="${poSelected.pid == null || poSelected.pid<=0}">
						      			<label>Create Purchase Order</label>
					</jstl:if>
					<jstl:if
							test="${poSelected.pid != null && poSelected.pid > 0}">
						      			<label>Update Purchase Order</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="poRegForm" role="form"
							action="poreg" method="post"							>  
		
		
		      <input type="hidden" name="regType" value="po">
			  <input type="hidden" name="pid" id="pid"
								value="${poSelected.pid}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="poNumber">PO No:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="poNumber"
											name="poNumber" value="${poSelected.poId}">
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poVersion" class="control-label">PO Version:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="poVersion"
											name="poVersion" value="${poSelected.version}">
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poDate" class="control-label">PO Date:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="poDate"
											name="poDate" value="${poSelected.date}">
			    	 	</div>
			    	 </div>
		    	</div>
		    	
  				<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="vnoSender">Vendor No:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="vnoSender"
											name="vnoSender" value="${poSelected.vendor}">
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poSender" class="control-label">Sender:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="poSender"
											name="poSender" value="${poSelected.sender}">
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poSenderDetails" class="control-label">Sender Details:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="poSenderDetails"
											name="poSenderDetails" value="${poSelected.senderDetails}">
			    	 	</div>
			    	 </div>
		    	</div>
  	  		  	
  	  		   
  				<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="senderEmail">Sender Email:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="senderEmail"
											name="senderEmail" value="${poSelected.senderEmail}">
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="senderPhone" class="control-label">Sender Phone:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="senderPhone"
											name="senderPhone" value="${poSelected.senderPhone}">
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="senderFax" class="control-label">Sender Fax:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="senderFax"
											name="senderFax" value="${poSelected.senderFax}">
			    	 	</div>
			    	 </div>
		    	</div>
			  
			  	<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="notes">Notes:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="notes"
											name="notes" value="${poSelected.notes}">
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="matNo" class="control-label">Mat No:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="matNo"
											name="matNo" value="${poSelected.matNo}">
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="matDesc" class="control-label">Mat Desc:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="matDesc"
											name="matDesc" value="${poSelected.matDesc}">
			    	 	</div>
			    	 </div>
		    	</div>
		    	
		    	<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="unitPrice">Unit Price:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="unitPrice"
											name="unitPrice" value="${poSelected.unitPrice}">
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="quantity" class="control-label">Quantity:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="quantity"
											name="quantity" value="${poSelected.quantity}">
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="discount" class="control-label">Discount:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="discount"
											name="discount" value="${poSelected.discount}">
			    	 	</div>
			    	 </div>
		    	</div>
		    	
		    	<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="value">Total value:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="value"
											name="value" value="${poSelected.value}">
			    	 	</div>
		    	 	</div>
		    	</div>
  	  		   <br>
  	  		   
			   <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
						<jstl:if
							test="${poSelected.pid == null || poSelected.pid<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <!--  <input id="createEmployeeSubmit" type=submit value="Create">-->
						      <button id="createEmployeeSubmit" type="submit"
								class="btn btn-primary">Create</button>
						      
						</jstl:if>
					<jstl:if
							test="${poSelected.pid != null && poSelected.pid > 0}">
							  <input type="hidden" name="create" value="false">
						      <!-- <input id="createEmployeeSubmit" type=submit value="Update"> -->
						      <button id="createEmployeeSubmit" type="submit"
								class="btn btn-primary">Update</button>
						      
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
								href="${pageContext.request.contextPath}/poreg" role="button">Cancel</a>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
		</form>	
		
		</div>
		</div>
  	  		
  	  		<!-- List of emps -->
						<div class="col-md-10 col-sm-10 col-xs-10">
	   		 					<br>
	   		 					<form id="poSearchForm" role="form" action="poreg"
				method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-1 col-md-1">
						      			<label for="searchPoInput">Search Order</label>
						    		</div>
						   		 	<div class="col-sm-2 col-md-2">
						      			<input type="text" class="form-control"
							id="searchPoInput" name="searchPoInput">
						    	 	</div>
						    	 	<div class="col-sm-1 col-md-1">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of existing Orders</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>PO</th>
										<th>Version</th>
										<th>Date</th>
			   					        <th>Sender Details</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="po" items="${poList}">
							                <tr>
							                    <td>
							                    	<a
								href="${pageContext.request.contextPath}/poreg?poSelected=${po.pid}">
								                    	<jstl:out
										value="${po.poId}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${po.version}" /></td>
							                    <td><jstl:out value="${po.date}" /></td>
							                    <td><jstl:out value="${po.senderDetails}" /></td>
							                    
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
  	  		
					</jstl:if>
		
</div>
</jsp:body>


</t:layout>

