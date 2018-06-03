package com.manzurola.prodigy.users.data;

import com.manzurola.prodigy.common.Guid;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlUserRepository implements UserRepository {

    private static final String TABLE_NAME = "users";
    private final JdbcTemplate jdbcTemplate;
    private final Mapper mapper = new Mapper();


    public MysqlUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(Guid<User> id, String email) {
        String sql = "insert into " + TABLE_NAME + " values (?,?)";
        Object[] args = List.of(id.getId(), email).toArray();
        jdbcTemplate.update(sql, args);
        return get(id);
    }

    @Override
    public User get(Guid<User> id) {
        String sql = "select * from " + TABLE_NAME + " where id = (?)";
        Object[] args = List.of(id.getId()).toArray();
        return jdbcTemplate.queryForObject(sql, args, mapper);
    }

    private static final class Mapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(Guid.of(resultSet.getString("id")), resultSet.getString("email"));
        }
    }
}
