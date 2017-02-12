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
					<div class="pageHeadings"> Machine Usage</div>
					<br>
					<div class="formDiv">
					<jstl:if test="${machineUsage.id == null || machineUsage.id<=0}">
						      			<label>Enter Machine Usage</label>
					</jstl:if>
					<jstl:if test="${machineUsage.id != null && machineUsage.id>0}">
						      			<label>${machineUsage.name}</label>
					</jstl:if>
				   	<div class="row">
	   					<div class="col-sm-10 col-md-10">
					   	<jstl:if test="${not empty error}">
							<div id="errorDiv" class="error">${error}</div>
						</jstl:if>
			
					</div>
					</div>
		
		<form id="jobusageForm" role="form" action="aadmachineuse"
							method="post">  
		
		
		      <input type="hidden" name="regType" value="machineusage">
			  <input type="hidden" name="muid" id="muid"
								value="${machineUsage.id}">
			   <br>
			   
  			 <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="mid">Machine : </label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="machine" id="machine">
			 			 		<option value="">Select Machine</option>
		  						<jstl:forEach items="${machines}" var="eachMachine">
		  				  			<option value="${eachMachine.key}"
													${eachTask.machineId == eachMachine.key ? 'selected' : ''}>${eachMachine.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	</div>
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="muDate" class="control-label">Date:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="muDate"
											name="muDate" value="${machineUsage.date}">
			    	 	</div>
			    	</div>
			    	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="shift" class="control-label">Shift:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="machine" id="shift">
			 			 		<option value="">Select Shift</option>
		  						<jstl:forEach items="${shifts}" var="eachShift">
		  				  			<option value="${eachShift.key}"
													${machineUsage.shift == eachShift.key ? 'selected' : ''}>${eachShift.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
			    	</div>
  	  		  </div>
  	  		  <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="mid">Customer : </label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="customer" id="customer">
				 			 		<option value="">Select Customer</option>
			  						<jstl:forEach items="${customers}" var="eachCustomer">
			  				  			<option value="${eachCustomer.key}"
													${machineUsage.customer == eachCustomer.key ? 'selected' : ''}>${eachCustomer.value}</option>
			  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	</div>
		    	 	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="po" class="control-label">PO:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="po" id="po">
				 			 		<option value="">Select PO</option>
			  						<jstl:forEach items="${poList}" var="eachPo">
			  				  			<option value="${eachPo.key}"
													${machineUsage.pid == eachPo.key ? 'selected' : ''}>${eachPo.value}</option>
			  						</jstl:forEach>
								</select>
			    	 	</div>
			    	</div>
			    	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="task" class="control-label">Task:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="task" id="task">
			 			 		<option value="">Select Task</option>
		  						<jstl:forEach items="${tasks}" var="eachTask">
		  				  			<option value="${eachTask.key}"
													${machineUsage.taskid == eachTask.key ? 'selected' : ''}>${eachTask.value}</option>
		  						</jstl:forEach>
							</select>
			    	 	</div>
			    	</div>
  	  		  </div>
  	  		  
  	  		  <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="programmer">Programmer : </label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="programmer"
											id="programmer">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}"
													${machineUsage.programmer == eachEmp.key ? 'selected' : ''}>${eachEmp.value}</option>
							  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	</div>
		    	 	<div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="operator">Operator : </label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="operator" id="operator">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}"
													${machineUsage.operator == eachEmp.key ? 'selected' : ''}>${eachEmp.value}</option>
							  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	</div>
			    	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="actualTime" class="control-label">Actual Time:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="actualTime"
											name="actualTime" value="${machineUsage.actualTime}">
			    	 	</div>
			    	</div>
  	  		  </div>
  	  		  
  	  		  <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
		      			<label>Tool Usage</label>
		    		</div>
  	  		   </div>
  	  		  <div class="row rowspace">
  	  		        <div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="item">Item : </label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="item" id="item">
								 			 		<option value="">Select Item</option>
							  						<jstl:forEach items="${items}" var="eachItem">
							  				  			<option value="${eachItem.key}"
													${machineUsage.programmer == eachEmp.key ? 'selected' : ''}>${eachEmp.value}</option>
							  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	</div>
		    	 	<div class="control-group">
	  	  		   		<div class="col-sm-2 col-md-2">
			      			<label class="control-label" for="operator">Operator : </label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<select class="form-control" name="operator" id="operator">
								 			 		<option value="">Select</option>
							  						<jstl:forEach items="${employees}" var="eachEmp">
							  				  			<option value="${eachEmp.key}"
													${machineUsage.operator == eachEmp.key ? 'selected' : ''}>${eachEmp.value}</option>
							  						</jstl:forEach>
							</select>
			    	 	</div>
		    	 	</div>
			    	<div class="control-group">
			    	 	<div class="col-sm-2 col-md-2">
			      			<label for="actualTime" class="control-label">Actual Time:</label>
			    		</div>
			   		 	<div class="col-sm-2 col-md-2 controls">
			      			<input type="text" class="form-control" id="actualTime"
											name="actualTime" value="${machineUsage.actualTime}">
			    	 	</div>
			    	</div>
  	  		  </div>
  	  		  
  	  		   <br>
  	  		      <jstl:if
								test="${machineUsage.id != null && machineUsage.id > 0 && machineUsage.tools!=null}">
	  	  		   <div class="row rowspace">
			   		 	<div id="alreadySavedTools" class="col-sm-12 col-md-12">
			   		 		<table class="form-table" id="customFieldsExisting">
				   		 	<jstl:forEach var="eachTool" items="${machineUsage.tools}">
				   			 	
				                <tr>
				                <td class="joboperationbox">
									<input type="text" class="form-control" id="code" name="code"
														value="${eachTool.code}" placeholder="code" />
														
								</td>
													<td class="joboperationbox">							
										<input type="text" class="form-control" id="make" name="make"
														value="${eachTool.make}" placeholder="make" />
								</td>
													<td class="joboperationbox">															
									<input type="text" class="form-control" id="quantity"
														name="quantity" value="${eachTool.quantity}"
														placeholder="quantity" /> 
								</td>
													<td class="joboperationbox">															
									<input type="text" class="form-control" id="approver"
														name="approver" value="${eachTool.approver}"
														placeholder="approver" /> 
								</td>
													<td class="joboperationbox">															
									<input type="button" class="deleteButton" value="Delete" />
								</td>
				                </tr>
			           		 </jstl:forEach>
			           		 </table>
			    	 	</div>
				   </div>
				</jstl:if>
				
			   <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
	  	  		   	
	  	  		   	<table class="form-table" id="customFields">
						<tr valign="top">
							<td class="joboperationbox">
								 <div>
				                 	 	 <select class="form-control" name="tool"
														id="tool">
							 			 		<option value="">Select</option>
						  						<jstl:forEach items="${tools}" var="eachTool">
						  				  			<option value="${eachTool.key}">${eachTool.value}</option>
						  						</jstl:forEach>
										 </select>
								 </div> 
							</td>
											<td class="joboperationbox">												
								<input type="text" class="form-control" id="make" name="make"
												value="" placeholder="make" />
							</td>
											<td class="joboperationbox">												
								<input type="text" class="form-control" id="quantity"
												name="quantity" value="" placeholder="quantity" /> 
							</td>
											<td class="joboperationbox">												
								<input type="text" class="v" id="approver" name="approver"
												value="" placeholder="approver" />
							</td>
						</tr>
					</table>

		    		</div>
  	  		   </div>
  	  		   <div class="row rowspace">
  	  		   		<div class="col-sm-12 col-md-12">
		      			    <p id="insertRowParent">
    							<input class="insertRow" type="button" value="Add Tool">
							</p>
		    		</div>
  	  		   </div>
			   <div class="row">
	   		 		<div class="col-sm-8 col-md-8">
						<jstl:if test="${machineUsage.id == null || machineUsage.id<=0}">
	   		 				  <input type="hidden" name="create" value="true">
						      <!--  <input id="createEmployeeSubmit" type=submit value="Create">-->
						      <button type="submit" class="btn btn-primary">Create</button>
						      
						</jstl:if>
					<jstl:if test="${machineUsage.id != null && machineUsage.id > 0}">
							  <input type="hidden" name="create" value="false">
						      <!-- <input id="createEmployeeSubmit" type=submit value="Update"> -->
						      <butto type="submit" class="btn btn-primary">Update</button>
						      
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						      <a class="btn btn-default"
											href="${pageContext.request.contextPath}/machineusage"
											role="button">Cancel</a>
						
									</jstl:if>
	    	 		</div>
	  			</div>
		    	 	
	  			
	  			<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
		</form>	
		
		</div>
		
			<div class="col-md-6 col-sm-6 col-xs-6">
	   		 					<br>
	   		 					<form id="machineUseSearchForm" role="form" action="machineusage"
							method="GET">  
	   		 					<div class="row">
				  	  		   		<div class="col-sm-6 col-md-6">
						      			<label for="searchMUInput">Search Machine Use</label>
						    		</div>
						   		 	<div class="col-sm-6 col-md-6">
						      			<input type="text" class="form-control"
										id="searchMUInput" name="searchMUInput">
						    	 	</div>
  	  		  					</div>
  	  		  					 <div class="row">
						   		 	<div class="col-sm-10 col-md-10">
						      			<input type=submit value="Search">
						    	 	</div>
  	  		  					</div>
  	  		  					</form>
  	  		   					<br>
  	  		   		
						</div>
			
			
			</jstl:if>	
</div>
</jsp:body>
</t:layout>

