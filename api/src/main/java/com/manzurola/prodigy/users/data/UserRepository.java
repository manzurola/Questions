package com.manzurola.prodigy.users.data;

public interface UserRepository {

    void add(User user) throws Exception;

    User get(String id) throws Exception;
}
