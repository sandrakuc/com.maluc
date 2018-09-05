package com.maluc.user;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static com.maluc.user.UserStatementsProvider.*;

public class UserRepositoryIT extends CheckUserIT{

    @Before
    public void addUserToDataBase() throws SQLException {
        createUser();
    }

    @After
    public void deleteUserFromDataBase() throws SQLException{
        deleteUser();
    }

    @Test
    public void shouldGetUserByLogin() throws SQLException {
        UserRepo userRepo = new UserRepoImpl();
        User foundUser = userRepo.getByLogin(TEST_LOGIN);
        checkFoundUser(foundUser);
    }

    @Test
    public void shouldGetNullDueToGettingNonExistingUser() throws SQLException{
        UserRepo userRepo = new UserRepoImpl();
        User nonExistingUser = userRepo.getByLogin(NON_EXISTING_LOGIN);
        Assert.assertNull(nonExistingUser);
    }

}