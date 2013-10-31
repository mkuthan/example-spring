package example.infrastructure.jpa.iam;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import example.EntitiesAssert;
import example.EntityAssert;
import example.domain.iam.model.Group;
import example.domain.iam.model.Group.Builder;
import example.domain.iam.model.GroupRepository;
import example.infrastructure.jpa.AbstractJpaRepositoryTest;

public class JpaRoleRepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private GroupRepository groupRepository;

	public void shouldSave() {
		// given
		Group group = createBuilder().build();

		// when
		Group savedGroup = groupRepository.save(group);
		flushAndClear();

		savedGroup = groupRepository.findOne(savedGroup.getEntityId());

		// then
		EntityAssert.assertThat(savedGroup).isManaged();

		assertThat(savedGroup.getName()).isEqualTo(group.getName());
		EntitiesAssert.assertThat(savedGroup.getSubGroups()).containsOnly(group.getSubGroups());
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveDuplicates() {
		// given
		String name = "duplicate";

		Group group1 = createBuilder().withName(name).build();
		Group group2 = createBuilder().withName(name).build();

		// when
		groupRepository.save(newArrayList(group1, group2));
	}

	@Test
	public void shouldFindByName() {
		// given
		String name = "name";

		Group expectedGroup = saveFlushAndClear(createBuilder().withName(name).build());

		// when
		Group group = groupRepository.findByName(name);

		// then
		EntityAssert.assertThat(group).isEqualTo(expectedGroup);
	}

	public Builder createBuilder() {
		return new Builder().withName("any name").withSubGroups(new Builder().withName("sub group 1").build(),
				new Builder().withName("sub group 2").build());
	}

}
