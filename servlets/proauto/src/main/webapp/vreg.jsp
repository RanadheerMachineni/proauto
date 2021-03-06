<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	
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
		
					<div class="row">
	   					<div class="col-sm-10 col-md-10">
						   	<jstl:if test="${not empty result && result=='sucess'}">
								<div id="sucessDiv" class="successresponse">
								Successfully created/updated employee. Please click to view/update
								<a
										href="${pageContext.request.contextPath}/vreg?vendorSelected=${venCreated}">
								                    	<jstl:out value="${venCreatedName}" />
								                    </a>
								</div>
							</jstl:if>
						</div>
					</div>
					
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
		
		<form id="vendorRegForm" role="form" action="vreg" method="post">  
		    <input type="hidden" name="regType" value="vendor">
			   <br>
			   		    <input type="hidden" name="vid" id="vid"
							value="${vendorSelected.vendorId}">
			   
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
		      			<textarea class="form-control" id="vAddress" name="vAddress">${vendorSelected.address}</textarea>
		    	 	</div>
  	  		   </div>
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
	   		 			<jstl:if test="${vendorSelected.vendorName == null}">
	   		 				  <input type="hidden" name="create" value="true">
						      <input id="createVendorSubmit" type=submit value="Create">
						</jstl:if>
						<jstl:if test="${vendorSelected.vendorName != null}">
							  <input type="hidden" name="create" value="false">
						      <input id="createVendorSubmit" type=submit value="Update">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
										href="${pageContext.request.contextPath}/vreg" role="button">Cancel</a>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
		</form>	
  	  		</div>
  	  		
  	  						<div class="col-md-10 col-sm-10 col-xs-10">
	   		 					<br>
	   		 					
	   		 					<form id="vendorSearchForm" role="form" action="vreg"
						method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-1 col-md-1">
						      			<label for="searchVendorInput">Search Vendor</label>
						    		</div>
						   		 	<div class="col-sm-2 col-md-2">
						      			<input type="text" class="form-control"
									id="searchVendorInput" name="searchVendorInput">
						    	 	</div>
						    	 	<div class="col-sm-1 col-md-1">
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
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="vendor" items="${vendorList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/vreg?vendorSelected=${vendor.vendorName}">
								                    	<jstl:out value="${vendor.vendorName}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${vendor.address}" /></td>
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
	   		 					</div>
					
					</jstl:if>
		
</div>
</jsp:body>
</t:layout>

