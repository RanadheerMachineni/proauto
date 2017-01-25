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

	function customLoad() {
	
	}
	
	function switchForm(){
		$('.block').each(function(i, obj) {
			if($(this).hasClass("show")){
				$(this).removeClass("show");
				$(this).addClass("hide");
			}else{
				$(this).addClass("show");
				$(this).removeClass("hide");
			}
		});
	}
	
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
				
				
				
				$('select[name=customer]').change(function(){
				    var customer = $(this).val();
				    $.get("${pageContext.request.contextPath}/getPos",
				    	    {"customer" : customer},
				    	    function(resultJSON) {
				    	    	var result = $.parseJSON(resultJSON);
				    	    	$('#po').empty();
				    	    	$.each(result, function(k, v) {
				    	    	      $('#po').append('<option value="'+k+'">'+v+'</option>');

				    	    	});
				    	    }
				    );
				})

			
				
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
							test="${jobCardSelected.id != null && jobCardSelected.id>0}">
						      			<label>${jobCardSelected.name}</label>
					</jstl:if>
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="jobcardRegForm" role="form" action="createjobcard"
							method="post">  
		
		
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
   		 					<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jname" class="block show">${jobCardSelected.name}</p>
   		 						<input type="text" class="form-control block hide" id="jname"	name="jname" value="${jobCardSelected.name}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="jname"	name="jname" value="${jobCardSelected.name}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="jdesc">Description:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			   		 	
			   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jdesc" class="block show">${jobCardSelected.name}</p>
   		 						<input type="text" class="form-control block hide" id="jdesc"	name="jdesc" value="${jobCardSelected.desc}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="jdesc"	name="jdesc" value="${jobCardSelected.desc}">
							</jstl:if>

			    	 	</div>
		    	 	</div>
		    	</div>
		    	 	
		    	 
		    	 <div class="row rowspace">
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="customer">Customer:</label>
		    			</div>
			   		 	<div class="col-sm-2 col-md-2">
			   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
			   		 			<jstl:forEach items="${customers}" var="eachCustomer">
					   		 		<jstl:if test="${jobCardSelected.customer == eachCustomer.key}">
					   		 			   <p id="customer" class="block show">${eachCustomer.value}</p>
					   		 		</jstl:if>
		  						</jstl:forEach>
		  						<select class="form-control block hide" name="customer" id="customer">
				 			 		<option value="">Select Customer</option>
			  						<jstl:forEach items="${customers}" var="eachCustomer">
			  				  			<option value="${eachCustomer.key}"
														${jobCardSelected.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
							
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<select class="form-control block show" name="customer" id="customer">
				 			 		<option value="">Select Customer</option>
			  						<jstl:forEach items="${customers}" var="eachCustomer">
			  				  			<option value="${eachCustomer.key}"
														${jobCardSelected.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
			    	 	</div>
			    	 </div>
			    	 
			    	 <div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="po">PO:</label>
		    			</div>
		    			 	<div class="col-sm-2 col-md-2">
			   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
			   		 			<jstl:forEach items="${poList}" var="eachPo">
					   		 		<jstl:if test="${jobCardSelected.po == eachPo.key}">
					   		 			   <p id="po" class="block show">${eachPo.value}</p>
					   		 		</jstl:if>
		  						</jstl:forEach>
		  						<select class="form-control block hide" name="po" id="po">
				 			 		<option value="">Select PO</option>
			  						<jstl:forEach items="${poList}" var="eachPo">
			  				  			<option value="${eachPo.key}"
														${jobCardSelected.po == eachPo.key ? 'selected' : ''}>${eachPo.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
							
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<select class="form-control block show" name="po" id="po">
				 			 		<option value="">Select PO</option>
			  						<jstl:forEach items="${poList}" var="eachPo">
			  				  			<option value="${eachPo.key}"
														${jobCardSelected.po == eachPo.key ? 'selected' : ''}>${eachPo.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
			    	 	</div>
			    	 </div>
			    	 
			    	  <div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="status">Status:</label>
		    			</div>
		    			<div class="col-sm-2 col-md-2">
			   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
			   		 			<jstl:forEach items="${states}" var="eachState">
					   		 		<jstl:if test="${jobCardSelected.state == eachState.key}">
					   		 			   <p id="status" class="block show">${eachState.value}</p>
					   		 		</jstl:if>
		  						</jstl:forEach>
		  						<select class="form-control block hide" name="status" id="status">
				 			 		<option value="">State</option>
			  						<jstl:forEach items="${states}" var="eachState">
			  				  			<option value="${eachState.key}"
														${jobCardSelected.state == eachState.key ? 'selected' : ''}>${eachState.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
							
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<select class="form-control block show" name="status" id="status">
				 			 		<option value="">State</option>
			  						<jstl:forEach items="${states}" var="eachState">
			  				  			<option value="${eachState.key}"
														${jobCardSelected.state == eachState.key ? 'selected' : ''}>${eachState.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	</div>
		    	
		      <div class="row rowspace">
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="createdBy" class="control-label">Created By:</label>
			    		</div>
			    		<div class="col-sm-2 col-md-2 controls">
   		 					<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="createdBy" class="block show">${jobCardSelected.createdBy}</p>
   		 						<input type="text" class="form-control block hide" id="createdBy"	name="createdBy" value="${jobCardSelected.createdBy}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="createdBy"	name="createdBy" value="${jobCardSelected.createdBy}">
							</jstl:if>
			    	 	</div>
			    	 </div>
			    	
			    	 <div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="jobStart" class="control-label">Start Date:</label>
			    		</div>
			    		<div class="col-sm-2 col-md-2 controls">
   		 					<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jobStart" class="block show">${jobCardSelected.jobStart}</p>
   		 						<input type="text" class="form-control block hide" id="jobStart" readonly name="jobStart" value="${jobCardSelected.jobStart}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="jobStart" readonly name="jobStart" value="${jobCardSelected.jobStart}">
							</jstl:if>
			    	 	</div>
			    	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="jobEnd" class="control-label">End Date:</label>
			    		</div>
			    		<div class="col-sm-2 col-md-2 controls">
   		 					<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jobEnd" class="block show">${jobCardSelected.jobEnd}</p>
   		 						<input type="text" class="form-control block hide" id="jobEnd" readonly name="jobEnd" value="${jobCardSelected.jobEnd}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="jobEnd" readonly name="jobEnd" value="${jobCardSelected.jobEnd}">
							</jstl:if>
			    	 	</div>
			    	</div>
		    	</div>
		    	<br>
		      <div class="row">
  	  		   		<div class="col-sm-12 col-md-12">
		      			<label>Job Operations</label>
		    		</div>
  	  		   </div>
  	  		   
  	  		   
  	  		  
  	  		   <jstl:if	test="${jobCardSelected.id != null && jobCardSelected.id > 0 && jobCardSelected.tasks!=null}">
	  	  		   <div class="row rowspace">
			   		 	<div id="alreadySavedContacts" class="col-sm-12 col-md-12">
			   		 		<table class="form-table" id="customFieldsExisting">
				   		 	<jstl:forEach var="eachTask" items="${jobCardSelected.tasks}">
				   			 	<jstl:set var="splittedString"
															value="${fn:split(eachTask, '[|]')}" />
				                <tr>
					               <td>
					                 	  		<div class="col-sm-1 col-md-1">
				  								<jstl:forEach items="${operations}" var="eachOp">
															<jstl:if test="${eachOp.key == splittedString[0]}">
											   		 			   <p id="jobop" class="joboperationbox block show">${eachOp.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="joboperationbox block hide" name="jobop" id="jobop">
								 			 		<option value="">Select Operation</option>
							  						<jstl:forEach items="${operations}" var="eachOp">
							  				  			<option value="${eachOp.key}" ${splittedString[0] == eachOp.key ? 'selected' : ''}>${eachOp.value}</option>
							  						</jstl:forEach>
												</select>
												</div>
												<div class="col-sm-1 col-md-1">
												<p id="notes" class="joboperationbox block show">${splittedString[1]}</p>
   		 										<input type="text" class="joboperationbox block hide" id="notes" placeholder="Notes" name="notes" value="${splittedString[1]}">
												</div>
												
												<div class="col-sm-1 col-md-1">
												<p id="assignee" class="joboperationbox block show">${splittedString[2]}</p>
   		 										<input type="text" class="joboperationbox block hide" id="assignee" placeholder="Assignee" name="assignee" value="${splittedString[2]}">
												</div>
												
												<div class="col-sm-1 col-md-1">
												<p id="startTime" class="joboperationbox block show">${splittedString[3]}</p>
   		 										<input type="text" class="joboperationbox block hide" id="startTime" placeholder="Start Time" name="startTime" value="${splittedString[3]}">
												</div>
												
												<div class="col-sm-1 col-md-1">
												<p id="endTime" class="joboperationbox block show">${splittedString[4]}</p>
   		 										<input type="text" class="joboperationbox block hide" id="endTime" placeholder="End Time" name="endTime" value="${splittedString[4]}">
												</div>
												
												<div class="col-sm-1 col-md-1">
												<p id="duration" class="joboperationbox block show">${splittedString[5]}</p>
   		 										<input type="text" class="joboperationbox block hide" id="duration" placeholder="Duration(Mins)" name="duration" value="${splittedString[5]}">
												</div>
												
												<div class="col-sm-1 col-md-1">
												<jstl:forEach items="${machines}" var="eachMachine">
															<jstl:if test="${eachMachine.key == splittedString[6]}">
											   		 			   <p id="machine" class="joboperationbox block show">${eachMachine.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="joboperationbox block hide" name="machine" id="machine">
								 			 		<option value="">Machine</option>
							  						<jstl:forEach items="${machines}" var="eachMachine">
							  				  			<option value="${eachMachine.key}" ${splittedString[6] == eachMachine.key ? 'selected' : ''}>${eachMachine.value}</option>
							  						</jstl:forEach>
												</select>
												 </div>
												
												<div class="col-sm-1 col-md-1">
												<p id="cost" class="joboperationbox block show">${splittedString[7]}</p>
   		 										<input type="text" class="joboperationbox block hide" id="cost" placeholder="Cost" name="cost" value="${splittedString[7]}">
												</div>
												
												<div class="col-sm-1 col-md-1">
												<jstl:forEach items="${states}" var="eachState">
															<jstl:if test="${eachState.key == splittedString[8]}">
											   		 			   <p id="taskStatus" class="joboperationbox block show">${eachState.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="joboperationbox block hide" name="taskStatus" id="taskStatus">
								 			 		<option value="">State</option>
							  						<jstl:forEach items="${states}" var="eachState">
							  				  			<option value="${eachState.key}" ${splittedString[8] == eachMachine.key ? 'selected' : ''}>${eachState.value}</option>
							  						</jstl:forEach>
												</select>
												 </div>
												
												<div class="col-sm-1 col-md-1">
												<input type="button" class="block hide deleteButton" value="Delete" />
												</div>
								</td>
				                </tr>
			           		 </jstl:forEach>
			           		 </table>
			    	 	</div>
				   </div>
				</jstl:if>							
  	  		   
  	  		
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
								<input type="text" class="joboperationbox" id="cost" name="cost"
												value="" placeholder="Cost" /> &nbsp;
								<select class="joboperationbox" name="taskStatus"
												id="taskStatus">
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
		    	
		    	<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
					<div class="row rowspace">
	  	  		   		<div class="col-sm-12 col-md-12">
			      			    <p id="insertRowParent">
	    							<input class="insertRow block show" type="button" value="Insert row">
								</p>
			    		</div>
  	  		   		</div>
				</jstl:if>
	  			<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
					<div class="row rowspace">
	  	  		   		<div class="col-sm-12 col-md-12">
			      			    <p id="insertRowParent">
	    							<input class="insertRow block hide" type="button" value="Insert row">
								</p>
			    		</div>
  	  		   		</div>
				</jstl:if>
		    	
  	  		   <br>
			   <div class="row">
		   		 	<div class="col-sm-1 col-md-1">
		   		 		<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <button id="createJobcardSubmit" type="submit" class="btn btn-primary">Create</button>
						</jstl:if>
						<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id > 0}">
							  <button id="editButton" type="button" onclick="switchForm();return false;" class="btn btn-primary block show">Edit</button>
							  <input type="hidden" name="create" value="false">
						      <button id="createJobcardSubmit" type="submit" class="btn btn-primary block hide">Update</button>
						</jstl:if>
		   		 	</div>
		   		 	<div class="col-sm-1 col-md-1">
		   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id > 0}">
						      <button id="printButton" type="button" onclick="alert('print pdf');" class="btn btn-primary block show">Print</button>
						      <button id="cancelButton" onclick="switchForm();return false;" class="btn btn-primary block hide">Cancel</button>
						</jstl:if>
		   		 	</div>
				</div>
		    	 	
	  			</div>
	  			<br>
	  			<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
		</form>	
		
		</div>
		</div>
  	  		

					</jstl:if>
		
</div>
</jsp:body>


</t:layout>

