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
					<div class="pageHeadings">Inventory</div>
					<br>
					<div class="formDiv">
					
					<div class="row">
	   					<div class="col-sm-10 col-md-10">
						   	<jstl:if test="${not empty result && result=='sucess'}">
								<div id="sucessDiv" class="successresponse">
								Successfully created/updated Item. Please click to view/update
								<a
								href="${pageContext.request.contextPath}/showinv?purchaseSelected=${purCreatedId}">
								                    	<jstl:out
										value="${purCreatedCode}" />
								                    </a>
								</div>
							</jstl:if>
						</div>
					</div>
					
					<jstl:if test="${purchase.id == null || purchase.id<=0}">
						      			<label>Create Item</label>
					</jstl:if>
					<jstl:if test="${purchase.id != null && purchase.id>0}">
						      			<label>${purchase.code}</label>
					</jstl:if>
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="addtoinvform" role="form" action="addtoinv" method="post">  
		
		
		      <input type="hidden" name="regType" value="inventory">
			  <input type="hidden" name="parid" id="parid" value="${purchase.id}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		   
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="particular">Particular:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="particular"
											name="particular" value="${purchase.particular}">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="code">Code:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="code"
											name="code" value="${purchase.code}">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="make">Make:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="make" id="make">
			 			 		<option value="">Select Make</option>
		  						<jstl:forEach items="${makes}" var="make">
		  				  			<option value="${make.key}"
													${purchase.make == make.key ? 'selected' : ''}>${make.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	 </div>
		       </div>
  	  		  	
  	  		   <div class="row rowspace">
  	  		    	<div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="unit">Unit:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="unit" id="unit">
				 			 		<option value="">Select Unit</option>
			  						<jstl:forEach items="${units}" var="eachUnit">
			  				  			<option value="${eachUnit.key}"
													${purchase.unit == eachUnit.key ? 'selected' : ''}>${eachUnit.value}</option>
			  						</jstl:forEach>
								</select>
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="desc">Desc:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="desc"
											name="desc" value="${purchase.desciption}">
			    	 	</div>
		    	 	 </div>
  	  		       	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="type">Type:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      				<select class="form-control" name="type" id="type">
				 			 		<option value="">Select Type</option>
			  						<jstl:forEach items="${types}" var="eachType">
			  				  			<option value="${eachType.key}"
													${purchase.tooltypeId == eachType.key ? 'selected' : ''}>${eachType.value}</option>
			  						</jstl:forEach>
								</select>
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
											name="authouredby" value="${purchase.authouredby}">
			    	 	</div>
		    	 	 </div>
		    	 	 <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="repository">Repository:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="repository"
										readonly="readonly"	 name="repository" value="${purchase.repository}">
			    	 	</div>
		    	 	 </div>
		    	</div>
		    	<br>
		    	 <div class="row rowspace">
  	  		    	<div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="additems">Add Items:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="additems"
											name="additems" value="">
			    	 	</div>
		    	 	 </div>
		    	</div>
		    	<br>
		    	<div class="row">
		   		 	<div class="col-sm-2 col-md-2">
		   		 		<jstl:if test="${purchase.id == null || purchase.id<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <button id="createPurchaseSubmit" type="submit"
											class="btn btn-primary">Create</button>
						</jstl:if>
						<jstl:if test="${purchase.id != null && purchase.id > 0}">
							  <input type="hidden" name="create" value="false">
						      <button id="createPurchaseSubmit" type="submit"
											class="btn btn-primary">Update</button>
						       <a class="btn btn-default"
											href="${pageContext.request.contextPath}/showinv" role="button">Cancel</a>
						</jstl:if>
		   		 	</div>
				</div>
				<br>
					<div class="informativeText">Item History</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>Time</th>
										<th>Authored By</th>
										<th>Quantity</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="history" items="${purchase.purchaseHistory}">
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

