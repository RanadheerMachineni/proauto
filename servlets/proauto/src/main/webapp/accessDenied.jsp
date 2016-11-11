<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
<jsp:body>
<div class="container">
	
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;<p>
								${pageContext.request.userPrincipal.name}, You are not authorized for this page
							</p>
	
</div>
</jsp:body>
</t:layout>

