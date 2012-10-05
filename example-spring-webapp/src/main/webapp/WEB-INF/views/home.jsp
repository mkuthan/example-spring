<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<layout:page>
	<jsp:attribute name="title">
        <spring:message code="application.name" />
    </jsp:attribute>
	<jsp:body>
        <div class="hero-unit">
            <h1>
				<spring:message code="application.name" />
			</h1>
        </div>
    </jsp:body>
</layout:page>