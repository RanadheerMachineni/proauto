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

	$(document)
			.ready(
					function() {
						
						
						$('#machineRegForm').validate(
								{
									rules : {
										mName : {
											minlength : 2,
											required : true
										},
										mCode : {
											required : true
										},
										mAxle : {
											required : true
										},
										mCost : {
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
	function customLoad() {
	}
	
</script>
    </jsp:attribute>

	<jsp:body>
<div class="container">
		<input type="hidden" name="guid" value="${guidGenerator.getNextGuid()}">
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
					<div class="pageHeadings"> Machine Registration</div>
					<br>
					<div class="formDiv">
					<jstl:if
							test="${machineSelected.machineId == null || machineSelected.machineId<=0}">
						      			<label>Create Machine</label>
					</jstl:if>
					<jstl:if
							test="${machineSelected.machineId != null && machineSelected.machineId > 0}">
						      			<label>Update Machine</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="machineRegForm" role="form"
							action="mreg" method="post"							>  
		
		
		      <input type="hidden" name="regType" value="machine">
			  <input type="hidden" name="mid" id="mid"
								value="${machineSelected.machineId}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="mName">Description:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="mName"
											name="mName" value="${machineSelected.name}">
			    	 	</div>
		    	 	</div>
		    	</div>
		    	 	
		    	 
		    	 <div class="row rowspace">
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="mCode" class="control-label">Code Type:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="mCode"
											name="mCode" value="${machineSelected.code}">
			    	 	</div>
			    	 </div>
		    	</div>
		    	
		      <div class="row rowspace">
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="mAxle" class="control-label">Axle:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="mAxle"
											name="mAxle" value="${machineSelected.axle}">
			    	 	</div>
			    	 </div>
		    	</div>
		    	
		    	<div class="row rowspace">
			    	 <div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="mCost" class="control-label">Cost:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="mCost"
											name="mCost" value="${machineSelected.cost}">
			    	 	</div>
			    	 </div>
  	  		 	 </div>
  	  		  	
  	  		  	
  	  		   
			  
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
						<jstl:if
							test="${machineSelected.machineId == null || machineSelected.machineId<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <!--  <input id="createEmployeeSubmit" type=submit value="Create">-->
						      <button id="createEmployeeSubmit" type="submit"
								class="btn btn-primary">Create</button>
						      
						</jstl:if>
					<jstl:if
							test="${machineSelected.machineId != null && machineSelected.machineId > 0}">
							  <input type="hidden" name="create" value="false">
						      <!-- <input id="createEmployeeSubmit" type=submit value="Update"> -->
						      <button id="createEmployeeSubmit" type="submit"
								class="btn btn-primary">Update</button>
						      
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
								href="${pageContext.request.contextPath}/mreg" role="button">Cancel</a>
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
	   		 					<form id="machineSearchForm" role="form" action="mreg"
				method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-1 col-md-1">
						      			<label for="searchMachineInput">Search Machine</label>
						    		</div>
						   		 	<div class="col-sm-2 col-md-2">
						      			<input type="text" class="form-control"
							id="searchMachineInput" name="searchMachineInput">
						    	 	</div>
						    	 	<div class="col-sm-1 col-md-1">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of existing Machines</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>Description</th>
										<th>Code type</th>
										<th>Axle</th>
			   					        <th>Cost</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="machine" items="${machineList}">
							                <tr>
							                    <td>
							                    	<a
								href="${pageContext.request.contextPath}/mreg?machineSelected=${machine.machineId}">
								                    	<jstl:out
										value="${machine.name}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${machine.code}" /></td>
							                    <td><jstl:out
									value="${machine.axle}" /></td>
							                    <td><jstl:out value="${machine.cost}" /></td>
							                    
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
  	  		
					</jstl:if>
		
</div>
</jsp:body>


</t:layout>

