<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
	<script>
	
	$(document).on("click", ".insertRow", function(event) {
		 $('#customFields').append('<tr><td>'+'<input type="text" class="contactField" id="contactname" name="contactname" value="" placeholder="Name" /> &nbsp;	<input type="text" class="contactField" id="phone" name="phone"							value="" placeholder="Phone" /> &nbsp;			<input type="text" class="contactField" id="email" name="email"							value="" placeholder="Email" /> &nbsp;			<input type="text" class="contactField" id="fax" name="fax"							value="" placeholder="Fax" /> &nbsp;			<input type="text" class="contactField" id="notes" name="notes"							value="" placeholder="Notes" /> &nbsp;'+
		 			'<input type="button" class="deleteButton" value="Delete" />'+
 					'</td></tr>')
		
	});
	$(document).on("click", ".deleteButton", function(event) {
	    $(this).closest('tr').remove();
		
	});
	
		$(document).ready(
				function() {
					$('#customerRegForm').validate(
							{
								rules : {
									cName : {
										minlength : 2,
										required : true
									},
									cAddress : {
										minlength : 2,
										required : true
									}
								},
								highlight : function(element) {
									$(element).closest('.control-group')
											.removeClass('success').addClass(
													'error');
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
			
		<div class="pageHeadings"> Customer Registration</div>
		<br>
							<div class="formDiv">
		
					<jstl:if test="${customerSelected.customerName == null}">
						      			<label>Create Customer</label>
					</jstl:if>
					<jstl:if test="${customerSelected.customerName != null}">
						      			<label>Update Customer</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
			</div>
			
		<form id="customerRegForm" role="form"
							action="creg?${_csrf.parameterName}=${_csrf.token}" method="post"
							enctype="multipart/form-data">  
		    <input type="hidden" name="regType" value="customer">
			   <br>
			   			   		    <input type="hidden" name="cid" id="cid"
								value="${customerSelected.customerId}">
			   <input type="hidden" name="uploadedFiles" id="uploadedFiles"
								value="${customerSelected.files}">		
  	  		   <div class="row">
	  	  		   	<div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="cName">Customer Name:</label>
			    		</div>
			   		 	<div class="col-sm-3 col-md-3 controls">
			      			<input type="text" class="form-control" id="cName"
											name="cName" value="${customerSelected.customerName}">
			    	 	</div>
	  	  			  </div>
	  	  		</div>
  	  		  <br>
  	  		  <div class="row">
  	  		  	  	  		   	<div class="control-group">
  	  		  
  	  		   		<div class="col-sm-2 col-md-2">
		      			<label class="control-label" for="cAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-3 col-md-3 controls">
		      			<textarea class="form-control" id="cAddress" name="cAddress">${customerSelected.address}</textarea>
		    	 	</div>
		    	 	</div>
  	  		   </div>
 	  		 <br>
 	  		  <div class="row">
  	  		   		<div class="col-sm-12 col-md-12">
		      			<label>Contact details (Name is mandatory. Without Name contact will not be saved)</label>
		    		</div>
  	  		   </div>
  	  		  
  	  		  	 
  	  		   <jstl:if
								test="${customerSelected.customerId != null && customerSelected.customerId > 0 && customerSelected.contacts!=null}">
	  	  		   <div class="row rowspace">
			   		 	<div id="alreadySavedContacts" class="col-sm-12 col-md-12">
			   		 		<table class="form-table" id="customFieldsExisting">
				   		 	<jstl:forEach var="eachContact"
												items="${customerSelected.contacts}">
				   			 	<jstl:set var="splittedString"
													value="${fn:split(eachContact, '[|]')}" />
				                <tr>
				                <td>
								<input type="text" class="contactField" id="contactname"
														name="contactname" value="${splittedString[0]}"
														placeholder="Name" /> &nbsp;
								<input type="text" class="contactField" id="phone" name="phone"
														value="${splittedString[1]}" placeholder="Phone" /> &nbsp;
								<input type="text" class="contactField" id="email" name="email"
														value="${splittedString[2]}" placeholder="Email" /> &nbsp;
								<input type="text" class="contactField" id="fax" name="fax"
														value="${splittedString[3]}" placeholder="Fax" /> &nbsp;
								<input type="text" class="contactField" id="notes" name="notes"
														value="${splittedString[4]}" placeholder="Notes" /> &nbsp;
								<input type="button" class="deleteButton" value="Delete" />
														
								</td>
				                </tr>
			           		 </jstl:forEach>
			           		 </table>
			    	 	</div>
				   </div>
				</jstl:if>							
  	  		   
  	  		   
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-12 col-md-12">
	  	  		   	
	  	  		   	<table class="form-table" id="customFields">
						<tr valign="top">
							<td>
								<input type="text" class="contactField" id="contactname"
												name="contactname" value="" placeholder="Name" /> &nbsp;
								<input type="text" class="contactField" id="phone" name="phone"
												value="" placeholder="Phone" /> &nbsp;
								<input type="text" class="contactField" id="email" name="email"
												value="" placeholder="Email" /> &nbsp;
								<input type="text" class="contactField" id="fax" name="fax"
												value="" placeholder="Fax" /> &nbsp;
								<input type="text" class="contactField" id="notes" name="notes"
												value="" placeholder="Notes" /> &nbsp;
							</td>
						</tr>
					</table>

		    		</div>
  	  		   </div>
  	  		   <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
		      			    <p id="insertRowParent">
    							<input class="insertRow" type="button" value="Add Contact">
							</p>
		    		</div>
  	  		   </div>
  	  		   <br>
  	  		   
  	  		   <jstl:if
								test="${customerSelected.customerId != null && customerSelected.customerId > 0 && customerSelected.files!=null}">
	  	  		   <div class="row rowspace">
	  	  		  		 <div class="col-sm-2 col-md-2">
			      			<label for="uFiles">Uploaded file/s:</label>
			    		</div>
			   		 	<div id="alreadyUploadedFiles" class="col-sm-4 col-md-4">
			   		 		<table>
				   		 	<jstl:forEach var="eachFile"
												items="${customerSelected.files}">
				                <tr>
				                    <td>
				                    	<a
														href="filedownload/customer/${customerSelected.customerId}/${eachFile}"> 	
				                    		<jstl:out value="${eachFile}" />
										</a>
				                    </td>
				                    
				                    <td style="padding: 3px;">
				                    	<button type="button"
															class="btn btn-default btn-sm" name="deleteFile"
															id="deleteFile" onClick="removeFile('${eachFile}');">
        									 <span class="glyphicon glyphicon-remove"></span> Remove 
        								</button>
				                   		<!--  <input type="button" name="deleteFile" id="deleteFile" value="Delete" onClick="deleteFile(${employeeSelected.employeeId},${eachFile});" > -->
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
	   		 			<jstl:if test="${customerSelected.customerName == null}">
	   		 				  <input type="hidden" name="create" value="true">
						      <input id="createCustomerSubmit" type=submit value="Create">
						</jstl:if>
						<jstl:if test="${customerSelected.customerName != null}">
							  <input type="hidden" name="create" value="false">
						      <input id="createCustomerSubmit" type=submit value="Update">
						      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
											href="${pageContext.request.contextPath}/creg" role="button">Cancel</a>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
		</form>	
  	  		</div>
  	  		
  	  		</div>
  	  					<!--  RHS Side -->
						<div class="col-md-6 col-sm-6 col-xs-6">
	   		 					<br>
	   		 					<form id="customerSearchForm" role="form" action="creg"
						method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-6 col-md-6">
						      			<label for="searchCustomerInput">Search Customer</label>
						    		</div>
						   		 	<div class="col-sm-6 col-md-6">
						      			<input type="text" class="form-control"
									id="searchCustomerInput" name="searchCustomerInput">
						    	 	</div>
  	  		  					</div>
  	  		  					 <div class="row">
						   		 	<div class="col-sm-10 col-md-10">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of existing Customers</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>Customer Name</th>
								        <th>Address</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="customer" items="${customerList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/creg?customerSelected=${customer.customerId}">
								                    	<jstl:out value="${customer.customerName}" />
								                    </a>
												</td>
							                    <td><jstl:out
											value="${customer.address}" /></td>
							               
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
					</jstl:if>
		
</div>
</jsp:body>
</t:layout>

