<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html>
<body>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../../../${pageContext.request.contextPath}/css/template.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<!-- Header -->

	<div class="container">
		<div class="form-group">
			<img class="logo-customer" alt=""
				src="../../../${pageContext.request.contextPath}/images/customerlogo.png">
			<img class="logo-proauto" alt=""
				src="../../../${pageContext.request.contextPath}/images/proautologo.png">

			<div class="headerContent">
				<h3>Ennem Excel Engineering(P) Limited</h3>
			</div>
			<div class="straightLine"></div>

			<%
				String role = null;
				role = (String) session.getAttribute("role");
				System.out.println("role in tmplate  = " + role);
			%>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">

				<%
					if (role != null) {
				%>
				<li class="active"><a href="${pageContext.request.contextPath}/dashboard.jsp" role="tab"
					data-toggle="tab">Dashboard</a></li>
				<%
					}
				%>
				<%
					if (role != null && (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("jobcard"))) {
				%>
				<li><a href="${pageContext.request.contextPath}/jobcard.jsp" role="tab" data-toggle="tab">Job
						Card</a></li>
				<%
					}
				%>
				<%
					if (role != null && (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("dms"))) {
				%>
				<li><a href="${pageContext.request.contextPath}/dms.jsp" role="tab" data-toggle="tab">DMS</a></li>
				<%
					}
				%>
				<%
					if (role != null && (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("costing"))) {
				%>
				<li><a href="${pageContext.request.contextPath}/costing.jsp" role="tab" data-toggle="tab">Costing</a></li>
				<%
					}
				%>
				<%
					if (role != null && role.equalsIgnoreCase("admin")) {
				%>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Registration Forms <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/ereg.jsp" role="tab" data-toggle="tab">Employee
								Registration</a></li>
						<li><a href="${pageContext.request.contextPath}/vreg.jsp" role="tab" data-toggle="tab">Vendor
								Registration</a></li>
						<li><a href="${pageContext.request.contextPath}/creg.jsp" role="tab" data-toggle="tab">Customer
								Registration</a></li>
					</ul></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<div id="body">
		<jsp:doBody />
	</div>

	<!-- <div id="pagefooter">
			<p>Copyright © 2016</p>
	</div> -->
</body>
</html>