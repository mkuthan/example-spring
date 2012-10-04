package example.infrastructure.hibernate;

import example.domain.shared.DomainEntity;

public aspect EntityMixin {

    private interface Entity {
    };

    declare parents : (@DomainEntity *) implements Entity;

    // declare @type : Entity1 : @org.springframework.beans.factory.annotation.Configurable;

    // declare @type : VersionedEntity+ : @javax.persistence.Entity;
    // declare @type : VersionedEntity+ : @org.springframework.beans.factory.annotation.Configurable;
    
}
