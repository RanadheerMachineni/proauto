<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
	$(document).ready(function() {

		function customLoad() {
		}

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
  	  				<br>
						<div class="col-md-10 col-sm-10 col-xs-10">
	   		 					<br>
	   		 					<form id="jobcardSearchForm" role="form"
						action="searchjobcard" method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-1 col-md-1">
						      			<label for="searchJobcardInput">Search Jobcard</label>
						    		</div>
						   		 	<div class="col-sm-2 col-md-2">
						      			<input type="text" class="form-control"
									id="searchJobcardInput" name="searchJobcardInput">
						    	 	</div>
						    	 	<div class="col-sm-1 col-md-1">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   					<div class="informativeText">List of Jobcards</div>
  	  		   					<br>
				   		 		<table class="table table-bordered">
								    <thead>
								      <tr>
								    	<th>Name</th>
								        <th>Description</th>
										<th>PO</th>
										<th>Customer</th>
								      </tr>
								    </thead>
								    <tbody>
								      <jstl:forEach var="jobcard" items="${jobcardList}">
							                <tr>
							                    <td>
							                    	<a
										href="${pageContext.request.contextPath}/createjobcard?jobcardSelected=${jobcard.id}">
								                    	<jstl:out value="${jobcard.name}" />
								                    </a>
												</td>
							                    <td><jstl:out value="${jobcard.desc}" /></td>
							                    <td><jstl:out value="${jobcard.po}" /></td>
							                    <td><jstl:out
											value="${jobcard.customer}" /></td>
							                    
							                </tr>
							            </jstl:forEach>
								    </tbody>
								  </table>
						</div>
  	  		
					</jstl:if>
		
</div>
</jsp:body>


</t:layout>

