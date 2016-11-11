<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	function ValidateForm(form) {
		var cName = $("#cName").val();
		var cAddress = $("#cAddress").val();
		var cnameOne = $("#name_one").val();
		var cPhoneOne = $("#phone_one").val();
		var cEmailOne = $("#email_one").val();
		//var div = document.getElementById('errorDiv');
		if (!cName || !cAddress || !cnameOne) {
			alert('Customer Name/Address/First contact can not be empty');
			//event.preventDefault();
			return false;
		}
		
		var pattern = /[0-9\-\(\)\s]+/;
		if (!cPhoneOne || (cPhoneOne.length < 10) || (!pattern.test(cPhoneOne))) {
			alert('Please enter a valid phone number(First row).');
			return false;
		}

		var emailRe = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!cEmailOne || !emailRe.test(cEmailOne)) {
			alert('Please enter a valid email address(First row).');
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
		<div class="pageHeadings"> Customer Registration</div>
		<br>
					<div class="col-md-4 col-sm-4 col-xs-4">
		
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
		
		<form id="customerRegForm" role="form" action="creg" method="post"
						onsubmit="return ValidateForm(this);">  
		    <input type="hidden" name="regType" value="customer">
			   <br>
			   
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cName">Customer Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cName"
									name="cName" value="${customerSelected.customerName}">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<textarea class="form-control" id="cAddress" name="cAddress">${customerSelected.address}</textarea>
		    	 	</div>
  	  		   </div>
  	  		   <br>
 	  		 
 	  		  <div class="row">
  	  		   		<div class="col-sm-10 col-md-10">
		      			<label>Contact details</label>
		    		</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-10 col-md-10">
	  	  		   		<table border="1">
	  	  		   		
								<tr>
										<th>Name</th>
										<th>Phone</th>
										<th>Email</th>
									</tr>
								<tr>
									<td><input type="text" id="name_one" name="name_one"
											value="${customerSelected.name_one}"></td>
									<td><input type="text" id="phone_one" name="phone_one"
											value="${customerSelected.phone_one}"></td>
									<td><input type="text" id="email_one" name="email_one"
											value="${customerSelected.email_one}"></td>
								</tr>
								<tr>
									<td><input type="text" name="name_two"
											value="${customerSelected.name_two}"></td>
									<td><input type="text" name="phone_two"
											value="${customerSelected.phone_two}"></td>
									<td><input type="text" name="email_two"
											value="${customerSelected.email_two}"></td>
								</tr>
								<tr>
									<td><input type="text" name="name_three"
											value="${customerSelected.name_three}"></td>
									<td><input type="text" name="phone_three"
											value="${customerSelected.phone_three}"></td>
									<td><input type="text" name="email_three"
											value="${customerSelected.email_three}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_four"
											value="${customerSelected.name_four}"></td>
									<td><input type="text" name="phone_four"
											value="${customerSelected.phone_four}"></td>
									<td><input type="text" name="email_four"
											value="${customerSelected.email_four}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_five"
											value="${customerSelected.name_five}"></td>
									<td><input type="text" name="phone_five"
											value="${customerSelected.phone_five}"></td>
									<td><input type="text" name="email_five"
											value="${customerSelected.email_five}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_six"
											value="${customerSelected.name_six}"></td>
									<td><input type="text" name="phone_six"
											value="${customerSelected.phone_six}"></td>
									<td><input type="text" name="email_six"
											value="${customerSelected.email_six}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_seven"
											value="${customerSelected.name_seven}"></td>
									<td><input type="text" name="phone_seven"
											value="${customerSelected.phone_seven}"></td>
									<td><input type="text" name="email_seven"
											value="${customerSelected.email_seven}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_eight"
											value="${customerSelected.name_eight}"></td>
									<td><input type="text" name="phone_eight"
											value="${customerSelected.phone_eight}"></td>
									<td><input type="text" name="email_eight"
											value="${customerSelected.email_eight}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_nine"
											value="${customerSelected.name_nine}"></td>
									<td><input type="text" name="phone_nine"
											value="${customerSelected.phone_nine}"></td>
									<td><input type="text" name="email_nine"
											value="${customerSelected.email_nine}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_ten"
											value="${customerSelected.name_ten}"></td>
									<td><input type="text" name="phone_ten"
											value="${customerSelected.phone_ten}"></td>
									<td><input type="text" name="email_ten"
											value="${customerSelected.email_ten}"></td>

								</tr>
	  	  		   		</table>
		    		</div>
  	  		   </div>
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-4 col-md-4">
	   		 			<jstl:if test="${customerSelected.customerName == null}">
	   		 				  <input type="hidden" name="create" value="true">
						      <input id="createCustomerSubmit" type=submit value="Create">
						</jstl:if>
						<jstl:if test="${customerSelected.customerName != null}">
							  <input type="hidden" name="create" value="false">
						      <input id="createCustomerSubmit" type=submit value="Update">
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
					class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
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
			   					        <th>Contact</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="customer" items="${customerList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/creg?customerSelected=${customer.customerName}">
								                    	<jstl:out value="${customer.customerName}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${customer.address}" /></td>
							                    <td><jstl:out value="${customer.name_one}" /></td>
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
					</jstl:if>
		
</div>
</jsp:body>
</t:layout>

