package example.infrastructure.jpa;

import example.ddd.AbstractEntity;
import example.ddd.DomainEntity;

public privileged aspect EntityMixin {

	declare @type : @DomainEntity * : @javax.persistence.Entity;
	declare @type : @DomainEntity * : @org.springframework.beans.factory.annotation.Configurable;

	//declare @constructor : @DomainEntity *.new(..) : @edu.umd.cs.findbugs.annotations.SuppressWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE");
	declare @constructor : AbstractEntity+.new(..) : @edu.umd.cs.findbugs.annotations.SuppressWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE");
}
