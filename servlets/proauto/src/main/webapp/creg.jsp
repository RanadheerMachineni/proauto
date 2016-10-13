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
		<div class="pageHeadings"> Customer Registration</div>
		<form role="form" action="Registrations" method="post">  
		    <input type="hidden" name="regType" value="customer">
			<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
			   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cName">Customer Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cName"
									name="cName">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<textarea class="form-control" id="cAddress"
									name="cAddress"></textarea>
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cEmail">Email:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cEmail"
									name="cEmail">
		    	 	</div>
  	  		   </div>
			   <br>
 	  		 
 	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cFirstContact">First Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cFirstContact"
									name="cFirstContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cSecondContact">Second Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cSecondContact"
									name="cSecondContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="cThirdContact">Third Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="cThirdContact"
									name="cThirdContact">
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
							<div class="informativeText centerAligned">Customer registered successfully</div>
						</jstl:if>
						<jstl:if test="${not empty registered and !registered}">
							<div class="informativeText centerAligned">Customer registration failed</div>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<div class="row">
	   		 		<div class="informativeText">List of existing Customer</div>
	  			</div>
	  			<br>
	  			<sql:setDataSource
				        var="proauto_db"
				        driver="com.mysql.jdbc.Driver"
				        url="jdbc:mysql://localhost:3306/proauto_db"
				        user="proauto" password="proauto"
				/>
     
   				<sql:query var="listCustomers"   dataSource="${proauto_db}">
        			select customer_name,address,customer_email,customer_first_contact,customer_second_contact,customer_third_contact,create_date from customer;
   				</sql:query>
	  			<div class="row">
	   		 		<table class="table table-bordered">
					    <thead>
					      <tr>
					        <th>Customer Name</th>
					        <th>Address</th>
   					        <th>Email</th>
   					        <th>First Contact</th>
   					        <th>Second Contact</th>
   					        <th>Third Contact</th>
							<th>Created</th>
					      </tr>
					    </thead>
					    <tbody>
					      <jstl:forEach var="customer" items="${listCustomers.rows}">
				                <tr>
				                    <td><jstl:out value="${customer.customer_name}" /></td>
				                    <td><jstl:out value="${customer.address}" /></td>
				                    <td><jstl:out value="${customer.customer_email}" /></td>
				                    <td><jstl:out value="${customer.customer_first_contact}" /></td>
				                    <td><jstl:out value="${customer.customer_second_contact}" /></td>
				                    <td><jstl:out value="${customer.customer_third_contact}" /></td>
				                    <td><jstl:out value="${customer.create_date}" /></td>
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

