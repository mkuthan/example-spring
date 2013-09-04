package example.infrastructure.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Component;

import example.infrastructure.common.AjaxHeaderMatcher;

@Component
public class NonAjaxRequestMatcher implements RequestMatcher {

	private AjaxHeaderMatcher ajaxHeaderMatcher;

	@Autowired
	public NonAjaxRequestMatcher(AjaxHeaderMatcher ajaxHeaderMatcher) {
		this.ajaxHeaderMatcher = ajaxHeaderMatcher;
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		return !ajaxHeaderMatcher.isAjaxRequest(request);
	}
}
