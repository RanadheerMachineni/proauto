<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
	<jsp:body>
<div class="container">
	<jstl:if test="${empty role}">
		<p>You are not logged in. Go to the <a
						href="${pageContext.request.contextPath}/index.jsp">Login</a>.</p>
	</jstl:if>
	<jstl:if test="${not empty role}">
		<form class="form-horizontal" action="Registrations" method="post">  
  	  		<div class="form-group">
				<label for="userName">Name:</label>	<input class="form-control" type="text" id="userName" name="userName" /> 
			</div>	
			<div class="form-group">
							<label for="userPass">Password:</label>	<input class="form-control" type="password" id="userPass" name="userPass" />
			</div>	
			<div class="form-group">
							<label for="userEmail">Email:</label>	<input class="form-control" type="text" id="userEmail" name="userEmail" />  
			</div>	
			<div class="form-group">
							<label for="userRole">Role:</label>	
							<select name="userRole">  
									<option>admin</option>  
									<option>jobcard</option>  
									<option>costing</option>
									<option>dms</option>  
				  
							</select>  
  							<br /><br />   
			</div>
			<input type="submit" value="Register" />  
		</form>	
	</jstl:if>
</div>
</jsp:body>
</t:layout>

