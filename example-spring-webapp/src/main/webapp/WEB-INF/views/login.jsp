<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="example" tagdir="/WEB-INF/tags"%>
<example:layout>

	<jsp:attribute name="title">
        <spring:message code="security_login_title" />
    </jsp:attribute>

	<jsp:body>    	    	    	

		<spring:message code="security_login_form_username_message" var="usernameMessage" />
    	<spring:message code="security_login_form_password_message" var="passwordMessage" />

    	<form action="${pageContext.request.contextPath}/resources/j_spring_security_check" method="POST"
			class="form-horizontal">
	    	<fieldset>
	    		<legend>
	        		<spring:message code="security_login_form_legend" />
	    		</legend>
	    		
	    		<c:if test="${not empty param.login_error}">
			    	<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><spring:message code="security_login_unsuccessful" /></strong>
						<p> 
							<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</p>
					</div>
	    		</c:if>
	    		
	    		<div class="control-group">
	    			<label class="control-label" for="j_username">
	    				<spring:message code="security_login_form_username_label" />
	    			</label>
	    			<div class="controls">
	      				<input id="j_username" name="j_username" type="text" placeholder="${usernameMessage}" autofocus>
	    			</div>
	  			</div>
	  			<div class="control-group">
	    			<label class="control-label" for="j_password">
	    				<spring:message code="security_login_form_password_label" />
	    			</label>
	    			<div class="controls">
	      				<input id="j_password" name="j_password" type="password" placeholder="${passwordMessage}">
	    			</div>
	  			</div>
	  			<div class="control-group">
	    			<div class="controls">
	     	 			<button type="submit" class="btn btn-primary">
	     	 				<spring:message code="security_login_form_button" />
	     	 			</button>
	     	 		</div>
	     	 	</div>
    		</fieldset>
    	</form>
    </jsp:body>
</example:layout>