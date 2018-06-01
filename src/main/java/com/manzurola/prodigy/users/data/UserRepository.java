package com.manzurola.prodigy.users.data;

import com.manzurola.prodigy.common.Guid;

public interface UserRepository {

    User add(Guid<User> id, String email);

    User get(Guid<User> id);
}
