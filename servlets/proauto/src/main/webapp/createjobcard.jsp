<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	//$( function() {
	//} );

	$(document).on("click", ".insertRow", function(event) {
		 $('#customFields').append('<tr><td>'+
		    		'<select class="joboperationbox" name="jobop" id="jobop">		 		<option value="">Select Operation</option>						<jstl:forEach items="${operations}" var="eachOp">				  			<option value="${eachOp.key}">${eachOp.value}</option>						</jstl:forEach>				</select>				<input type="text" class="joboperationbox" id="notes"								name="notes" value="" placeholder="Notes" /> &nbsp;				<input type="text" class="joboperationbox" id="assignee"								name="assignee" value="" placeholder="Assignee" /> &nbsp;				<input type="text" class="joboperationbox" id="startTime"								name="startTime" value="" placeholder="Start Time" /> &nbsp;				<input type="text" class="joboperationbox" id="endTime"								name="endTime" value="" placeholder="End Time" /> &nbsp;				<input type="text" class="joboperationbox" id="duration"								name="duration" value="" placeholder="Duration(Mins)" /> &nbsp;				<select class="joboperationbox" name="machine" id="machine"> 			 		<option value="">Machine</option>						<jstl:forEach items="${machines}" var="eachMachine">				  			<option value="${eachMachine.key}">${eachMachine.value}</option>						</jstl:forEach>				</select> &nbsp;				<input type="text" class="joboperationbox" id="cost"								name="cost" value="" placeholder="Cost" /> &nbsp;				<select class="joboperationbox" name="taskStatus" id="taskStatus"> 			 		<option value="">State</option>						<jstl:forEach items="${states}" var="eachState">				  			<option value="${eachState.key}">${eachState.value}</option>						</jstl:forEach>				</select> &nbsp;'+
		    		'<input type="button" class="deleteButton" value="Delete" />'+
		    		'</td></tr>')
		
	});
	$(document).on("click", ".deleteButton", function(event) {
	    $(this).closest('tr').remove();
		
	});
	
	$(document).ready(
			function() {
				
				function customLoad() {
				}
				
				

				$("#jobStart").datepicker();
				$("#jobEnd").datepicker();
				$('#jobcardRegForm').validate(
						{
							rules : {

							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							},
							success : function(element) {
								element.text('OK!').addClass('valid').closest(
										'.control-group').removeClass('error')
										.addClass('success');
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
					<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="pageHeadings"> Job Cards</div>
					<br>
					<div class="formDiv">
					<jstl:if
							test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
						      			<label>Create Job Card</label>
					</jstl:if>
					<jstl:if
							test="${jobCardSelected.id != null && jobCardSelected.id > 0}">
						      			<label>Update Job Card</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="jobcardRegForm" role="form" action="createjobcard" method="post">  
		
		
		      <input type="hidden" name="regType" value="jobcard">
			  <input type="hidden" name="jid" id="jid"
								value="${jobCardSelected.id}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		   
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="jname">Name:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="jname"
											name="jname" value="${jobCardSelected.name}">
			    	 	</div>
		    	 	</div>
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="jdesc">Description:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="jdesc"
											name="jdesc" value="${jobCardSelected.desc}">
			    	 	</div>
		    	 	</div>
		    	</div>
		    	 	
		    	 
		    	 <div class="row rowspace">
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="customer">Customer:</label>
		    			</div>
			   		 	<div class="col-sm-2 col-md-2">
				 			<select class="form-control" name="customer" id="customer">
				 			 	<option value="">Select Customer</option>
		  						<jstl:forEach items="${customers}" var="eachCustomer">
		  				  			<option value="${eachCustomer.key}"
													${jobCardSelected.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
			    	 </div>
			    	 
			    	 <div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="po">PO:</label>
		    			</div>
			   		 	<div class="col-sm-2 col-md-2">
				 			<select class="form-control" name="po" id="po">
				 			 	<option value="">Select PO</option>
		  						<jstl:forEach items="${poList}" var="eachPo">
		  				  			<option value="${eachPo.key}"
													${jobCardSelected.po == eachPo.key ? 'selected' : ''}>${eachPo.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
			    	 </div>
			    	 
			    	  <div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="status">Status:</label>
		    			</div>
			   		 	<div class="col-sm-2 col-md-2">
				 			<select class="form-control" name="status" id="status">
				 			 	<option value="">State</option>
		  						<jstl:forEach items="${states}" var="eachState">
		  				  			<option value="${eachState.key}"
													${jobCardSelected.state == eachState.key ? 'selected' : ''}>${eachState.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
			    	 </div>
		    	</div>
		    	
		      <div class="row rowspace">
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="createdBy" class="control-label">Created By:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="createdBy"
											name="createdBy" value="${jobCardSelected.createdBy}">
			    	 	</div>
			    	 </div>
			    	
			    	 <div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="jobStart" class="control-label">Start Date:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="jobStart"
											name="jobStart" readonly="true"
											value="${jobCardSelected.jobStart}">
			    	 	</div>
			    	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="jobEnd" class="control-label">End Date:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="jobEnd"
											name="jobEnd" readonly="true"
											value="${jobCardSelected.jobEnd}">
			    	 	</div>
			    	</div>
		    	</div>
		    	<br>
		      <div class="row">
  	  		   		<div class="col-sm-12 col-md-12">
		      			<label>Job Operations</label>
		    		</div>
  	  		   </div>
  	  		   
  	  		   
  	  		    <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
  	  		   <jstl:if
								test="${jobCardSelected.id != null && jobCardSelected.id > 0 && jobCardSelected.tasks!=null}">
	  	  		   <div class="row rowspace">
			   		 	<div id="alreadySavedContacts" class="col-sm-12 col-md-12">
			   		 		<table class="form-table" id="customFieldsExisting">
				   		 	<jstl:forEach var="eachTask"
												items="${jobCardSelected.tasks}">
				   			 	<jstl:set var="splittedString"
													value="${fn:split(eachTask, '[|]')}" />
				                <tr>
					               <td>
										<select class="joboperationbox" name="jobop" id="jobop">
						 			 		<option value="">Select Operation</option>
					  						<jstl:forEach items="${operations}" var="eachOp">
					  				  			<option value="${eachOp.key}" ${splittedString[0] == eachOp.key ? 'selected' : ''}>${eachOp.value}</option>
					  						</jstl:forEach>
										</select>
										<input type="text" class="joboperationbox" id="notes"
														name="notes" value="${splittedString[1]}" placeholder="Notes" /> &nbsp;
										<input type="text" class="joboperationbox" id="assignee"
														name="assignee" value="${splittedString[2]}" placeholder="Assignee" /> &nbsp;
										<input type="text" class="joboperationbox" id="startTime"
														name="startTime" value="${splittedString[3]}" placeholder="Start Time" /> &nbsp;
										<input type="text" class="joboperationbox" id="endTime"
														name="endTime" value="${splittedString[4]}" placeholder="End Time" /> &nbsp;
										<input type="text" class="joboperationbox" id="duration"
														name="duration" value="${splittedString[5]}" placeholder="Duration(Mins)" /> &nbsp;
										<select class="joboperationbox" name="machine" id="machine">
						 			 		<option value="">Machine</option>
					  						<jstl:forEach items="${machines}" var="eachMachine">
					  				  			<option value="${eachMachine.key}" ${splittedString[6] == eachMachine.key ? 'selected' : ''}>${eachMachine.value}</option>
					  						</jstl:forEach>
										</select> &nbsp;
										<input type="text" class="joboperationbox" id="cost"
														name="cost" value="${splittedString[7]}" placeholder="Cost" /> &nbsp;
										<select class="joboperationbox" name="taskStatus" id="taskStatus">
						 			 		<option value="">State</option>
					  						<jstl:forEach items="${states}" var="eachState">
					  				  			<option value="${eachState.key}" ${splittedString[8] == eachState.key ? 'selected' : ''}>${eachState.value}</option>
					  						</jstl:forEach>
										</select> &nbsp;
										<input type="button" class="deleteButton" value="Delete" />
								</td>
				                </tr>
			           		 </jstl:forEach>
			           		 </table>
			    	 	</div>
				   </div>
				</jstl:if>							
  	  		   
  	  		   </div>
  	  		   </div>
			  	<div class="row rowspace">
  	  		 
  	  				 <div class="col-sm-12 col-md-12">
	  	  		   	
	  	  		   	<table class="form-table" id="customFields">
						<tr>
							<td>
								<select class="joboperationbox" name="jobop" id="jobop">
				 			 		<option value="">Select Operation</option>
			  						<jstl:forEach items="${operations}" var="eachOp">
			  				  			<option value="${eachOp.key}">${eachOp.value}</option>
			  						</jstl:forEach>
								</select>
								<input type="text" class="joboperationbox" id="notes"
												name="notes" value="" placeholder="Notes" /> &nbsp;
								<input type="text" class="joboperationbox" id="assignee"
												name="assignee" value="" placeholder="Assignee" /> &nbsp;
								<input type="text" class="joboperationbox" id="startTime"
												name="startTime" value="" placeholder="Start Time" /> &nbsp;
								<input type="text" class="joboperationbox" id="endTime"
												name="endTime" value="" placeholder="End Time" /> &nbsp;
								<input type="text" class="joboperationbox" id="duration"
												name="duration" value="" placeholder="Duration(Mins)" /> &nbsp;
								<select class="joboperationbox" name="machine" id="machine">
				 			 		<option value="">Machine</option>
			  						<jstl:forEach items="${machines}" var="eachMachine">
			  				  			<option value="${eachMachine.key}">${eachMachine.value}</option>
			  						</jstl:forEach>
								</select> &nbsp;
								<input type="text" class="joboperationbox" id="cost"
												name="cost" value="" placeholder="Cost" /> &nbsp;
								<select class="joboperationbox" name="taskStatus" id="taskStatus">
				 			 		<option value="">State</option>
			  						<jstl:forEach items="${states}" var="eachState">
			  				  			<option value="${eachState.key}">${eachState.value}</option>
			  						</jstl:forEach>
								</select> &nbsp;
							</td>
						</tr>
					</table>
		    	</div>
		    	</div>
		    	
		    	
		     <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
		      			    <p id="insertRowParent">
    							<input class="insertRow" type="button" value="Insert row">
							</p>
		    		</div>
  	  		   </div>
		    	
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
						<jstl:if
										test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <button id="createJobcardSubmit" type="submit"
											class="btn btn-primary">Create</button>
						      
						</jstl:if>
					<jstl:if
										test="${jobCardSelected.id != null && jobCardSelected.id > 0}">
							  <input type="hidden" name="create" value="false">
						      <button id="createJobcardSubmit" type="submit"
											class="btn btn-primary">Update</button>
						      
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
											href="${pageContext.request.contextPath}/createjobcard"
											role="button">Cancel</a>
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
	   		 					<form id="jobcardSearchForm" role="form"
						action="createjobcard" method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-1 col-md-1">
						      			<label for="searchJobcardInput">Search Jobcard</label>
						    		</div>
						   		 	<div class="col-sm-2 col-md-2">
						      			<input type="text" class="form-control"
									id="searchJobcardInput" name="searchJobcardInput">
						    	 	</div>
						    	 	<div class="col-sm-1 col-md-1">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of Jobcards</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								    	<th>Name</th>
								        <th>Description</th>
										<th>PO</th>
										<th>Customer</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="jobcard" items="${jobcardList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/createjobcard?jobcardSelected=${jobcard.id}">
								                    	<jstl:out value="${jobcard.name}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${jobcard.desc}" /></td>
							                    <td><jstl:out value="${jobcard.po}" /></td>
							                    <td><jstl:out
											value="${jobcard.customer}" /></td>
							                    
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
  	  		
					</jstl:if>
		
</div>
</jsp:body>


</t:layout>

