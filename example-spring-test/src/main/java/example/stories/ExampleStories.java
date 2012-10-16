package example.stories;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(parameterConverters = { ParameterConverters.EnumConverter.class,
		ParameterConverters.DateConverter.class })
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = false, ignoreFailureInView = false)
@UsingSpring(resources = { "classpath:/META-INF/spring/jbehaveContext.xml",
"classpath:/META-INF/spring/storiesContext.xml" })
public class ExampleStories extends InjectableEmbedder {

	@Override
	@Test
	public void run() throws Throwable {
		injectedEmbedder().runStoriesAsPaths(storyPaths());
	}

	protected List<String> storyPaths() {
		return new StoryFinder().findPaths("src/main/resources",
				Arrays.asList("**/*.story"), Arrays.asList(""));
	}
}