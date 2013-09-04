package example.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import example.infrastructure.common.AjaxHeaderMatcher;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	private AjaxHeaderMatcher ajaxHeaderMatcher;

	@Autowired
	public ExceptionHandlerAdvice(AjaxHeaderMatcher ajaxHeaderMatcher) {
		this.ajaxHeaderMatcher = ajaxHeaderMatcher;
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (ajaxHeaderMatcher.isAjaxRequest(request)) {
			return new ResponseEntity<Object>(ex.getMessage(), headers, status);
		} else {
			return super.handleExceptionInternal(ex, null, headers, status, request);
		}
	}

}
