<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html>
<body>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var url = window.location.href;
		var lielement;
	    var res = url.split("/");

		for (i = 0; i < res.length; i++) { 
		    if(res[i].endsWith(".jsp")){
	    	    lielement=res[i].substring(0,res[i].indexOf(".jsp"))
	    	}
		}

		if(lielement=="ereg" || lielement=="vreg" || lielement=="creg"){
			lielement ="reg";
		}
		$("#"+lielement+"li").addClass('active').siblings().removeClass('active');
	});
</script>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../../../${pageContext.request.contextPath}/css/template.css">

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

				<ul>
					<li id="dashboardli"><a
						href="${pageContext.request.contextPath}/dashboard.jsp">Dashboard</a></li>

					<li id="jobcardli"><a
						href="${pageContext.request.contextPath}/jobcard.jsp">Job Card</a></li>

					<li id="dmsli"><a
						href="${pageContext.request.contextPath}/dms.jsp">DMS</a></li>

					<li id="costingli"><a
						href="${pageContext.request.contextPath}/costing.jsp">Costing</a></li>

					<li id="regli" class="dropdownmenu"><a class="dropbtn"
						href="#">Registration Forms</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath}/ereg.jsp">Employee
								Registration</a> <a
								href="${pageContext.request.contextPath}/vreg.jsp">Vendor
								Registration</a> <a
								href="${pageContext.request.contextPath}/creg.jsp">Customer
								Registration</a>
						</div></li>

				</ul>

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