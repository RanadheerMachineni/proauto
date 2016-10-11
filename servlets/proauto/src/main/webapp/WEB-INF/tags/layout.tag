<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
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