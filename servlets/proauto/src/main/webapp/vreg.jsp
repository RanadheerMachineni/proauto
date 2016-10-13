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
		<div class="pageHeadings"> Vendor Registration</div>
		<form role="form" action="Registrations" method="post">  
		    <input type="hidden" name="regType" value="vendor">
			<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
			   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vName">Vendor Name:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="vName"
									name="vName">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vAddress">Address:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<textarea class="form-control" id="vAddress"
									name="vAddress"></textarea>
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vEmail">Email:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="vEmail"
									name="vEmail">
		    	 	</div>
  	  		   </div>
			   <br>
 	  		 
 	  		  <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vFirstContact">First Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="vFirstContact"
									name="vFirstContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vSecondContact">Second Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="vSecondContact"
									name="vSecondContact">
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="vThirdContact">Third Contact:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="vThirdContact"
									name="vThirdContact">
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
							<div class="informativeText centerAligned">Vendor registered successfully</div>
						</jstl:if>
						<jstl:if test="${not empty registered and !registered}">
							<div class="informativeText centerAligned">Vendor registration failed</div>
						</jstl:if>
	    	 		</div>
	  			</div>
	  			<br>
	  			<div class="row">
	   		 		<div class="informativeText">List of existing Vendors</div>
	  			</div>
	  			<br>
	  			<sql:setDataSource
				        var="proauto_db"
				        driver="com.mysql.jdbc.Driver"
				        url="jdbc:mysql://localhost:3306/proauto_db"
				        user="proauto" password="proauto"
				/>
     
   				<sql:query var="listVendors"   dataSource="${proauto_db}">
        			select vendor_name,address,vendor_email,vendor_first_contact,vendor_second_contact,vendor_third_contact,create_date from vendor;
   				</sql:query>
	  			<div class="row">
	   		 		<table class="table table-bordered">
					    <thead>
					      <tr>
					        <th>Vendor Name</th>
					        <th>Address</th>
   					        <th>Email</th>
   					        <th>First Contact</th>
   					        <th>Second Contact</th>
   					        <th>Third Contact</th>
							<th>Created</th>
					      </tr>
					    </thead>
					    <tbody>
					      <jstl:forEach var="vendor" items="${listVendors.rows}">
				                <tr>
				                    <td><jstl:out value="${vendor.vendor_name}" /></td>
				                    <td><jstl:out value="${vendor.address}" /></td>
				                    <td><jstl:out value="${vendor.vendor_email}" /></td>
				                    <td><jstl:out value="${vendor.vendor_first_contact}" /></td>
				                    <td><jstl:out value="${vendor.vendor_second_contact}" /></td>
				                    <td><jstl:out value="${vendor.vendor_third_contact}" /></td>
				                    <td><jstl:out value="${vendor.create_date}" /></td>
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

