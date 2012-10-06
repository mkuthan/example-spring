<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>
<example:exception>
	<jsp:attribute name="error_title">
		<spring:message code="error_dataaccessfailure_title" />
	</jsp:attribute>
	<jsp:attribute name="error_description">
		<spring:message code="error_dataaccessfailure_description" />
	</jsp:attribute>
</example:exception>