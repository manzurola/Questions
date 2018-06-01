package com.manzurola.prodigy.users.data;

import com.manzurola.prodigy.common.Guid;

import java.util.Objects;

public class User {

    private final Guid<User> id;
    private final String email;

    public User(Guid<User> id, String email) {
        this.id = id;
        this.email = email;
    }

    public Guid<User> getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
