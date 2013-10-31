package example.domain.iam.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import example.domain.iam.model.Group;
import example.domain.iam.model.Group.Builder;
import example.domain.iam.model.GroupRepository;
import example.domain.shared.bootstrap.BootstrapEvent;
import example.domain.shared.bootstrap.BootstrapOrder;

@Component
public class GroupBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	public static final String CIA = "CIA";
	public static final String CIA_INTELLIGENCE = "CIA_INTELLIGENCE";
	public static final String CIA_OPERATIONS = "CIA_OPERATIONS";

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!rolesExist()) {
			Builder builder = new Builder();

			Group cia = builder.withName(CIA).build();
			cia.addSubGroup(builder.withName(CIA_INTELLIGENCE).build());
			cia.addSubGroup(builder.withName(CIA_OPERATIONS).build());

			groupRepository.save(cia);
			groupRepository.flush();
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.IAM_GROUPS;
	}

	public boolean rolesExist() {
		return groupRepository.count() > 0;
	}

}
