<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<t:layout>
	<jsp:body>
<div class="container">
	<jstl:if test="${empty role}">
		<p>You are not logged in. Go to the <a
						href="${pageContext.request.contextPath}/index.jsp">Login</a>.</p>
	</jstl:if>
	<jstl:if test="${not empty role}">
		<div class="pageHeadings"> Employee Registration</div>
		<form role="form" action="Registrations" method="post">  
			<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
			   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empName">Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="empName"
									name="empName">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empPassword">Password:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="empPassword"
									name="empPassword">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empEmail">Email:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="empEmail"
									name="empEmail">
		    	 	</div>
  	  		   </div>
			   <br>
 	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="empRole">Role:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<select class="form-control" name="empRole">  
								<option>jobcard</option>  
								<option>costing</option>
								<option>dms</option>
								<option>admin</option>  
						</select>  
		    	 	</div>
  	  		   </div>
  	  		   <br>
			   <div class="row">
	   		 		<div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4">
						<input type=submit value="Register">
	    	 		</div>
	  			</div>
	  			<br>
  	  		</div>
		</form>	
		
		<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
		
			<div class="row">
	   		 		<div class="col-sm-10 col-md-10">
						<jstl:if test="${not empty registered and registered}">
							<div class="informativeText centerAligned">Employee registered successfully</div>
						</jstl:if>
						<jstl:if test="${not empty registered and !registered}">
							<div class="informativeText centerAligned">Employee registration failed</div>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<div class="row">
	   		 		<div class="informativeText">List of existing employees</div>
	  			</div>
	  			<br>
	  			<sql:setDataSource
				        var="proauto_db"
				        driver="com.mysql.jdbc.Driver"
				        url="jdbc:mysql://localhost:3306/proauto_db"
				        user="proauto" password="proauto"
				/>
     
   				<sql:query var="listEmployees"   dataSource="${proauto_db}">
        			select employee_name,role from employee;
   				</sql:query>
	  			<div class="row">
	   		 		<table class="table table-bordered">
					    <thead>
					      <tr>
					        <th>Employee Name</th>
					        <th>Role</th>
					      </tr>
					    </thead>
					    <tbody>
					      <jstl:forEach var="employee" items="${listEmployees.rows}">
				                <tr>
				                    <td><jstl:out value="${employee.employee_name}" /></td>
				                    <td><jstl:out value="${employee.role}" /></td>
				                </tr>
				            </jstl:forEach>
					    </tbody>
					  </table>

	  			</div>
		</div>
		
		
		
		
	</jstl:if>
</div>
</jsp:body>
</t:layout>

