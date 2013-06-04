package example.sample.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import example.sample.domain.SampleEntity;
import example.sample.domain.SampleRepository;
import example.sample.domain.SampleValueObject;
import example.shared.bootstrap.BootstrapEvent;
import example.shared.bootstrap.BootstrapOrder;

@Component
@Profile("local")
public class SampleBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	@Autowired
	private SampleRepository sampleRepository;

	@Override
	@Transactional
	public void onApplicationEvent(BootstrapEvent event) {
		if (!examplesExist()) {
			SampleEntity.Builder exampleEntityBuilder = new SampleEntity.Builder();
			SampleValueObject.Builder exampleValueObjectBuilder = new SampleValueObject.Builder().withFieldA(
					"any value A").withFieldB("any value B");

			for (int i = 0; i < 100; i++) {
				SampleEntity sampleEntity = exampleEntityBuilder.withName("Example #" + i)
						.withEmbeddedValueObject(exampleValueObjectBuilder.build()).build();
				sampleRepository.save(sampleEntity);
			}
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.EXAMPLE;
	}

	private boolean examplesExist() {
		return sampleRepository.count() > 0;
	}

}
