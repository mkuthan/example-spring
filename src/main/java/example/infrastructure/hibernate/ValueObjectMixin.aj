package example.infrastructure.hibernate;

import example.domain.shared.DomainValueObject;

public privileged aspect ValueObjectMixin {

    declare @type : (@DomainValueObject *) : @javax.persistence.Embeddable;
    declare @type : (@DomainValueObject *) : @org.springframework.beans.factory.annotation.Configurable;

}
