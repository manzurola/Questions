package com.manzurola.prodigy.users;

import com.manzurola.prodigy.common.Guid;
import com.manzurola.prodigy.questions.Application;
import com.manzurola.prodigy.users.data.User;
import com.manzurola.prodigy.users.data.UserRepository;
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

    @Test
    public void add() {
        User expected = new User(Guid.random(), "prodigy@prodigy.com");
        User actual = repository.add(expected.getId(), expected.getEmail());

        Assert.assertEquals(expected, actual);
    }
}
