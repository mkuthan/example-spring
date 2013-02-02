<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>

<example:layout>

	<jsp:attribute name="title">
        <spring:message code="security_logged_out_title" />
    </jsp:attribute>

	<jsp:body>   	    	    	
		<div class="hero-unit">
			<h2><spring:message code="security_logged_out_message" /></h2>

			<p>Thank you for using <em><spring:message code="application_name" /></em>.</p>
           
           	<p>
           		<a href="${pageContext.request.contextPath}" class="btn btn-primary">
           			<spring:message code="security_logged_out_again" />
           		</a>
           	</p>
       </div>
		
    </jsp:body>
</example:layout>