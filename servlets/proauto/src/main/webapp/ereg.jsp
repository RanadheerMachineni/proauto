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

	/*$(document).on("change", "#eFiles", function(event) {
		$.each(this.files, function( index, eachFile ) {
			if(eachFile.size>1048576){
				  //alert("File '"+eachFile.name+"' is not allowed as it size crosses 1MB.");
				  this.files[0].val('');
			}
		});
	});*/
	//return true means no error
	//return this.optional(element) || (element.files[0].size <= param)

	$.validator.addMethod('filesize', function (value, element, param) {
		if(!element.files[0]) return true;
	    return (element.files[0].size <= param)
	}, 'File size must be less than 4MB');
	
	$(document)
			.ready(
					function() {

						$("#eDob").datepicker();
						$("#eDoj").datepicker();

						$('#employeeRegForm').validate(
								{
									rules : {
										efirstName : {
											minlength : 2,
											required : true
										},
										eLastName : {
											minlength : 2,
											required : true
										},
										gender : {
											required : true
										},
										eUserId : {
											required : true
										},
										ePassword : {
											required : true
										},
										eEmergencyContact : {
											required : true
										},
										ePhone : {
											required : true,
											minlength : 10,
											number : true
										},
										eEmail : {
											required : true,
											email : true
										},
										eDob : {
											required : true
										},
										eDoj : {
											required : true
										},
										eFiles : {
											filesize: 4194304
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
		roleChanged();
	}

	function addMoreFiles() {  
		   $("#fileuploads").append(document.createElement("br"));  
		   $("#fileuploads").append('<input type="file" name="eFiles" id="eFiles">');  
	}  
	
	function unSelectFiles() {
		//$("#eFiles").val('');
		
		$('input[name="eFiles"]').each(function() {
		    $(this).val('');
		});

	}
	
	function removeFile(fileName){
		//alert(fileName);
		var existingInputValue = $('input#uploadedFiles').val();
		var newValue = existingInputValue.replace(fileName, "");
		$('input#uploadedFiles').val(newValue);
		$('#alreadyUploadedFiles tr').each(function() {
		    var href = $(this).find("td:first").find("a").attr('href');  
		    if (href.toLowerCase().indexOf(fileName.toLowerCase()) >= 0){
		        $(this).closest('tr').remove();
		    }
		});
	}
	function roleChanged() {
		var roleSelect = document.getElementById("eRole");
		var role = roleSelect.options[roleSelect.selectedIndex].value;
		if (role == 'ROLE_norole') {
			$("#userIdDiv").removeClass('show');
			$("#pwdDiv").removeClass('show');
			$("#userIdDiv").addClass('hide');
			$("#pwdDiv").addClass('hide');
		} else {
			$("#userIdDiv").removeClass('hide');
			$("#pwdDiv").removeClass('hide');
			$("#userIdDiv").addClass('show');
			$("#pwdDiv").addClass('show');

		}
	}
	function ValidateForm(form) {
		var eName = $("#eName").val();
		var roleSelect = document.getElementById("eRole");
		var eRole = roleSelect.options[roleSelect.selectedIndex].value;
		var eUserId = $("#eUserId").val();
		var ePassword = $("#ePassword").val();
		var eAddress = $("#eAddress").val();
		var ePhone = $("#ePhone").val();
		var eEmail = $("#eEmail").val();

		if (!eName || !eAddress) {
			alert('Employee Name/Address can not be empty');
			//event.preventDefault();
			return false;
		}

		if (eRole != 'ROLE_norole') {
			if (!eUserId || !ePassword) {
				alert('Employee UserId/Password can not be empty');
				//event.preventDefault();
				return false;
			}
		}

		var pattern = /[0-9\-\(\)\s]+/;
		if (!ePhone || (ePhone.length < 10) || (!pattern.test(ePhone))) {
			alert('Please enter a valid phone number');
			return false;
		}

		var emailRe = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!eEmail || !emailRe.test(eEmail)) {
			alert('Please enter a valid email address');
			return false;

		}

		return true;
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
					<div class="pageHeadings"> Employee Registration</div>
					<br>
					<div class="formDiv">
					<div class="row">
	   					<div class="col-sm-10 col-md-10">
						   	<jstl:if test="${not empty result && result=='sucess'}">
								<div id="sucessDiv" class="successresponse">
								Successfully created/updated employee. Please click to view/update
								<a
								href="${pageContext.request.contextPath}/ereg?employeeSelected=${empCreated}">
								                    	<jstl:out
										value="${empCreatedName}" />
								                    </a>
								</div>
							</jstl:if>
						</div>
					</div>
					<jstl:if
							test="${employeeSelected.employeeId == null || employeeSelected.employeeId<=0}">
						      			<label>Create Employee</label>
					</jstl:if>
					<jstl:if
							test="${employeeSelected.employeeId != null && employeeSelected.employeeId > 0}">
						      			<label>Update Employee</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
						   	<jstl:if test="${not empty error}">
								<div id="errorDiv" class="error">${error}</div>
							</jstl:if>
						</div>
					</div>
		<form id="employeeRegForm" role="form"
							action="ereg?${_csrf.parameterName}=${_csrf.token}" method="post"
							enctype="multipart/form-data">  
			<jsp:useBean id="guidGenerator"
								class="com.esteeminfo.proauto.utils.GUIDGenerator"
								scope="application" />
		
		
		      <input type="hidden" name="regType" value="employee">
			  <input type="hidden" name="eid" id="eid"
								value="${employeeSelected.employeeId}">
			  <input type="hidden" name="uploadedFiles" id="uploadedFiles"
								value="${employeeSelected.files}">								
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="efirstName">First Name:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="efirstName"
											name="efirstName" value="${employeeSelected.firstName}">
			    	 	</div>
		    	 	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="eLastName" class="control-label">Last Name:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="eLastName"
											name="eLastName" value="${employeeSelected.lastName}">
			    	 	</div>
			    	 </div>
		    	 <div class="control-group">
		    	
		    	 	<div class="col-sm-3 col-md-3 controls">
		      			<label class="radio-inline"><input type="radio"
											name="gender" value="m"
											${employeeSelected.gender == 'm' ? 'checked' : ''}>Male</label>
    			    	<label class="radio-inline"><input type="radio"
											name="gender" value="f"
											${employeeSelected.gender == 'f' ? 'checked' : ''}>Female</label>
		    	 	</div>
		    	 	<label for="gender" class="error"></label>
		    	 	
		    	 </div>
  	  		  </div>
  	  		  	
  	  		   <div class="row rowspace">
  	  		   		<div class="col-sm-2 col-md-2">
		      			<label for="eQualification">Qualification:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
		      			<input type="text" class="form-control" id="eQualification"
										name="eQualification"
										value="${employeeSelected.qualification}">
		    	 	</div>
		    	 	
		    	 	<div class="col-sm-2 col-md-2">
		      			<label for="eExperience">Experience:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
		      			<input type="text" id="eExperience" class="form-control"
										name="eExperience" value="${employeeSelected.experience}">
		    	 	</div>
		    	 	
		    	 	<div class="col-sm-3 col-md-3">
		      			<label class="radio-inline"><input type="radio"
										name="married" value="s"
										${employeeSelected.married == 's' ? 'checked' : ''}>Single</label>
    			    	<label class="radio-inline"><input type="radio"
										name="married" value="m"
										${employeeSelected.married == 'm' ? 'checked' : ''}>Married</label>
		    	 	</div>
		    	 	
  	  		  </div>
  	  		  <div class="row rowspace">
  	  		   		<div class="col-sm-2 col-md-2">
		      			<label for="eDesignation">Designation:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
		      			<input type="text" class="form-control" id="eDesignation"
										name="eDesignation" value="${employeeSelected.designation}">
		    	 	</div>
		    	 	
		    	 	
		    	 	<div class="col-sm-2 col-md-2">
		      			<label for="eSection">Section:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
			 			<select class="form-control" name="eSection" id="eSection">
	  						<jstl:forEach items="${sections}" var="section">
	  				  			<option value="${section.key}"
												${employeeSelected.section == section.key ? 'selected' : ''}>${section.value}</option>
	  						</jstl:forEach>
						</select>
		    	 	</div>
		    	 	
		    		<div class="col-sm-3 col-md-3">
		      			<label class="radio-inline"><input type="radio"
										name="eEmploymentType" value="p"
										${employeeSelected.employmentType==null || employeeSelected.employmentType == 'p' ? 'checked' : ''}>Permanent</label>
    			    	<label class="radio-inline"><input type="radio"
										name="eEmploymentType" value="t"
										${employeeSelected.employmentType == 't' ? 'checked' : ''}>Contract</label>
		    	 	</div>	
		    	 	
  	  		  </div>

  	  		  <div class="row rowspace">
  	  		  	  	<div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label for="ePhone" class="control-label">Phone:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="ePhone"
											name="ePhone" value="${employeeSelected.phone}">
			    	 	</div>
		    	 	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="eEmail" class="control-label">Email:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="eEmail"
											name="eEmail" value="${employeeSelected.email}">
			    	 	</div>
		    	 	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-1 col-md-1">
		      				<label for="ePassport">Passport:</label>
		    			</div>
		   		 		<div class="col-sm-2 col-md-2">
		      				<input type="text" class="form-control" id="ePassport"
											name="ePassport" value="${employeeSelected.passport}">
		    	 		</div>
		    	 	</div>
  	  		  </div>
  	  		  
  	  		  <div class="row rowspace">
		    	 	
		    	 <div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="eDob" class="control-label">Date Of Birth:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="eDob"
											name="eDob" readonly="true" value="${employeeSelected.dob}">
			    	 	</div>
			    	</div>
		    	 	
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="eDoj" class="control-label">Date Of Joining:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="eDoj"
											name="eDoj" readonly="true" value="${employeeSelected.doj}">
			    	 	</div>
			    	</div>
		    	 	
		    	 	<div class="control-group">
		    	 	
			    	 	<div class="col-sm-1 col-md-1">
			      			<label for="eEmergencyContact" class="control-label">Emergency Contact:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control"
											id="eEmergencyContact" name="eEmergencyContact"
											value="${employeeSelected.emergencyContact}">
			    	 	</div>
		    	 	</div>
  	  		  </div>
  	  		  
  	  		  <div class="row rowspace">
		    	 	
		    	 	<div class="col-sm-2 col-md-2">
		      			<label for="eCAddress">Current Address:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
		      			<textarea class="form-control" id="eCAddress"
										name="eCAddress">${employeeSelected.currentAddress}</textarea>
		    	 	</div>
		    	 	
		    	 	<div class="col-sm-2 col-md-2">
		      			<label for="ePAddress">Permanent Address:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
		      			<textarea class="form-control" id="ePAddress"
										name="ePAddress">${employeeSelected.permanentAddress}</textarea>
		    	 	</div>
		    	 	
		    	 	<div class="col-sm-1 col-md-1">
		      			<label for="eNotes">Notes:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
		      			<textarea class="form-control" id="eNotes" name="eNotes">${employeeSelected.notes}</textarea>
		    	 	</div>
		    	 	
  	  		  </div>
  	  		   <div class="row rowspace">
  	  		   		<div class="col-sm-2 col-md-2">
		      			<label for="eRole">Role:</label>
		    		</div>
		   		 	<div class="col-sm-2 col-md-2">
			 			<select class="form-control" name="eRole" id="eRole"
										onchange="roleChanged();">
	  						<jstl:forEach items="${roles}" var="role">
	  				  			<option value="${role.key}"
												${employeeSelectedRole == role.key ? 'selected' : ''}>${role.value}</option>
	  						</jstl:forEach>
						</select>
		    	 	</div>
	  	  		   <div class="control-group">
		  	  		   	<div class="hide" id="userIdDiv">
		  	  		   		<div class="col-sm-2 col-md-2">
				      			<label for="eUserId" class="control-label">User Id:</label>
				    		</div>
				 			<div class="col-sm-2 col-md-2 controls">
				      			<input type="text" class="form-control" id="eUserId"
												name="eUserId" value="${employeeSelected.userId}">
				    	 	</div>
		  	  		   </div>
	  	  		  
		  	  		  <div class="hide" id="pwdDiv">
		  	  		   		<div class="col-sm-1 col-md-1">
				      			<label for="ePassword" class="control-label">Password:</label>
				    		</div>
				 			<div class="col-sm-2 col-md-2 controls">
				      			<input type="text" class="form-control" id="ePassword"
												name="ePassword" value="${employeeSelected.password}">
				    	 	</div>
		  	  		   </div>
		  	  		</div>
  	  		   </div>
  	  		   
  	  		   <jstl:if	test="${employeeSelected.employeeId != null && employeeSelected.employeeId > 0 && employeeSelected.files!=null}">
	  	  		   <div class="row rowspace">
	  	  		  		 <div class="col-sm-2 col-md-2">
			      			<label for="uFiles">Uploaded file/s:</label>
			    		</div>
			   		 	<div id="alreadyUploadedFiles" class="col-sm-4 col-md-4">
			   		 		<table>
				   		 	<jstl:forEach var="eachFile" items="${employeeSelected.files}">
				                <tr>
				                    <td>
				                    	<a href="filedownload/employee/${employeeSelected.employeeId}/${eachFile}"> 	
				                    		<jstl:out value="${eachFile}" />
										</a>
				                    </td>
				                    
				                    <td style="padding: 3px;">
				                    	<button type="button" class="btn btn-default btn-sm" name="deleteFile" id="deleteFile" onClick="removeFile('${eachFile}');">
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
		           		 <input type="file" name="eFiles" id="eFiles">
		    	 	</div>
		    	 	
			   </div>

  			   <div class="row rowspace">
  	  		  		 <div class="col-sm-2 col-md-2">
		      			    <input type="button" name="addmore" id="addmore" value="Add More" onClick="addMoreFiles();" />  
		    		</div>
		    		<div class="col-sm-2 col-md-2">
		           		 <input type="button" name="unSelectFile" id="unSelectFile" value="Reset file selection" onClick="unSelectFiles();"/>
		    	 	</div>	
			  </div>
			  
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
						<jstl:if
							test="${employeeSelected.employeeId == null || employeeSelected.employeeId<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <!--  <input id="createEmployeeSubmit" type=submit value="Create">-->
						      <button id="createEmployeeSubmit" type="submit"
								class="btn btn-primary">Create</button>
						      
						</jstl:if>
					<jstl:if
							test="${employeeSelected.employeeId != null && employeeSelected.employeeId > 0}">
							  <input type="hidden" name="create" value="false">
						      <!-- <input id="createEmployeeSubmit" type=submit value="Update"> -->
						      <button id="createEmployeeSubmit" type="submit"
								class="btn btn-primary">Update</button>
						      
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
								href="${pageContext.request.contextPath}/ereg" role="button">Cancel</a>
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
	   		 					<form id="employeeSearchForm" role="form" action="ereg"
				method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-1 col-md-1">
						      			<label for="searchEmployeeInput">Search Employee</label>
						    		</div>
						   		 	<div class="col-sm-2 col-md-2">
						      			<input type="text" class="form-control"
							id="searchEmployeeInput" name="searchEmployeeInput">
						    	 	</div>
						    	 	<div class="col-sm-1 col-md-1">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of existing Employees</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>Employee Name</th>
										<th>Gender</th>
										<th>Designation</th>
			   					        <th>Phone</th>
										<th>User Id</th>
										<th>Emergency Contact</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="employee" items="${employeeList}">
							                <tr>
							                    <td>
							                    	<a
								href="${pageContext.request.contextPath}/ereg?employeeSelected=${employee.employeeId}">
								                    	<jstl:out
										value="${employee.firstName} ${employee.lastName}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${employee.gender}" /></td>
							                    <td><jstl:out
									value="${employee.designation}" /></td>
							                    <td><jstl:out value="${employee.phone}" /></td>
							                    <td><jstl:out value="${employee.userId}" /></td>
							                    <td><jstl:out
									value="${employee.emergencyContact}" /></td>
							                    
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
  	  		
					</jstl:if>
		
</div>
</jsp:body>


</t:layout>

