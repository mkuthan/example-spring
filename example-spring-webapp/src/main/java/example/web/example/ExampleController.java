package example.web.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import example.domain.example.ExampleEntity;
import example.domain.example.ExampleRepository;
import example.web.RequestMappings;

@Controller
@RequestMapping(RequestMappings.EXAMPLES)
public class ExampleController {

	private ExampleRepository exampleRepository;

	@Autowired
	public ExampleController(ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String examples() {
		return RequestMappings.EXAMPLES;
	}
	
	@RequestMapping(value = RequestMappings.EXAMPLES_REST, method = RequestMethod.GET)
	@ResponseBody
	public Page<ExampleEntity> list(Pageable page) {
		return exampleRepository.findAll(page);
	}

	@RequestMapping(value = RequestMappings.EXAMPLES_UNCAUGHT_EXCEPTION, method = RequestMethod.GET)
	public String throwRuntimeException() {
		throw new RuntimeException("Sample error");
	}
}
