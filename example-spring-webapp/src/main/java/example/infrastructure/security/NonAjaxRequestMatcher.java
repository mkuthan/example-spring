package example.infrastructure.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.RequestMatcher;

public class NonAjaxRequestMatcher implements RequestMatcher {

	private static final String HEADER_NAME = "X-Requested-With";

	private static final String HEADER_VALUE = "XmlHttpRequest";

	@Override
	public boolean matches(HttpServletRequest request) {
		return !HEADER_VALUE.equalsIgnoreCase(request.getHeader(HEADER_NAME));
	}

}
