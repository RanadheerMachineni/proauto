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

			<jstl:if test="${not empty role}">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">


					<li class="active"><a href="#dashboardtab" role="tab"
						data-toggle="tab">Dashboard</a></li>

					<li><a href="#jobcardtab" role="tab" data-toggle="tab">Job
							Card</a></li>

					<li><a href="#dmstab" role="tab" data-toggle="tab">DMS</a></li>

					<li><a href="#costingtab" role="tab" data-toggle="tab">Costing</a></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Registration Forms <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#eregtab" role="tab" data-toggle="tab">Employee
									Registration</a></li>
							<li><a href="#vregtab" role="tab" data-toggle="tab">Vendor
									Registration</a></li>
							<li><a href="#cregtab" role="tab" data-toggle="tab">Customer
									Registration</a></li>
						</ul></li>

				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="dashboardtab">Dashboard
						comes here.......</div>
					<div class="tab-pane" id="jobcardtab">job card application</div>
					<div class="tab-pane" id="dmstab">Document Management System</div>
					<div class="tab-pane" id="costingtab">Costing Application.</div>

					<div class="tab-pane" id="eregtab">Employee Registration</div>

					<div class="tab-pane" id="vregtab">Vendor Registration</div>
					<div class="tab-pane" id="cregtab">Customer Registration</div>

				</div>
			</jstl:if>
			<jstl:if test="${empty role}">
				<p>You're not logged in!</p>
			</jstl:if>


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