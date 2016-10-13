<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<t:layout>
<jsp:body>
<div class="container">
	<jstl:if test="${empty role}">
		<p>You are not logged in. Go to the <a href="${pageContext.request.contextPath}/index.jsp">Login</a>.</p>
	</jstl:if>
	<jstl:if test="${not empty role}">
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;dms app comes here....
	</jstl:if>
</div>
</jsp:body>
</t:layout>

