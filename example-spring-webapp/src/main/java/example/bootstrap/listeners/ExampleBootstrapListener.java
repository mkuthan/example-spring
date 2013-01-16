package example.bootstrap.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import example.bootstrap.BootstrapEvent;
import example.bootstrap.BootstrapOrder;
import example.domain.example.ExampleEntity;
import example.domain.example.ExampleRepository;
import example.domain.example.ExampleValueObject;
import example.domain.example.ExampleValueObject.Builder;

@Component
public class ExampleBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	@Autowired
	private ExampleRepository exampleRepository;

	@Override
	@Transactional
	public void onApplicationEvent(BootstrapEvent event) {
		if (!examplesExist()) {
			Builder exampleValueObjectBuilder = new ExampleValueObject.Builder().withFieldA("any value A").withFieldA(
					"any value B");

			for (int i = 0; i < 100; i++) {
				ExampleEntity exampleEntity = new ExampleEntity("Example #" + i);
				exampleEntity.setValue(exampleValueObjectBuilder.build());

				exampleRepository.save(exampleEntity);
			}
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.EXAMPLE;
	}

	private boolean examplesExist() {
		return exampleRepository.count() > 0;
	}

}
