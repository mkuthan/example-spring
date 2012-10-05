<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<jsp:directive.attribute name="title" fragment="true" required="false" description="Page title" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><jsp:invoke fragment="title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<c:import url="/WEB-INF/tags/layout/fragments/navbar.jsp" />
			</div>
		</div>
	</div>

	<div class="container">
		<jsp:doBody />

		<hr>

		<footer>
			<c:import url="/WEB-INF/tags/layout/fragments/footer.jsp" />
		</footer>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/demo.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>
</body>
</html>
