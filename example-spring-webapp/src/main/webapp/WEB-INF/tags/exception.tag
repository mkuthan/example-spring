<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>

<jsp:directive.attribute name="error_title" required="true" description="Error title" />
<jsp:directive.attribute name="error_description" required="true" description="Error description" />

<jsp:useBean id="date" class="java.util.Date" />

<example:layout>
	<jsp:attribute name="title">
		${error_title}
	</jsp:attribute>

	<jsp:body>
		<div class="hero-unit">
			<h1>
				${error_title}
			</h1>
			<p>
	      		${error_description}
	    	</p>
	    		    	
	    	<c:if test="${not empty exception}">
	    	<p>
	    		<a href="#details" role="button" class="btn btn-primary" data-toggle="modal"><spring:message code="exception_details" /></a>
	    	</p>
	    	</c:if>
		</div>
	    
	    <div id="details" class="modal hide fade">
	  		<div class="modal-header">
	    		<button type="button" class="close" data-dismiss="modal">&times;</button>
	    		<h3>
					<spring:message code="exception_details" />
				</h3>
	  		</div>
	  		<div class="modal-body">
	    		<p class="lead">
					<c:out value="${exception.message}" />
				</p>
								
				<p><em><spring:message code="exception_date" /></em> <code><fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long" /></code></p>
	            <p><em><spring:message code="exception_request_uri" /></em> <code>${requestScope['javax.servlet.error.request_uri']}</code></p>
	            <p><em><spring:message code="exception_user_agent" /></em> <code>${header['user-agent']}</code></p>

	        	<p><em><spring:message code="exception_status_code" /></em> <code>${requestScope['javax.servlet.error.status_code']}</code></p>
	            <p><em><spring:message code="exception_type" /></em> <code>${requestScope['javax.servlet.error.exception_type']}</code></p>
				
				<p>
					<em><spring:message code="exception_stacktrace" /></em>
				</p>
				<pre>
		    		<c:forEach items="${exception.stackTrace}" var="trace">
<c:out value="${trace}" />
		       		</c:forEach>
	       		</pre>
	  		</div>
		</div>
	</jsp:body>
</example:layout>