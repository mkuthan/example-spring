package example.domain.iam.bootstrap;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import example.domain.iam.model.Group;
import example.domain.iam.model.GroupRepository;
import example.domain.iam.model.User;
import example.domain.iam.model.User.Builder;
import example.domain.iam.model.UserIdentifier;
import example.domain.iam.model.UserRepository;
import example.domain.shared.bootstrap.BootstrapEvent;
import example.domain.shared.bootstrap.BootstrapOrder;

@Component
public class UserBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	public static final UserIdentifier JACK_RYAN = new UserIdentifier("jryan");
	public static final UserIdentifier JOHN_CLARK = new UserIdentifier("jclark");

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!usersExist()) {
			Group ciaIntelligence = groupRepository.findByName(GroupBootstrapListener.CIA_INTELLIGENCE);
			Group ciaOperations = groupRepository.findByName(GroupBootstrapListener.CIA_OPERATIONS);

			Builder builder = new Builder();

			User jack = builder.withIdentifier(JACK_RYAN).withFirstname("Jack").withLastname("Ryan")
					.withEmail("jack.ryan@cia.gov").withGroups(ciaIntelligence).build();
			User john = builder.withIdentifier(JOHN_CLARK).withFirstname("John").withLastname("Clark")
					.withEmail("john.clark@cia.gov").withGroups(ciaOperations).build();

			userRepository.save(newArrayList(jack, john));
			userRepository.flush();
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.IAM_USERS;
	}

	public boolean usersExist() {
		return userRepository.count() > 0;
	}

}
