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
		var status = $("#status").text();
		if(status=='New'){
			$('.block').each(function(i, obj) {
				if($(this).hasClass("show")){
					$(this).removeClass("show");
					$(this).addClass("hide");
				}else{
					$(this).addClass("show");
					$(this).removeClass("hide");
				}
			});
		}else{
			$('.block').each(function(i, obj) {
				if($(this).hasClass("show") && $(this).hasClass("alwaysEditable")){
					$(this).removeClass("show");
					$(this).addClass("hide");
				}else if($(this).hasClass("hide") && $(this).hasClass("alwaysEditable")){
					$(this).addClass("show");
					$(this).removeClass("hide");
				}
			});
		}
	
	}

	$(document).on("click", ".insertRow", function(event) {
		 $('#customFields').append('<tr>'+
				 '<td class="joboperationbox">  		  <div>    	 	 <select class="form-control" name="jobop" id="jobop">			 		<option value="">Select</option>						<jstl:forEach items="${operations}" var="eachOp">				  			<option value="${eachOp.key}">${eachOp.value}</option>						</jstl:forEach>				</select>			</div>	</td><td class="joboperationbox">						<div>				<input type="text" class="form-control" id="notes"				name="notes" value="" placeholder="Notes" />				</div>	</td> <td class="joboperationbox">				<div>	 <select class="form-control" name="assignee" id="assignee">	 		<option value="">Select</option>			<jstl:forEach items="${employees}" var="eachEmp">	  			<option value="${eachEmp.key}">${eachEmp.value}</option>			</jstl:forEach>	</select>	</div></td><td class="joboperationbox">		<div>	 <select class="form-control" name="programmer" id="programmer">	 		<option value="">Select</option>			<jstl:forEach items="${employees}" var="eachEmp">	  			<option value="${eachEmp.key}">${eachEmp.value}</option>			</jstl:forEach>	</select>	</div></td><td class="joboperationbox">						<div >				<input type="text" class="form-control" id="duration"				name="duration" value="" placeholder="Duration(Mins)" />				</div>	</td><td class="joboperationbox">						<div >				<select class="form-control" name="machine" id="machine">			 		<option value="">Machine</option>						<jstl:forEach items="${machines}" var="eachMachine">				  			<option value="${eachMachine.key}">${eachMachine.value}</option>						</jstl:forEach>				</select>				 </div>	</td><td class="joboperationbox">						<div >				 <input type="text" class="form-control" id="cost" readonly="readonly" name="cost"				value="" placeholder="Cost" />				</div>	</td><td class="joboperationbox">						<div >				<select class="form-control" name="taskStatus"						id="taskStatus"> 					<option value="">State</option>						<jstl:forEach items="${states}" var="eachState">				  			<option value="${eachState.key}">${eachState.value}</option>						</jstl:forEach>				</select>				 </div>	</td><td class="joboperationbox">						<div >				<input type="button" class="form-control deleteButton" value="Delete" />				</div>				 	</td>'
				+'</tr>')
		
	});
	$(document).on("click", ".deleteButton", function(event) {
	    $(this).closest('tr').remove();
		
	});

	
	$(document).ready(
			function() {
				
			    $('#jobStart').datepicker();
				$('#jobEnd').datepicker();
				$('#jobStartExisting').datepicker();
				$('#jobEndExisting').datepicker();
				$('select[name=customer]').change(function(){
				    var customer = $(this).val();
				    $.get("${pageContext.request.contextPath}/getPos",
				    	    {"customer" : customer},
				    	    function(resultJSON) {
				    	    	var result = $.parseJSON(resultJSON);
				    	    	$('#po').empty();
				    	    	$('#po').append('<option>Select Customer</option>');
				    	    	$.each(result, function(k, v) {
				    	    	      $('#po').append('<option value="'+k+'">'+v+'</option>');

				    	    	});
				    	    }
				    );
				})

				$('select[name=po]').change(function(){
				    var po = $(this).val();
				    $.get("${pageContext.request.contextPath}/getPoItems",
				    	    {"po" : po},
				    	    function(resultJSON) {
				    	    	var result = $.parseJSON(resultJSON);
				    	    	$('#poItem').empty();
				    	    	$('#poItem').append('<option>Select PO Item</option>');
				    	    	$.each(result, function(k, v) {
				    	    	      $('#poItem').append('<option value="'+k+'">'+v+'</option>');

				    	    	});
				    	    }
				    );
				})

				$('#jobcardRegForm').validate(
						{
							rules : {
								jname : {
									required : true
								},
								jdesc : {
									required : true
								},
								customer : {
									required : true
								},
								po : {
									required : true
								},
								poItem : {
									required : true
								},
								status : {
									required : true
								},
								createdBy : {
									required : true
								}
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
		      				<label for="poItem">PO Item:</label>
		    			</div>
		    			 	<div class="col-sm-2 col-md-2">
			   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
			   		 			<jstl:forEach items="${jobCardSelected.poItems}" var="eachPoItem">
					   		 		<jstl:if test="${jobCardSelected.SelectedPoItem == eachPoItem.key}">
					   		 			   <p id="poItem" class="block show">${eachPoItem.value}</p>
					   		 		</jstl:if>
		  						</jstl:forEach>
		  						<select class="form-control block hide" name="poItem" id="poItem">
				 			 		<option value="">Select PO Item</option>
			  						<jstl:forEach items="${jobCardSelected.poItems}" var="eachPoItem">
			  				  			<option value="${eachPoItem.key}"
														${jobCardSelected.SelectedPoItem == eachPoItem.key ? 'selected' : ''}>${eachPoItem.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
							
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<select class="form-control block show" name="poItem" id="poItem">
				 			 		<option value="">Select PO Item</option>
			  						<jstl:forEach items="${jobCardSelected.poItems}" var="eachPoItem">
			  				  			<option value="${eachPoItem.key}"
														${jobCardSelected.SelectedPoItem == eachPoItem.key ? 'selected' : ''}>${eachPoItem.value}</option>
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
					   		 			   <p id="status" class="block show alwaysEditable">${eachState.value}</p>
					   		 		</jstl:if>
		  						</jstl:forEach>
		  						<select class="form-control block hide alwaysEditable" name="status" id="status">
				 			 		<option value="">State</option>
			  						<jstl:forEach items="${states}" var="eachState">
			  				  			<option value="${eachState.key}"
														${jobCardSelected.state == eachState.key ? 'selected' : ''}>${eachState.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
							
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<select class="form-control block show alwaysEditable" name="status" id="status">
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
   		 						<p id="jobStart" class="block show alwaysEditable">${jobCardSelected.jobStart}</p>
   		 						<input type="text" class="form-control block hide alwaysEditable" id="jobStartExisting" readonly name="jobStartExisting" value="${jobCardSelected.jobStart}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show alwaysEditable" id="jobStart" readonly name="jobStart" value="${jobCardSelected.jobStart}">
							</jstl:if>
			    	 	</div>
			    	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="jobEnd" class="control-label">End Date:</label>
			    		</div>
			    		<div class="col-sm-2 col-md-2 controls">
   		 					<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jobEnd" class="block show alwaysEditable">${jobCardSelected.jobEnd}</p>
   		 						<input type="text" class="form-control block hide alwaysEditable" id="jobEndExisting" readonly name="jobEndExisting" value="${jobCardSelected.jobEnd}">
							</jstl:if>
							<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show alwaysEditable" id="jobEnd" readonly name="jobEnd" value="${jobCardSelected.jobEnd}">
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
	  	  		   <div class="row rowspace col-sm-12 col-md-12">
			   		 	<div id="alreadySavedContacts" class="control-group">
			   		 		<table id="customFieldsExisting">
				   		 	<jstl:forEach var="eachTask" items="${jobCardSelected.tasks}">
				                <tr>
					               <td class="joboperationbox">
					                 	  		<div>
				  								<jstl:forEach items="${operations}" var="eachOp">
															<jstl:if test="${eachOp.key == eachTask.joId}">
											   		 			   <p id="jobop" class="form-control block show">${eachOp.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="form-control block hide" name="jobop" id="jobop">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${operations}" var="eachOp">
							  				  			<option value="${eachOp.key}" ${eachTask.joId == eachOp.key ? 'selected' : ''}>${eachOp.value}</option>
							  						</jstl:forEach>
												</select>
												</div>
									</td><td class="joboperationbox">			
												<div>
												<p id="notes" class="form-control block show alwaysEditable">${eachTask.notes}</p>
   		 										<input type="text" class="form-control block hide alwaysEditable" id="notes" placeholder="Notes" name="notes" value="${eachTask.notes}">
												</div>
									</td><td class="joboperationbox">
												<div>
												<jstl:forEach items="${employees}" var="eachEmp">
															<jstl:if test="${eachEmp.key == eachTask.assignee}">
											   		 			   <p id="assignee" class="form-control block show">${eachEmp.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="form-control block hide" name="assignee" id="assignee">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}" ${eachTask.assignee == eachEmp.key ? 'selected' : ''}>${eachEmp.value}</option>
							  						</jstl:forEach>
												</select>
												</div>
									</td><td class="joboperationbox">			
												<div>
												<jstl:forEach items="${employees}" var="eachEmp">
															<jstl:if test="${eachEmp.key == eachTask.programmer}">
											   		 			   <p id="programmer" class="form-control block show">${eachEmp.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="form-control block hide" name="programmer" id="programmer">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}" ${eachTask.programmer == eachEmp.key ? 'selected' : ''}>${eachEmp.value}</option>
							  						</jstl:forEach>
												</select>
												</div>
									</td><td class="joboperationbox">			
												<div >
												<p id="duration" class="form-control block show alwaysEditable">${eachTask.timeTaken}</p>
   		 										<input type="text" class="form-control block hide alwaysEditable" id="duration" placeholder="Duration(Mins)" name="duration" value="${eachTask.timeTaken}">
												</div>
									</td><td class="joboperationbox">			
												<div >
												<jstl:forEach items="${machines}" var="eachMachine">
															<jstl:if test="${eachMachine.key == eachTask.machineId}">
											   		 			   <p id="machine" class="form-control block show alwaysEditable">${eachMachine.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="form-control block hide alwaysEditable" name="machine" id="machine">
								 			 		<option value="">Machine</option>
							  						<jstl:forEach items="${machines}" var="eachMachine">
							  				  			<option value="${eachMachine.key}" ${eachTask.machineId == eachMachine.key ? 'selected' : ''}>${eachMachine.value}</option>
							  						</jstl:forEach>
												</select>
												 </div>
									</td><td class="joboperationbox">			
												<div >
												<p id="cost" class="form-control block show">${eachTask.cost}</p>
   		 										<input type="text" class="form-control block hide" id="cost" placeholder="Cost" readonly="readonly" name="cost" value="${eachTask.cost}">
												</div>
									</td><td class="joboperationbox">			
												<div >
												<jstl:forEach items="${states}" var="eachState">
															<jstl:if test="${eachState.key == eachTask.status}">
											   		 			   <p id="taskStatus" class="form-control block show alwaysEditable">${eachState.value}</p>
											   		 		</jstl:if>
											   	</jstl:forEach>
												<select class="form-control block hide alwaysEditable" name="taskStatus" id="taskStatus">
								 			 		<option value="">State</option>
							  						<jstl:forEach items="${states}" var="eachState">
							  				  			<option value="${eachState.key}" ${eachTask.status == eachState.key ? 'selected' : ''}>${eachState.value}</option>
							  						</jstl:forEach>
												</select>
												 </div>
									</td><td class="joboperationbox">			
												<div >
												<input type="button" class="form-control block hide deleteButton" value="Delete" />
												</div>
									</td>
				                </tr>
			           		 </jstl:forEach>
			           		 </table>
			    	 	</div>
				   </div>
				</jstl:if>							
  	  		   
  	  		    <jstl:if	test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
	  	  		   <div class="row rowspace control-group col-sm-12 col-md-12">
			   		 		<table id="customFields" class="form-table block show">
				                <tr>
					               <td class="joboperationbox">
					               		  <div>
					                 	 	 <select class="form-control" name="jobop" id="jobop">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${operations}" var="eachOp">
							  				  			<option value="${eachOp.key}">${eachOp.value}</option>
							  						</jstl:forEach>
												</select>
											</div>
									</td><td class="joboperationbox">			
												<div>
												<input type="text" class="form-control" id="notes"
												name="notes" value="" placeholder="Notes" />
												</div>
									</td><td class="joboperationbox">			
												<div>
												 <select class="form-control" name="assignee" id="assignee">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}">${eachEmp.value}</option>
							  						</jstl:forEach>
												</select>
												</div>
									</td><td class="joboperationbox">			
												<div>
												 <select class="form-control" name="programmer" id="programmer">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}">${eachEmp.value}</option>
							  						</jstl:forEach>
												</select>
												</div>
									</td><td class="joboperationbox">			
												<div >
												<input type="text" class="form-control" id="duration"
												name="duration" value="" placeholder="Duration(Mins)" />
												</div>
									</td><td class="joboperationbox">			
												<div >
												<select class="form-control" name="machine" id="machine">
								 			 		<option value="">Machine</option>
							  						<jstl:forEach items="${machines}" var="eachMachine">
							  				  			<option value="${eachMachine.key}">${eachMachine.value}</option>
							  						</jstl:forEach>
												</select>
												 </div>
									</td><td class="joboperationbox">			
												<div >
												<input type="text" class="form-control" id="cost" name="cost"
												value="" placeholder="Cost" />
												</div>
									</td><td class="joboperationbox">			
												<div >
												<select class="form-control" name="taskStatus"
														id="taskStatus">
					 			 					<option value="">State</option>
							  						<jstl:forEach items="${states}" var="eachState">
							  				  			<option value="${eachState.key}">${eachState.value}</option>
							  						</jstl:forEach>
												</select>
												 </div>
									</td>
				                </tr>
			           		 </table>
				   </div>
				</jstl:if>				
		    	
		    	  <jstl:if	test="${jobCardSelected.id != null && jobCardSelected.id>0}">
	  	  		   <div class="row rowspace control-group col-sm-12 col-md-12">
			   		 		<table id="customFields" class="form-table block hide">
			           		 </table>
				   </div>
				</jstl:if>				
		    	
		    	
		    	
				<br>
		    	<jstl:if test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
					<div class="row rowspace">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			    <p id="insertRowParent">
	    							<input class="form-control insertRow block show btn btn-primary" type="button" value="Add Operation">
								</p>
			    		</div>
  	  		   		</div>
				</jstl:if>
	  			<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id>0}">
					<div class="row rowspace">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			    <p id="insertRowParent">
	    							<input class="form-control insertRow block hide btn btn-primary" type="button" value="Add Operation">
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
							  <button id="editButton" type="button" onclick="switchForm();return false;" class="btn btn-primary block show alwaysEditable">Edit</button>
							  <input type="hidden" name="create" value="false">
						      <button id="createJobcardSubmit" type="submit" class="btn btn-primary block hide alwaysEditable">Update</button>
						</jstl:if>
		   		 	</div>
		   		 	<div class="col-sm-1 col-md-1">
		   		 		<jstl:if test="${jobCardSelected.id != null && jobCardSelected.id > 0}">
						      <button id="printButton" type="button" onclick="alert('print pdf');" class="btn btn-primary block show alwaysEditable">Print</button>
						      <button id="cancelButton" onclick="switchForm();return false;" class="btn btn-primary block hide alwaysEditable">Cancel</button>
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

