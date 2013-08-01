<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>
<example:layout>
	<jsp:attribute name="title">Home</jsp:attribute>
    
    <jsp:attribute name="resources">
        <script src="${pageContext.request.contextPath}/webjars/angularjs/1.1.5/angular.min.js"></script>   
        <script src="${pageContext.request.contextPath}/webjars/angular-ui/0.4.0/angular-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/0.4.0/ui-bootstrap.min.js"></script>   
    
        <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/services.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/filters.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/directives.js"></script>
    </jsp:attribute>
    
	<jsp:body>
        <!-- Main hero unit for a primary marketing message or call to action -->
		<div class="hero-unit">
			<h1>
				<spring:message code="application_name" />
			</h1>
			<p>This is a template for a simple marketing or informational website. It includes a large callout called the
				hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
			<p>
				<a class="btn btn-primary btn-large">Learn more &raquo;</a>
			</p>
		</div>

		<div ng-controller='HelloController'>
    		<input ng-model='greeting.text'>
    		<p>{{greeting.text}}, World</p>
  		</div>
    </jsp:body>
</example:layout>