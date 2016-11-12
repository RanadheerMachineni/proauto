<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
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
		<div class="pageHeadings"> Employee Registration</div>
		<br>
					<div
					class="col-md-4 col-md-offset-1 col-sm-4 col-sm-offset-1 col-xs-4 col-xs-offset-1">
		
					<jstl:if test="${employeeSelected.employeeName == null}">
						      			<label>Create Employee</label>
					</jstl:if>
					<jstl:if test="${employeeSelected.employeeName != null}">
						      			<label>Update Employee</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
			</div>
		
		<form id="employeeRegForm" role="form" action="ereg" method="post"
						onsubmit="return ValidateForm(this);">  
		    <input type="hidden" name="regType" value="employee">
			  <input type="hidden" name="eid" id="eid"
							value="${employeeSelected.employeeId}">
			   <br>
			   
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="eName">Employee Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="eName"
									name="eName" value="${employeeSelected.employeeName}">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="eRole">Role:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		 			<select name="eRole" id="eRole" onchange="roleChanged();">
  						<jstl:forEach items="${roles}" var="role">
  				  			<option value="${role.key}" ${param.employeeSelectedRole}==${role.key}?'selected':''>${role.value}</option>
  						</jstl:forEach>
					</select>
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  
  	  		  <div class="row hide" id="userIdDiv">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="eUserId">User Id:</label>
		    		</div>
		 			<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="eUserId"
									name="eUserId" value="${employeeSelected.userId}">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  
  	  		  <div class="row hide" id="pwdDiv">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="ePassword">Password:</label>
		    		</div>
		 			<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="ePassword"
									name="ePassword" value="${employeeSelected.password}">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="eAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<textarea class="form-control" id="eAddress" name="eAddress">${employeeSelected.address}</textarea>
		    	 	</div>
  	  		   </div>
  	  		  
  	  		  <br>
  	  		  
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="ePhone">Phone :</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
							<input type="text" class="form-control" id="ePhone" name="ePhone"
									value="${employeeSelected.phone}">		    	 	
					</div>
  	  		   </div>
  	  		   <br>
 	  		 
 	  		 <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="eEmail">Email :</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
							<input type="text" class="form-control" id="eEmail" name="eEmail"
									value="${employeeSelected.email}">		    	 	
					</div>
  	  		   </div>
  	  		   <br>
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-4 col-md-4">
	   		 			<jstl:if test="${employeeSelected.employeeName == null}">
	   		 				  <input type="hidden" name="create" value="true">
						      <input id="createEmployeeSubmit" type=submit value="Create">
						</jstl:if>
						<jstl:if test="${employeeSelected.employeeName != null}">
							  <input type="hidden" name="create" value="false">
						      <input id="createEmployeeSubmit" type=submit value="Update">
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
		</form>	
		</div>
  	  		
  	  		
  	  					<!--  RHS Side -->
						<div
					class="col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2">
	   		 					<br>
	   		 					<form id="employeeSearchForm" role="form" action="ereg"
						method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-6 col-md-6">
						      			<label for="searchEmployeeInput">Search Employee</label>
						    		</div>
						   		 	<div class="col-sm-6 col-md-6">
						      			<input type="text" class="form-control"
									id="searchEmployeeInput" name="searchEmployeeInput">
						    	 	</div>
  	  		  					</div>
  	  		  					 <div class="row">
						   		 	<div class="col-sm-10 col-md-10">
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
								        <th>Address</th>
			   					        <th>Phone</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="employee" items="${employeeList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/ereg?employeeSelected=${employee.employeeName}">
								                    	<jstl:out value="${employee.employeeName}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${employee.userId}" /></td>
							                    <td><jstl:out
											value="${employee.phone}" /></td>
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
					</jstl:if>
		
</div>
</jsp:body>
</t:layout>

