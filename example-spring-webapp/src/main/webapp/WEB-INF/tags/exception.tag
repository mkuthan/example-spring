<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>

<jsp:directive.attribute name="error_title" required="true"/>
<jsp:directive.attribute name="error_description" required="true"/>

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
    		<a href="#details" role="button" class="btn" data-toggle="modal"><spring:message code="exception_details" /></a>
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
    		<p>
				<c:out value="${exception.localizedMessage}" />
			</p>
			<p>
				<spring:message var="stacktrace" code="exception_stacktrace" />
			</p>
    		<c:forEach items="${exception.stackTrace}" var="trace">
        		<c:out value="${trace}" />
            	<br>
       		</c:forEach>
  		</div>
	</div>
	</jsp:body>
</example:layout>