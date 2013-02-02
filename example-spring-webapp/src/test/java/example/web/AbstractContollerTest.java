package example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration("classpath:/META-INF/spring/testContext-web.xml")
@Test(groups = { TestGroups.INTEGRATION })
@ActiveProfiles("test")
@WebAppConfiguration
public abstract class AbstractContollerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeClass
	protected void initializeMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	protected ResultActions perform(RequestBuilder requestBuilder) throws Exception {
		return mockMvc.perform(requestBuilder);
	}

}
