<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
function ValidateForm(form){
	var vName =  $("#vName").val();
 	var vAddress =  $("#vAddress").val();
 	var vnameOne =  $("#name_one").val();
 	var vPhoneOne =  $("#phone_one").val();
 	var vEmailOne =  $("#email_one").val();
	//var div = document.getElementById('errorDiv');
 	if(!vName || !vAddress || !vnameOne){
 		alert('Vendor Name/Address/First contact can not be empty');
 		//event.preventDefault();
 		return false;
 	}
 	
 	var pattern = /[0-9\-\(\)\s]+/;
    if(!vPhoneOne || (vPhoneOne.length < 10) || (!pattern.test(vPhoneOne)))
    {
         alert('Please enter a valid phone number(First row).');
	 	 return false;
    }
    
    var emailRe = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
 	if(!vEmailOne || !emailRe.test(vEmailOne))
 	{
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
		<div class="pageHeadings"> Vendor Registration</div>
		<br>
					<div class="col-md-4 col-sm-4 col-xs-4">
		
					<jstl:if test="${vendorSelected.vendorName == null}">
						      			<label>Create Vendor</label>
					</jstl:if>
					<jstl:if test="${vendorSelected.vendorName != null}">
						      			<label>Update Vendor</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
			</div>
		
		<form id="vendorRegForm" role="form" action="vreg" method="post" onsubmit="return ValidateForm(this);">  
		    <input type="hidden" name="regType" value="vendor">
			   <br>
			   		    <input type="hidden" name="vid" id="vid" value="${vendorSelected.vendorId}">
			   
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vName">Vendor Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="vName"
									name="vName" value="${vendorSelected.vendorName}">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<textarea  class="form-control" id="vAddress" name="vAddress">${vendorSelected.address}</textarea>
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
									<td><input type="text" id="name_one" name="name_one" value="${vendorSelected.name_one}"></td>
									<td><input type="text" id="phone_one" name="phone_one" value="${vendorSelected.phone_one}"></td>
									<td><input type="text" id="email_one" name="email_one" value="${vendorSelected.email_one}"></td>
								</tr>
								<tr>
									<td><input type="text" name="name_two" value="${vendorSelected.name_two}"></td>
									<td><input type="text" name="phone_two" value="${vendorSelected.phone_two}"></td>
									<td><input type="text" name="email_two" value="${vendorSelected.email_two}"></td>
								</tr>
								<tr>
									<td><input type="text" name="name_three" value="${vendorSelected.name_three}"></td>
									<td><input type="text" name="phone_three" value="${vendorSelected.phone_three}"></td>
									<td><input type="text" name="email_three" value="${vendorSelected.email_three}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_four" value="${vendorSelected.name_four}"></td>
									<td><input type="text" name="phone_four" value="${vendorSelected.phone_four}"></td>
									<td><input type="text" name="email_four" value="${vendorSelected.email_four}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_five" value="${vendorSelected.name_five}"></td>
									<td><input type="text" name="phone_five" value="${vendorSelected.phone_five}"></td>
									<td><input type="text" name="email_five" value="${vendorSelected.email_five}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_six" value="${vendorSelected.name_six}"></td>
									<td><input type="text" name="phone_six" value="${vendorSelected.phone_six}"></td>
									<td><input type="text" name="email_six" value="${vendorSelected.email_six}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_seven" value="${vendorSelected.name_seven}"></td>
									<td><input type="text" name="phone_seven" value="${vendorSelected.phone_seven}"></td>
									<td><input type="text" name="email_seven" value="${vendorSelected.email_seven}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_eight" value="${vendorSelected.name_eight}"></td>
									<td><input type="text" name="phone_eight" value="${vendorSelected.phone_eight}"></td>
									<td><input type="text" name="email_eight" value="${vendorSelected.email_eight}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_nine" value="${vendorSelected.name_nine}"></td>
									<td><input type="text" name="phone_nine" value="${vendorSelected.phone_nine}"></td>
									<td><input type="text" name="email_nine" value="${vendorSelected.email_nine}"></td>

								</tr>
								<tr>
									<td><input type="text" name="name_ten" value="${vendorSelected.name_ten}"></td>
									<td><input type="text" name="phone_ten" value="${vendorSelected.phone_ten}"></td>
									<td><input type="text" name="email_ten" value="${vendorSelected.email_ten}"></td>

								</tr>
	  	  		   		</table>
		    		</div>
  	  		   </div>
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-4 col-md-4">
	   		 			<jstl:if test="${vendorSelected.vendorName == null}">
	   		 				  <input type="hidden" name="create" value="true">
						      <input id="createVendorSubmit" type=submit value="Create">
						</jstl:if>
						<jstl:if test="${vendorSelected.vendorName != null}">
							  <input type="hidden" name="create" value="false">
						      <input id="createVendorSubmit" type=submit value="Update">
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
	   		 					<form id="vendorSearchForm" role="form" action="vreg" method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-6 col-md-6">
						      			<label for="searchVendorInput">Search Vendor</label>
						    		</div>
						   		 	<div class="col-sm-6 col-md-6">
						      			<input type="text" class="form-control"
								id="searchVendorInput" name="searchVendorInput">
						    	 	</div>
  	  		  					</div>
  	  		  					 <div class="row">
						   		 	<div class="col-sm-10 col-md-10">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of existing Vendors</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>Vendor Name</th>
								        <th>Address</th>
			   					        <th>Contact</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="vendor" items="${vendorList}">
							                <tr>
							                    <td>
							                    	<a href="${pageContext.request.contextPath}/vreg?vendorSelected=${vendor.vendorName}">
								                    	<jstl:out value="${vendor.vendorName}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${vendor.address}" /></td>
							                    <td><jstl:out
											value="${vendor.name_one}" /></td>
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
					</jstl:if>
		
</div>
</jsp:body>
</t:layout>

