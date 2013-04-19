package example.infrastructure.jpa;

import example.ddd.AbstractValueObject;
import example.ddd.DomainValueObject;

public privileged aspect ValueObjectMixin {

	declare @type : (@DomainValueObject *) : @javax.persistence.Embeddable;
	declare @type : (@DomainValueObject *) : @org.springframework.beans.factory.annotation.Configurable;

	//declare @constructor : @DomainValueObject *.new(..) : @edu.umd.cs.findbugs.annotations.SuppressWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE");
	declare @constructor : AbstractValueObject+.new(..) : @edu.umd.cs.findbugs.annotations.SuppressWarnings("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE");
	
	pointcut domainValueObject(): within(@DomainValueObject *);

	pointcut setter() : execution(public void *.set*(..));

	declare error : domainValueObject() && setter() : "Value Object cannot be mutable";

}
