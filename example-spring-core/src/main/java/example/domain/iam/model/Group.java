package example.domain.iam.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.domain.shared.ddd.AbstractAggregateEntity;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group extends AbstractAggregateEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Group parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Group> subGroups = new HashSet<>();

	protected Group() {
	}

	protected Group(Builder builder) {
		this.name = checkNotNull(builder.name);

		if (builder.subGroups != null) {
			for (Group subGroup : builder.subGroups) {
				addSubGroup(subGroup);
			}
		}
	}

	public String getName() {
		return name;
	}

	public Group getParent() {
		return parent;
	}

	public Set<Group> getSubGroups() {
		return Collections.unmodifiableSet(subGroups);
	}

	public void addSubGroup(Group subGroup) {
		checkNotNull(subGroup);

		subGroup.parent = this;
		this.subGroups.add(subGroup);
	}

	public boolean isRoot() {
		return parent == null;
	}

	public Set<Group> effectiveGroups() {
		Set<Group> effectiveGroups = newHashSet(this);
		if (!isRoot()) {
			effectiveGroups.addAll(parent.effectiveGroups());
		}
		return effectiveGroups;
	}

	public static class Builder {

		private String name;

		private Set<Group> subGroups;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withSubGroups(Group... subGroups) {
			return withSubGroups(newHashSet(subGroups));
		}

		public Builder withSubGroups(Set<Group> subGroups) {
			this.subGroups = subGroups;
			return this;
		}

		public Group build() {
			return new Group(this);
		}
	}

}
