package com.manzurola.prodigy.users.data;

import com.manzurola.prodigy.common.EntityFactory;
import org.apache.commons.validator.routines.EmailValidator;

public class UserFactoryImpl extends EntityFactory implements UserFactory {

    @Override
    public User create(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new RuntimeException("Invalid email " + email);
        }
        return new User(nextId(), email);
    }
}
