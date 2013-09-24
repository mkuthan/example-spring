package example.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.google.common.io.Files;

@Controller
public class ViewController {

	@RequestMapping(value = "/views/**", method = RequestMethod.GET)
	public String handle(HttpServletRequest request) {
		String url = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		String view = new AntPathMatcher().extractPathWithinPattern(url, request.getServletPath());
		return Files.getNameWithoutExtension(view);
	}

}
