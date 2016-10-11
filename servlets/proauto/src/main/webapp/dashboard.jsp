<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout>
<jsp:body>
<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
	  <li class="active"><a href="#Dashboardtab" role="tab"
						data-toggle="tab">Dashboard</a></li>
	  <li><a href="#jobcardtab" role="tab" data-toggle="tab">Job Card</a></li>
	  <li><a href="#dmstab" role="tab" data-toggle="tab">DMS</a></li>
	  <li><a href="#costingtab" role="tab" data-toggle="tab">Costing</a></li>
	
	  <li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Registration Forms <b
							class="caret"></b></a>
	            <ul class="dropdown-menu">
	                <li><a href="#eregtab" role="tab"
								data-toggle="tab">Employee Registration</a></li>
	                <li><a href="#vregtab" role="tab"
								data-toggle="tab">Vendor Registration</a></li>
	                <li><a href="#cregtab" role="tab" data-toggle="tab">Customer Registration</a></li>
	            </ul>
	  </li>  
	  
	</ul>
	
	<!-- Tab panes -->
	<div class="tab-content">
	  <div class="tab-pane active" id="Dashboardtab">Dashboard comes here.......</div>
	  <div class="tab-pane" id="jobcardtab">job card application
	
	  </div>
	  <div class="tab-pane" id="dmstab">Document Management System</div>
	  <div class="tab-pane" id="costingtab">Costing Application.</div>
	
	  <div class="tab-pane" id="eregtab">Employee Registration </div>
	  <div class="tab-pane" id="vregtab">Vendor Registration </div>
	  <div class="tab-pane" id="cregtab">Customer Registration</div>  
	  
	</div>

</div>
</jsp:body>
</t:layout>

