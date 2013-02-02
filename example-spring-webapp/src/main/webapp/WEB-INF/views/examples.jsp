<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>
<example:layout>
	<jsp:attribute name="title">Example</jsp:attribute>
	<jsp:body>
		<p>
			<spring:url value="/examples/uncaughtException" var="uncaughtException" />
			<a href="${uncaughtException}">Uncaught Exception</a>
		</p>
    </jsp:body>
</example:layout>