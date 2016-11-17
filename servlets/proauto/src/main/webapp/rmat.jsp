<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<t:layout>
	<jsp:attribute name="header">
<script>
function ValidateForm(form){
	return true;
}

var availableTags = [
    "ActionScript",
    "AppleScript",
    "Asp",
    "BASIC",
    "C",
    "C++",
    "Clojure",
    "COBOL",
    "ColdFusion",
    "Erlang",
    "Fortran",
    "Groovy",
    "Haskell",
    "Java",
    "JavaScript",
    "Lisp",
    "Perl",
    "PHP",
    "Python",
    "Ruby",
    "Scala",
    "Scheme"
  ];


$(document).ready(function() {
	$( ".autocompletecv" ).autocomplete({
		//source: '${pageContext.request.contextPath}/get_provider_list.jsp'
		source: availableTags
	});
});

</script>
    </jsp:attribute>

	<jsp:body>
<div class="container">
	
		<br>
			<jstl:if test="${pageContext.request.userPrincipal.name == null}">
						<div class="col-md-12 col-sm-12 col-xs-12">
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
	 	  		</div>
			</jstl:if>
			<jstl:if test="${pageContext.request.userPrincipal.name != null}">
		    <div class="pageHeadings"> Raw Material</div>
		    <br>
					
  	  				<form role="form" action="rmat" method="post">  
		    <input type="hidden" name="regType" value="rmat">
			<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
			   <br>
			   <div class="row">
			     	  	<div class="col-sm-10 col-md-10">
			   
    						  <label class="radio-inline"><input type="radio" name="matradiogroup">Customer</label>
    					  	  <label class="radio-inline"><input type="radio" name="matradiogroup">Vendor</label>
    					</div>
  	  		   </div>
  	  		   <br>
  	  		    <div class="row">
  	  		    	<div class="container">
	  	  		   		<div class="col-sm-4 col-md-4">
			      			<label for="selectcv">Select Cust/Vend:</label>
			    		</div>
			   		 	<div class="col-sm-6 col-md-6">
			      			    <input class="form-control autocompletecv" placeholder="Enter Customer/Vendor" />
			    	 	</div>
		    	 	</div>
  	  		   </div>
  	  		   <br>
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="noofBars">No of bars:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="noofBars"
									name="noofBars">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  
  	  		   <div class="row">
  	  		   		<div class="col-sm-10 col-md-10">
		      			<label>Dimensions:</label>
		    		</div>
  	  		   </div>
  	  		   <br>
  	  		   
  	  		  
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="heightdim">Height:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="heightdim"
									name="heightdim">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="widthdim">Width:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="widthdim"
									name="widthdim">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		  
  	  		   <div class="row">
  	  		   		<div class="col-sm-4 col-md-4">
		      			<label for="lengthdim">Length:</label>
		    		</div>
		   		 	<div class="col-sm-6 col-md-6">
		      			<input type="text" class="form-control" id="lengthdim"
									name="lengthdim">
		    	 	</div>
  	  		   </div>
  	  		  <br>
  	  		   <div class="row">
	   		 		<div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4">
						<input type=submit value="Submit">
	    	 		</div>
	  			</div>
	  			<br>
  	  		</div>
		</form>	
  	  		
  	  			
		   </jstl:if>
		
</div>
</jsp:body>
</t:layout>

