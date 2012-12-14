package example.infrastructure.jpa;

import example.domain.shared.ddd.DomainValueObject;

public privileged aspect ValueObjectMixin {

	declare @type : (@DomainValueObject *) : @javax.persistence.Embeddable;
	declare @type : (@DomainValueObject *) : @org.springframework.beans.factory.annotation.Configurable;

	pointcut domainValueObject(): within(@DomainValueObject *);

	pointcut setter() : execution(public void *.set*(..));

	declare error : domainValueObject() && setter() : "Value Object cannot be mutable";

}
