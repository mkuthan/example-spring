package example.infrastructure.jpa.types;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.joda.money.Money;
import org.joda.time.DateTime;

import example.domain.shared.json.JsonHolder;

@MappedSuperclass
@TypeDefs({ @TypeDef(defaultForType = Money.class, typeClass = MoneyType.class),
		@TypeDef(defaultForType = DateTime.class, typeClass = DateTimeType.class),
		@TypeDef(defaultForType = JsonHolder.class, typeClass = JsonHolderType.class) })
public class CustomTypes {
}
