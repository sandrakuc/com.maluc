package com.maluc.user;

import java.sql.SQLException;

public interface UserRepo {
    void save(User user) throws SQLException;
    User getByLogin(String login);
    void deleteUserByLogin(String login) throws SQLException;
}
