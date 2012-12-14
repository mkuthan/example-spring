package example.infrastructure.jpa;

import example.domain.shared.ddd.DomainEntity;

public privileged aspect EntityMixin {

	declare @type : (@DomainEntity *) : @javax.persistence.Entity;
	declare @type : (@DomainEntity *) : @org.springframework.beans.factory.annotation.Configurable;

}
