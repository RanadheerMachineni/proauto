<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	function customLoad() {
	}
	$(document).ready(function() {

		$("#date").datepicker();
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
					<div class="pageHeadings">Raw Material</div>
					<br>
					<div class="formDiv">
					
					<div class="row">
	   					<div class="col-sm-10 col-md-10">
						   	<jstl:if test="${not empty result && result=='sucess'}">
								<div id="sucessDiv" class="successresponse">
								Successfully created/updated Item. Please click to view/update
								<a
								href="${pageContext.request.contextPath}/showrawm?rmSelected=${rmCreatedId}">
								                    	<jstl:out
										value="${rmCreatedCode}" />
								                    </a>
								</div>
							</jstl:if>
						</div>
					</div>
					
					<jstl:if test="${rm.id == null || rm.id<=0}">
						      			<label>Create Item</label>
					</jstl:if>
					<jstl:if test="${rm.id != null && rm.id>0}">
						      			<label>${rm.rawmname}</label>
					</jstl:if>
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="addtorawmform" role="form" action="addtorawm" method="post">  
		
		
		      <input type="hidden" name="regType" value="rawm">
			  <input type="hidden" name="rmid" id="rmid" value="${rm.id}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		   
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="rawmname">Raw Material:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="rawmname"
											name="rawmname" value="${rm.rawmname}">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="vendor">Vendor:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="vendor" id="vendor">
			 			 		<option value="">Select Vendor</option>
		  						<jstl:forEach items="${vendors}" var="vendor">
		  				  			<option value="${vendor.key}"
													${rm.vendor == vendor.key ? 'selected' : ''}>${vendor.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	 </div>
		       </div>
  	  		  	
  	  		   <div class="row rowspace">
  	  		    	<div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="length">Length:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="length"
											name="length" value="${rm.length}">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="width">Width:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="width"
											name="width" value="${rm.width}">
			    	 	</div>
		    	 	 </div>
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="thickness">Thickness:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="thickness"
											name="thickness" value="${rm.thickness}">
			    	 	</div>
		    	 	 </div>
		    	</div>
		    	
		    	 <div class="row rowspace">
  	  		    	<div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="authouredby">Authoured By:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="authouredby"
											name="authouredby" value="${rm.authouredby}">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="repository">Repository:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="repository"
										readonly="readonly"	 name="repository" value="${rm.repository}">
			    	 	</div>
		    	 	 </div>
		    	</div>
		    	<br>
		    	 <div class="row rowspace">
  	  		    	<div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="quantity">Add Quantity:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="quantity"
											name="quantity" value="">
			    	 	</div>
		    	 	 </div>
		    	</div>
		    	<br>
		    	<div class="row">
		   		 	<div class="col-sm-2 col-md-2">
		   		 		<jstl:if test="${rm.id == null || rm.id<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <button id="createRMSubmit" type="submit"
											class="btn btn-primary">Create</button>
						</jstl:if>
						<jstl:if test="${rm.id != null && rm.id > 0}">
							  <input type="hidden" name="create" value="false">
						      <button id="createRMSubmit" type="submit"
											class="btn btn-primary">Update</button>
						       <a class="btn btn-default"
											href="${pageContext.request.contextPath}/showrawm" role="button">Cancel</a>
						</jstl:if>
		   		 	</div>
				</div>
				<br>
					<div class="informativeText">Raw Material History</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								      	<th>Date</th>
										<th>Authoured By</th>
										<th>Quantity</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="history" items="${rm.rmHistory}">
							                <tr>
							              		<td><jstl:out value="${history.date}" /></td>
							                    <td><jstl:out value="${history.authouredby}" /></td>
							                    <td><jstl:out value="${history.quantity}" /></td>
							                </tr>
							            </jstl:forEach>
								    </tbody>
					</table>
				<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
		    </form>
		    </div>
		    </div>

		    </jstl:if>
</div>
</jsp:body>
</t:layout>

