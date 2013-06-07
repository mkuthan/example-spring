<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:directive.attribute name="title" description="Page title" />

<!DOCTYPE html>
<html lang="en" ng-app="example-spring">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8" >
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title><spring:message code="application_name" /> - ${title}</title>

	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<link href="${pageContext.request.contextPath}/webjars/bootstrap/2.3.1/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/css/example-spring.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> 
				<a class="brand" href="${pageContext.request.contextPath}">
					<spring:message code="application_name" />
				</a>
				
				<sec:authorize access="isAuthenticated()">				
					<div class="nav-collapse">
						<ul class="nav pull-right">
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<i class="icon-user icon-white"></i>
									<c:out value="${loggedUserDisplayName}" />
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/resources/j_spring_security_logout">
											<i class="icon-off"></i>
											<spring:message code="security_logout" />
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>				
				</sec:authorize>
			</div>
		</div>
	</div>

	<div class="container">
		<jsp:doBody />
	</div>
	
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="span4">
					<spring:message code="application_copyright" htmlEscape="false"></spring:message>
				</div>
				<div class="span4">
					<i class="icon-envelope"></i> 
					marcin.kuthan@gmail.com
				</div>
				<div class="span4">
					<i class="icon-info-sign"></i> 
					Version: <spring:message code="application_version" />, Build: <spring:message code="application_buildDdate" />
				</div>
			</div>		
		</div>				
	</footer>
	

	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.1.5/angular.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/2.3.1/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
</body>

</html>
