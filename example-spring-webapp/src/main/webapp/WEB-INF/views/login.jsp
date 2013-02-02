<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>
<example:layout>
	<jsp:attribute name="title">
        <spring:message code="Login" />
    </jsp:attribute>
	<jsp:body>
        <!-- Main hero unit for a primary marketing message or call to action -->
		<div class="hero-unit">
			<h1>
				<spring:message code="application.name" />
			</h1>
			<p>This is a template for a simple marketing or informational website. It includes a large callout called the
				hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
			<p>
				<a class="btn btn-primary btn-large">Learn more &raquo;</a>
			</p>
		</div>        
    </jsp:body>
</example:layout>