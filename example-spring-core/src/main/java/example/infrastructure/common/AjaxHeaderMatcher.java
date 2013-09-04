package example.infrastructure.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class AjaxHeaderMatcher {

	private static final String DEFAULT_HEADER_NAME = "X-Requested-With";

	private static final String DEFAULT_HEADER_VALUE = "XmlHttpRequest";

	private String headerName = DEFAULT_HEADER_NAME;

	private String headerValue = DEFAULT_HEADER_VALUE;

	public boolean isAjaxRequest(HttpServletRequest request) {
		return isAjaxRequest(request.getHeader(headerName));
	}

	public boolean isAjaxRequest(WebRequest request) {
		return isAjaxRequest(request.getHeader(headerName));
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	private boolean isAjaxRequest(String value) {
		return headerValue.equalsIgnoreCase(value);
	}

}
