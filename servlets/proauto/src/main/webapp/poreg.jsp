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
	
	$(document).on("click", ".insertRow", function(event) {
		 $('#customFields').append('<tr><td>'+'				 <input type="text" class="contactField rowspace" id="matNo"						name="matNo" value="" placeholder="Mat No" /> &nbsp;		<input type="text" class="contactField rowspace" id="matDesc" name="matDesc"						value="" placeholder="Description" /> &nbsp;		<input type="text" class="contactField rowspace" id="unitPrice" name="unitPrice"						value="" placeholder="Unit Price" /> &nbsp;		<input type="text" class="contactField rowspace" id="quantity" name="quantity"						value="" placeholder="Quantity" /> &nbsp;		<input type="text" class="contactField rowspace" id="discount" name="discount"						value="" placeholder="Discount" /> &nbsp;		<input type="text" class="contactField rowspace" id="value" name="value"						value="" placeholder="Value" /> &nbsp;'+
		  			'<input type="button" class="deleteButton" value="Delete" />'+
					'</td></tr>')
		
	});
	$(document).on("click", ".deleteButton", function(event) {
	    $(this).closest('tr').remove();
		
	});

	
	$(document)
			.ready(
					function() {
						$("#poDate").datepicker();

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
			   <br>
			   
			   <div class="row rowspace">
		    	 	<div class="col-sm-2 col-md-2">
		      			<label for="customer">Customer:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
			 			<select class="form-control" name="customer" id="customer">
			 			 	<option value="">Select Customer</option>
	  						<jstl:forEach items="${customers}" var="eachCustomer">
	  				  			<option value="${eachCustomer.key}"
												${poSelected.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
	  						</jstl:forEach>
						</select>
		    	 	</div>
  	  		  </div>
  	  		  
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
			      			<label class="control-label" for="value">Total value:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="totalValue"
											name="totalValue" value="${poSelected.totalValue}">
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
								test="${poSelected.pid != null && poSelected.pid > 0 && poSelected.material!=null}">
	  	  		   <div class="row rowspace">
			   		 	<div id="alreadySavedContacts" class="col-sm-12 col-md-12">
			   		 		<table class="form-table" id="customFieldsExisting">
				   		 	<jstl:forEach var="eachMaterial"
												items="${poSelected.material}">
				   			 	<jstl:set var="splittedString"
													value="${fn:split(eachMaterial, '[|]')}" />
				                <tr>
				                <td>
								<input type="text" class="contactField rowspace" id="matNo"
														name="matNo" value="${splittedString[0]}"
														placeholder="Mat No" /> &nbsp;
								<input type="text" class="contactField rowspace" id="matDesc" name="matDesc"
														value="${splittedString[1]}" placeholder="Description" /> &nbsp;
								<input type="text" class="contactField rowspace" id="unitPrice" name="unitPrice"
														value="${splittedString[2]}" placeholder="Unit Price" /> &nbsp;
								<input type="text" class="contactField rowspace" id="quantity" name="quantity"
														value="${splittedString[3]}" placeholder="Quantity" /> &nbsp;
								<input type="text" class="contactField rowspace" id="discount" name="discount"
														value="${splittedString[4]}" placeholder="Discount" /> &nbsp;
								<input type="text" class="contactField rowspace" id="value" name="value"
														value="${splittedString[5]}" placeholder="Value" /> &nbsp;
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
								<input type="text" class="contactField rowspace" id="matNo"
												name="matNo" value="" placeholder="Mat No" /> &nbsp;
								<input type="text" class="contactField rowspace" id="matDesc" name="matDesc"
												value="" placeholder="Description" /> &nbsp;
								<input type="text" class="contactField rowspace" id="unitPrice" name="unitPrice"
												value="" placeholder="Unit Price" /> &nbsp;
								<input type="text" class="contactField rowspace" id="quantity" name="quantity"
												value="" placeholder="Quantity" /> &nbsp;
								<input type="text" class="contactField rowspace" id="discount" name="discount"
												value="" placeholder="Discount" /> &nbsp;
								<input type="text" class="contactField rowspace" id="value" name="value"
												value="" placeholder="Value" /> &nbsp;
							</td>
						</tr>
					</table>
					
		
		    	</div>
		    	</div>
		    	<div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
		      			    <p id="insertRowParent">
    							<input class="insertRow" type="button" value="Insert Row">
							</p>
		    		</div>
  	  		   </div>
  	  		   <br>
  	  		   
  	  		   <jstl:if
								test="${poSelected.pid != null && poSelected.pid > 0 && poSelected.files!=null}">
	  	  		   <div class="row rowspace">
	  	  		  		 <div class="col-sm-2 col-md-2">
			      			<label for="uFiles">Uploaded file/s:</label>
			    		</div>
			   		 	<div id="alreadyUploadedFiles" class="col-sm-4 col-md-4">
			   		 		<table>
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
															class="btn btn-default btn-sm" name="deleteFile"
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
  	  		    
  	  		   
  	  		   <div class="row rowspace">
  	  		  		 <div class="col-sm-2 col-md-2">
		      			<label for="eFiles">Upload File/s:</label>
		    		</div>
		   		 	<div id="fileuploads" class="col-sm-4 col-md-4">
		           		 <input type="file" name="eFiles" id="eFiles" multiple>
		    	 	</div>
		    	 	
			   </div>

  			   <div class="row rowspace">
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
								href="${pageContext.request.contextPath}/searchpo" role="button">Cancel</a>
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

