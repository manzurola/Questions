package com.manzurola.prodigy.users.data;

import com.manzurola.prodigy.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserFactory factory;

    @Test
    public void add() throws Exception {
        User expected = factory.create("prodigy@prodigy.com");
        repository.add(expected);

        Assert.assertEquals(expected, repository.get(expected.getId()));
    }
}
