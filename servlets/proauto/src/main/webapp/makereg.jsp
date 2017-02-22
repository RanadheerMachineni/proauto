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
		<div class="pageHeadings"> Make Registration</div>
		<br>
					<div class="col-md-4 col-sm-4 col-xs-4">
		
					<div class="row">
	   					<div class="col-sm-10 col-md-10">
						   	<jstl:if test="${not empty result && result=='sucess'}">
								<div id="sucessDiv" class="successresponse">
								Successfully created/updated make. Please click to view/update
								<a
										href="${pageContext.request.contextPath}/makereg?makeSelected=${makeCreated}">
								                    	<jstl:out value="${makeCreatedName}" />
								                    </a>
								</div>
							</jstl:if>
						</div>
					</div>
					
					<jstl:if test="${makeSelected.name == null}">
						      			<label>Create Make</label>
					</jstl:if>
					<jstl:if test="${makeSelected.name != null}">
						      			<label>Update Make</label>
					</jstl:if>
					
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
			</div>
		
		<form id="makeRegForm" role="form" action="makereg" method="post">  
		    <input type="hidden" name="regType" value="make">
			   <br>
			   		    <input type="hidden" name="makeid" id="makeid"
							value="${makeSelected.id}">
			   
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="makeName">Make :</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="makeName"
									name="makeName" value="${makeSelected.name}">
		    	 	</div>
  	  		   </div>
  	  		  <br>
   		      <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
	   		 			<jstl:if test="${makeSelected.name == null}">
	   		 				  <input type="hidden" name="create" value="true">
						      <input id="createMakeSubmit" type=submit value="Create">
						</jstl:if>
						<jstl:if test="${makeSelected.name != null}">
							  <input type="hidden" name="create" value="false">
						      <input id="createMakeSubmit" type=submit value="Update">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
										href="${pageContext.request.contextPath}/makereg" role="button">Cancel</a>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
		</form>	
  	  		</div>
  	  		
  	  						<div class="col-md-5 col-sm-5 col-xs-5">
	   		 					<br>
	   		 					
	   		 					<form id="makeSearchForm" role="form" action="makereg"
						method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-2 col-md-2">
						      			<label for="searchMakeInput">Search Make</label>
						    		</div>
						   		 	<div class="col-sm-4 col-md-4">
						      			<input type="text" class="form-control"
									id="searchMakeInput" name="searchMakeInput">
						    	 	</div>
						    	 	<div class="col-sm-2 col-md-2">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of Makes</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								        <th>Make</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="make" items="${makeList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/makereg?makeSelected=${make.id}">
								                    	<jstl:out value="${make.name}" />
								                    </a>
												</td>
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
	   		 					</div>
					
					</jstl:if>
		
</div>
</jsp:body>
</t:layout>

