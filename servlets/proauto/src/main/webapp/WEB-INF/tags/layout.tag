<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ attribute name="header" fragment="true"%>
<%@ attribute name="footer" fragment="true"%>
<html>
<head>




<script	type="text/javascript" src="../../../${pageContext.request.contextPath}/scripts/jquery-1.12.4.js"></script>
	
<script type="text/javascript" src="../../../${pageContext.request.contextPath}/scripts/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="../../../${pageContext.request.contextPath}/css/jquery-ui.css"/>

<script type="text/javascript" src="../../../${pageContext.request.contextPath}/scripts/jquery.validate.js"></script>

<script type="text/javascript" src="../../../${pageContext.request.contextPath}/scripts/additional-methods.js"></script>

<link rel="stylesheet" type="text/css"	href="../../../${pageContext.request.contextPath}/css/bootstrap.css">

<link rel="stylesheet" type="text/css"	href="../../../${pageContext.request.contextPath}/css/template.css">

<script type="text/javascript" src="../../../${pageContext.request.contextPath}/scripts/bootstrap.js"></script>

<script type="text/javascript" src="../../../${pageContext.request.contextPath}/scripts/bootstrap-dialog.js"></script>

<script>


	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
	$(document).ready(function() {
		var url = window.location.href;
		var lielement;
	    var res = url.split("/");

	    lielement=res[res.length-1];
	    
		if(lielement=="ereg" || lielement=="vreg" || lielement=="creg" || lielement=="rmat" || lielement=="mreg"){
			lielement ="reg";
		}
		if(lielement=="searchjobcard" || lielement=="createjobcard"){
			lielement ="jobcard";
		}
		//$("#"+lielement+"li").addClass('active').siblings().removeClass('active');
	});
</script>

<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1">
</head>

<body onload="customLoad()">
	<!-- Header -->
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    
	<div class="container">
			<!-- <div class="col-md-12 col-sm-12 col-xs-12">
	 	 		<div class="row">
 	  		   		<div class="col-sm-2 col-md-2">
	      			<img class="logo-customer" alt="" src="../../../${pageContext.request.contextPath}/images/customerlogo.png">
	    		</div>
	    		<div class="col-sm-8 col-md-8">
	      				<div class="customerHeaderContent">
							<h3>Ennem Excel Engineering(P) Limited</h3>
						</div>
	    		</div>
	   		 	<div class="col-sm-2 col-md-2">
	      			<img class="logo-proauto" alt="" src="../../../${pageContext.request.contextPath}/images/proautologo.png">
	    	 	</div>
 	  		</div> -->
			<jstl:if test="${pageContext.request.userPrincipal.name == null}">
						<div class="straightLine"></div>
						<!-- <div class="col-md-12 col-sm-12 col-xs-12">
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
	 	  		</div> -->
			</jstl:if>
			
			<jstl:if test="${pageContext.request.userPrincipal.name != null}">
				<!-- Nav tabs -->

				<ul>
					<li id="dashboardli"><a
						href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>

					<li id="jobcardli" class="dropdownmenu"><a class="dropbtn">Job Card</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath}/searchjobcard">Search Jobcard</a>
							<a href="${pageContext.request.contextPath}/showjobcard">Create Jobcard</a>
						</div>
					</li>
					
					<li id="poli" class="dropdownmenu"><a class="dropbtn">Purchase Orders</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath}/searchpo">Search PO</a>
							<a href="${pageContext.request.contextPath}/showpo">Create PO</a>
						</div>
					</li>

					<li id="inventoryli"><a
						href="${pageContext.request.contextPath}/inventory">Inventory</a></li>

					<li id="inventoryli" class="dropdownmenu"><a class="dropbtn">Inventory</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath}/addtoinv">Add to Inventory</a>
							<a href="${pageContext.request.contextPath}/searchinv">Search Inventory</a>
						</div>
					</li>
											
					<li id="machineusageli"><a
						href="${pageContext.request.contextPath}/machineusage">Machine Usage</a></li>
						
					<li id="regli" class="dropdownmenu"><a class="dropbtn">Masters</a>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath}/ereg">Employee
								Master</a>
								 <a
								href="${pageContext.request.contextPath}/creg">Customer
								Master</a>
							
								<a
								href="${pageContext.request.contextPath}/mreg">Machines
								</a>
								<a
								href="${pageContext.request.contextPath}/createjobop">Job Operations
								</a>
						</div></li>

				</ul>

			</jstl:if>

			<jstl:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<jstl:if test="${pageContext.request.userPrincipal.name != null}">
				<div class="col-md-12 col-sm-12 col-xs-12">
		 	 		<div class="row">
	 	  		   		<div class="col-sm-2 col-md-2">
		    			</div>
		    			<div class="col-sm-7 col-md-7">
		      				
		    			</div>
		   		 		<div class="col-sm-3 col-md-3">
							<p>
								Welcome : ${pageContext.request.userPrincipal.name} | <a
										href="javascript:formSubmit()"> Logout</a>
							</p>
		    	 		</div>
	 	  			</div>
	 	  		</div>
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