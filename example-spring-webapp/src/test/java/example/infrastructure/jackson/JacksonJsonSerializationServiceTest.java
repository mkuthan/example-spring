package example.infrastructure.jackson;

import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.example.ExampleValueObject;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-jackson.xml")
@Test(groups = TestGroups.INTEGRATION)
@ActiveProfiles("test")
public class JacksonJsonSerializationServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private JacksonJsonSerializationService service;

	@DataProvider
	static final Object[][] objects() {
		return new Object[][] { { new ExampleValueObject("FieldA", "FieldB") },
				{ new ExampleValueObject("FieldA", "FieldB", "FieldC") } };
	}

	@Test(dataProvider = "objects")
	public void shouldSerializeAndDeserialize(Object object) {
		assertThat(service.fromJson(service.toJson(object), object.getClass().getName())).isEqualTo(object);
	}
}
