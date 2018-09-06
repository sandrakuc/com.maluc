package com.maluc.user;

import java.sql.SQLException;
import java.util.List;

public interface UserRepo {
    void save(User user) throws SQLException;
    List<User> getUserList();
    User getByLogin(String login);
    void deleteUserByLogin(String login) throws SQLException;

}
