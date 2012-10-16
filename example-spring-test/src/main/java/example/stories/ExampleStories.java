package example.stories;

import static org.jbehave.core.reporters.Format.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.stories.ExampleStories.ExampleReportBuilder;

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(parameterConverters = { ParameterConverters.EnumConverter.class,
		ParameterConverters.DateConverter.class }, storyReporterBuilder = ExampleReportBuilder.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = false, ignoreFailureInView = false)
@UsingSpring(resources = { "classpath:/META-INF/spring/jbehaveContext.xml",
		"classpath:/META-INF/spring/storiesContext.xml" })
public class ExampleStories extends InjectableEmbedder {

	private static final List<String> INCLUDES = Arrays.asList("**/*.story");
	private static final List<String> EXCLUDES = Collections.emptyList();

	@Override
	@Test
	public void run() throws Throwable {
		injectedEmbedder().runStoriesAsPaths(storyPaths());
	}

	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
				INCLUDES, EXCLUDES);
	}

	public static class ExampleReportBuilder extends StoryReporterBuilder {
		public ExampleReportBuilder() {
			withCodeLocation(CodeLocations.codeLocationFromClass(this
					.getClass()));
			withFailureTrace(true).withFailureTraceCompression(true);
			withDefaultFormats().withFormats(IDE_CONSOLE, XML, HTML);
		}
	}
}