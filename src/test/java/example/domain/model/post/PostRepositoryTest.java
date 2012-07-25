package example.domain.model.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.DomainConfiguration;
import example.infrastructure.hibernate.HibernateConfiguration;

@Test(groups = { TestGroups.DB })
@ContextConfiguration(classes = { DomainConfiguration.class, HibernateConfiguration.class })
public class PostRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    Post postRepository;

    @BeforeMethod
    public void setUp() {
    }

}
