package example.domain.model.comment;

import javax.persistence.Entity;

import example.domain.shared.AbstractAggregateEntity;

@Entity
public class Comment extends AbstractAggregateEntity<Comment> {
}
