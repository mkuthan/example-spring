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
			    	<label class="control-label">Please select your account provider:</label>
			        <div class="controls">
			            <label class="radio">
			                <input type="radio" name="openid_identifier" id="id_openid_google" value="https://www.google.com/accounts/o8/id"/>
			                Google - https://www.google.com/accounts/o8/id
			            </label>
						               
			            <label class="radio">
			                <input type="radio" name="openid_identifier" id="id_openid_myopenid" value="__username__.myopenid.com"/>
			                MyOpenID: <div class="input-append"><input class="username" type="text" placeholder="username"/><span class="add-on">.myopenid.com</span></div>
			            </label>
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