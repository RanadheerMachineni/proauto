<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
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
					<jstl:if
						test="${purchase.id == null || purchase.id<=0}">
						      			<label>Create Job Card</label>
					</jstl:if>
					<jstl:if
						test="${purchase.id != null && purchase.id>0}">
						      			<label>${jobCardSelected.name}</label>
					</jstl:if>
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="jobcardRegForm" role="form" action="createjobcard"
						method="post">  
		
		
		      <input type="hidden" name="regType" value="jobcard">
			  <input type="hidden" name="jid" id="jid"
							value="${jobCardSelected.id}">
			   <br>
			   
  	  		   <div class="row rowspace">
  	  		   
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="jname">Name:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
   		 					<jstl:if
										test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jname" class="block show">${jobCardSelected.name}</p>
   		 						<input type="text" class="form-control block hide"
											id="jname" name="jname" value="${jobCardSelected.name}">
							</jstl:if>
							<jstl:if
										test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="jname"
											name="jname" value="${jobCardSelected.name}">
							</jstl:if>
			    	 	</div>
		    	 	</div>
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-1 col-md-1">
			      			<label class="control-label" for="jdesc">Description:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			   		 	
			   		 		<jstl:if
										test="${jobCardSelected.id != null && jobCardSelected.id>0}">
   		 						<p id="jdesc" class="block show">${jobCardSelected.name}</p>
   		 						<input type="text" class="form-control block hide"
											id="jdesc" name="jdesc" value="${jobCardSelected.desc}">
							</jstl:if>
							<jstl:if
										test="${jobCardSelected.id == null || jobCardSelected.id<=0}">
								<input type="text" class="form-control block show" id="jdesc"
											name="jdesc" value="${jobCardSelected.desc}">
							</jstl:if>

			    	 	</div>
		    	 	</div>
		    	</div>
		    </form>
		    </div>
		    </div>
		    </jstl:if>
</div>
</jsp:body>
</t:layout>

