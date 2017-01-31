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
		 $('#customFields').append('<tr>'+
				 '<td class="joboperationbox">		<input type="text" class="form-control rowspace" id="matNo"							name="matNo" value="" placeholder="Mat No" />		</td><td class="joboperationbox">														<input type="text" class="form-control rowspace" id="matDesc" name="matDesc"							value="" placeholder="Description" />		</td><td class="joboperationbox">														<input type="text" class="form-control rowspace" id="unitPrice" name="unitPrice"							value="" placeholder="Unit Price" />		</td><td class="joboperationbox">														<input type="text" class="form-control rowspace" id="quantity" name="quantity"							value="" placeholder="Quantity" />		</td><td class="joboperationbox">														<input type="text" class="form-control rowspace" id="discount" name="discount"							value="" placeholder="Discount" />		</td><td class="joboperationbox">														<input type="text" class="form-control rowspace" id="value" name="value"							value="" placeholder="Value" />		</td><td class="joboperationbox">						<div >				<input type="button" class="form-control deleteButton" value="Delete" />				</div>				 	</td>'
				 +'</tr>');
	});
	$(document).on("click", ".deleteButton", function(event) {
	    $(this).closest('tr').remove();
		
	});

	function removeFile(fileName) {
		//alert(fileName);
		var existingInputValue = $('input#uploadedFiles').val();
		var newValue = existingInputValue.replace(fileName, "");
		$('input#uploadedFiles').val(newValue);
		$('#alreadyUploadedFiles tr').each(function() {
			var href = $(this).find("td:first").find("a").attr('href');
			if (href.toLowerCase().indexOf(fileName.toLowerCase()) >= 0) {
				$(this).closest('tr').remove();
			}
		});
	}

	function addMoreFiles() {
		$("#fileuploads").append(document.createElement("br"));
		$("#fileuploads").append(
				'<input type="file" name="eFiles" id="eFiles" multiple>');
	}

	function unSelectFiles() {
		//$("#eFiles").val('');

		$('input[name="eFiles"]').each(function() {
			$(this).val('');
		});

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
											required: true
										},
										customer :{
											required: true
										},
										quantity : {
											number : true
										},
										poDate : {
											required: true
										},
										poSender : {
											required : true
										},
										poSenderDetails : {
											minlength : 2,
											required: true
										}
									},
									highlight : function(element) {
										$(element).closest('.control-group')
												.removeClass('success')
												.addClass('error');
									}
									,
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
					<div class="col-md-11 col-sm-11 col-xs-11">
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
							action="poreg?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">							  
		
		
		      <input type="hidden" name="regType" value="po">
			  <input type="hidden" name="pid" id="pid"
								value="${poSelected.pid}">
			  <input type="hidden" name="uploadedFiles" id="uploadedFiles"
								value="${poSelected.files}">	
			   <br>
			   
			   <div class="row rowspace">
		    	 	<div class="col-sm-2 col-md-2">
		      			<label for="customer">Customer:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
			 			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
			   		 			<jstl:forEach items="${customers}" var="eachCustomer">
					   		 		<jstl:if test="${poSelected.customer == eachCustomer.key}">
					   		 			   <p id="customer" class="block show">${poSelected.customer}</p>
					   		 		</jstl:if>
		  						</jstl:forEach>
		  						<select class="form-control block hide" name="customer" id="customer">
				 			 		<option value="">Select Customer</option>
			  						<jstl:forEach items="${customers}" var="eachCustomer">
			  				  			<option value="${eachCustomer.key}"
														${poSelected.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
							
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<select class="form-control block show" name="customer" id="customer">
				 			 		<option value="">Select Customer</option>
			  						<jstl:forEach items="${customers}" var="eachCustomer">
			  				  			<option value="${eachCustomer.key}"
														${poSelected.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
			  						</jstl:forEach>
								</select>
							</jstl:if>
		    	 	</div>
  	  		  </div>
  	  		  
  	  		   <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="poNumber">PO No:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="poNumber" class="block show">${poSelected.poId}</p>
   		 						<input type="text" class="form-control block hide" id="poNumber"	name="poNumber" value="${poSelected.poId}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="poNumber"	name="poNumber" value="${poSelected.poId}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poVersion" class="control-label">PO Version:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="poVersion" class="block show">${poSelected.version}</p>
   		 						<input type="text" class="form-control block hide" id="poVersion"	name="poVersion" value="${poSelected.version}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="poVersion"	name="poVersion" value="${poSelected.version}">
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poDate" class="control-label">PO Date:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="poDate" class="block show">${poSelected.date}</p>
   		 						<input type="text" class="form-control block hide" id="poDate"	name="poDate" value="${poSelected.date}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="poDate"	name="poDate" value="${poSelected.date}">
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	</div>
		    	
  				<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="vnoSender">Vendor No:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="vnoSender" class="block show">${poSelected.vendor}</p>
   		 						<input type="text" class="form-control block hide" id="vnoSender"	name="vnoSender" value="${poSelected.vendor}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="vnoSender"	name="vnoSender" value="${poSelected.vendor}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poSender" class="control-label">Sender:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="poSender" class="block show">${poSelected.sender}</p>
   		 						<input type="text" class="form-control block hide" id="poSender"	name="poSender" value="${poSelected.sender}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="poSender"	name="poSender" value="${poSelected.sender}">
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="poSenderDetails" class="control-label">Sender Details:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="poSenderDetails" class="block show">${poSelected.senderDetails}</p>
   		 						<input type="text" class="form-control block hide" id="poSenderDetails"	name="poSenderDetails" value="${poSelected.senderDetails}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="poSenderDetails"	name="poSenderDetails" value="${poSelected.senderDetails}">
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	</div>
  	  		  	
  	  		   
  				<div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="senderEmail">Sender Email:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="senderEmail" class="block show">${poSelected.senderEmail}</p>
   		 						<input type="text" class="form-control block hide" id="senderEmail"	name="senderEmail" value="${poSelected.senderEmail}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="senderEmail"	name="senderEmail" value="${poSelected.senderEmail}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
		    	 
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="senderPhone" class="control-label">Sender Phone:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="senderPhone" class="block show">${poSelected.senderPhone}</p>
   		 						<input type="text" class="form-control block hide" id="senderPhone"	name="senderPhone" value="${poSelected.senderPhone}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="senderPhone"	name="senderPhone" value="${poSelected.senderPhone}">
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="senderFax" class="control-label">Sender Fax:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="senderFax" class="block show">${poSelected.senderFax}</p>
   		 						<input type="text" class="form-control block hide" id="senderFax"	name="senderFax" value="${poSelected.senderFax}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="senderFax"	name="senderFax" value="${poSelected.senderFax}">
							</jstl:if>
			    	 	</div>
			    	 </div>
		    	</div>
			  
			  <div class="row rowspace">
		    	   <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="notes">Notes:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="notes" class="block show">${poSelected.notes}</p>
   		 						<input type="text" class="form-control block hide" id="notes"	name="notes" value="${poSelected.notes}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="notes"	name="notes" value="${poSelected.notes}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="totalValue">Total value:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
   		 						<p id="totalValue" class="block show">${poSelected.totalValue}</p>
   		 						<input type="text" class="form-control block hide" id="totalValue"	name="totalValue" value="${poSelected.totalValue}">
							</jstl:if>
							<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
								<input type="text" class="form-control block show" id="totalValue"	name="totalValue" value="${poSelected.totalValue}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
		    	</div>
		    	
		    	<div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
		      			<label>Material details (Mat No is mandatory. Without Mat No Material will not be saved)</label>
		    		</div>
  	  		   </div>
  	  		  
  	  		  	
  	  		   <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
  	  		   <jstl:if
								test="${poSelected.material!=null}">
	  	  		   <div class="row rowspace">
			   		 	<div id="alreadySavedContacts" class="col-sm-12 col-md-12">
			   		 		<table class="form-table" id="customFieldsExisting">
				   		 	<jstl:forEach var="eachMaterial"
												items="${poSelected.material}">
				   			 	<jstl:set var="splittedString"
													value="${fn:split(eachMaterial, '[|]')}" />
				                <tr>
				                <td class="joboperationbox">
									<p id="matNo" class="form-control block show ">${splittedString[0]}</p>
   		 							<input type="text" class="form-control block hide " id="matNo" placeholder="Mat No" name="matNo" value="${splittedString[0]}">
								</td>														
								<td class="joboperationbox">
									<p id="matDesc" class="form-control block show">${splittedString[1]}</p>
   		 							<input type="text" class="form-control block hide" id="matDesc" placeholder="Description" name="matDesc" value="${splittedString[1]}">
								</td>														
								<td class="joboperationbox">
									<p id="unitPrice" class="form-control block show">${splittedString[2]}</p>
   		 							<input type="text" class="form-control block hide" id="unitPrice" placeholder="Unit Price" name="unitPrice" value="${splittedString[2]}">
								</td>														
								<td class="joboperationbox">
									<p id="quantity" class="form-control block show">${splittedString[3]}</p>
   		 							<input type="text" class="form-control block hide" id="quantity" placeholder="Quantity" name="quantity" value="${splittedString[3]}">														
								</td>														
								<td class="joboperationbox">														
									<p id="discount" class="form-control block show">${splittedString[4]}</p>
   		 							<input type="text" class="form-control block hide" id="discount" placeholder="Discount" name="discount" value="${splittedString[4]}">														
								</td>														
								<td class="joboperationbox">														
									<p id="value" class="form-control block show">${splittedString[5]}</p>
   		 							<input type="text" class="form-control block hide" id="value" placeholder="Value" name="value" value="${splittedString[5]}">
								</td>
								<td class="joboperationbox">			
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
  	  		   
  	  		   </div>
  	  		   </div>
  	  		   
  	  		   <jstl:if	test="${poSelected.pid == null || poSelected.pid<=0}">
  	  		   
			  	<div class="row rowspace">
  	  		 
  	  				 <div class="col-sm-12 col-md-12">
	  	  		   	
	  	  		   	<table class="form-table block show" id="customFields">
						<tr>
							<td class="joboperationbox">
								<input type="text" class="form-control rowspace" id="matNo"
												name="matNo" value="" placeholder="Mat No" />
							</td><td class="joboperationbox">												
								<input type="text" class="form-control rowspace" id="matDesc" name="matDesc"
												value="" placeholder="Description" />
							</td><td class="joboperationbox">												
								<input type="text" class="form-control rowspace" id="unitPrice" name="unitPrice"
												value="" placeholder="Unit Price" />
							</td><td class="joboperationbox">												
								<input type="text" class="form-control rowspace" id="quantity" name="quantity"
												value="" placeholder="Quantity" />
							</td><td class="joboperationbox">												
								<input type="text" class="form-control rowspace" id="discount" name="discount"
												value="" placeholder="Discount" />
							</td><td class="joboperationbox">												
								<input type="text" class="form-control rowspace" id="value" name="value"
												value="" placeholder="Value" />
							</td>
						</tr>
					</table>
					
		
		    	</div>
		    	</div>
		   	</jstl:if>	
		    	
		    
		     <jstl:if	test="${poSelected.pid != null && poSelected.pid>0}">
	  	  		   <div class="row rowspace control-group col-sm-12 col-md-12">
			   		 		<table id="customFields" class="form-table block hide">
			           		 </table>
				   </div>
				</jstl:if>				
		    	
		    	
		    	
				<br>
		    	<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
					<div class="row rowspace">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			    <p id="insertRowParent">
	    							<input class="form-control insertRow block show btn btn-primary" type="button" value="Add row">
								</p>
			    		</div>
  	  		   		</div>
				</jstl:if>
	  			<jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
					<div class="row rowspace">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			    <p id="insertRowParent">
	    							<input class="form-control insertRow block hide btn btn-primary" type="button" value="Add row">
								</p>
			    		</div>
  	  		   		</div>
				</jstl:if>
		    
  	  		   <br>
				
  	  		   <jstl:if
								test="${poSelected.pid != null && poSelected.pid > 0 && poSelected.files!=null}">
	  	  		   <div class="row rowspace">
	  	  		  		 <div class="col-sm-2 col-md-2">
			      			<label>Uploaded file/s:</label>
			    		</div>
			   		 	<div id="alreadyUploadedFiles" class="col-sm-4 col-md-4">
			   		 		<table >
				   		 	<jstl:forEach var="eachFile"
												items="${poSelected.files}">
				                <tr>
				                    <td>
				                    	<a 
														href="filedownload/po/${poSelected.pid}/${eachFile}"> 	
				                    		<jstl:out value="${eachFile}" />
										</a>
				                    </td>
				                    
				                    <td style="padding: 3px;">
				                    	<button type="button"
															class="btn btn-default btn-sm block hide" name="deleteFile"
															id="deleteFile" onClick="removeFile('${eachFile}');">
        									 <span class="glyphicon glyphicon-remove"></span> Remove 
        								</button>
				                    </td>
				                </tr>
			           		 </jstl:forEach>
			           		 </table>
			    	 	</div>
				   </div>
				</jstl:if>							
  	  		    
  	  		  
  	  		   <jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
  	  		   <div class="row rowspace block show">
  	  		  		 <div class="col-sm-2 col-md-2">
		      			<label for="eFiles">Upload File/s:</label>
		    		</div>
		   		 	<div id="fileuploads" class="col-sm-4 col-md-4">
		           		 <input type="file" name="eFiles" id="eFiles" multiple>
		    	 	</div>
		    	 	
			   </div>

  			   <div class="row rowspace block show">
  	  		  		 <div class="col-sm-2 col-md-2">
		      			    <input type="button" name="addmore" id="addmore"
										value="Add More" onClick="addMoreFiles();" />  
		    		</div>
		    		<div class="col-sm-2 col-md-2">
		           		 <input type="button" name="unSelectFile"
										id="unSelectFile" value="Reset file selection"
										onClick="unSelectFiles();" />
		    	 	</div>	
			  </div>
    		</jstl:if>	
  	  		  <jstl:if test="${poSelected.pid != null && poSelected.pid>0}">
  	  		   <div class="row rowspace block hide">
  	  		  		 <div class="col-sm-2 col-md-2">
		      			<label for="eFiles">Upload File/s:</label>
		    		</div>
		   		 	<div id="fileuploads" class="col-sm-4 col-md-4">
		           		 <input type="file" name="eFiles" id="eFiles" multiple>
		    	 	</div>
		    	 	
			   </div>

  			   <div class="row rowspace block hide">
  	  		  		 <div class="col-sm-2 col-md-2">
		      			    <input type="button" name="addmore" id="addmore"
										value="Add More" onClick="addMoreFiles();" />  
		    		</div>
		    		<div class="col-sm-2 col-md-2">
		           		 <input type="button" name="unSelectFile"
										id="unSelectFile" value="Reset file selection"
										onClick="unSelectFiles();" />
		    	 	</div>	
			  </div>
    		</jstl:if>							
			  
			  <br>
			     <br>
			   <div class="row">
		   		 	<div class="col-sm-1 col-md-1">
		   		 		<jstl:if test="${poSelected.pid == null || poSelected.pid<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <button id="createJobcardSubmit" type="submit" class="btn btn-primary">Create</button>
						</jstl:if>
						<jstl:if test="${poSelected.pid != null && poSelected.pid > 0}">
							  <button id="editButton" type="button" onclick="switchForm();return false;" class="btn btn-primary block show">Edit</button>
							  <input type="hidden" name="create" value="false">
						      <button id="createJobcardSubmit" type="submit" class="btn btn-primary block hide">Update</button>
						</jstl:if>
		   		 	</div>
		   		 	<div class="col-sm-1 col-md-1">
		   		 		<jstl:if test="${poSelected.pid != null && poSelected.pid > 0}">
						      <button id="cancelButton" onclick="switchForm();return false;" class="btn btn-primary block hide">Cancel</button>
						</jstl:if>
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

