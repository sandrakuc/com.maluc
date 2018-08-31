package com.maluc.user;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static com.maluc.user.UserStatementsProvider.*;

public class UserRepositoryIT {

    public static final String NON_EXISTING_LOGIN = "another69";

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

    private void checkFoundUser(User user){
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getLogin().equals(TEST_LOGIN));
        Assert.assertTrue(user.getPassword().equals(TEST_PASSWORD));
        Assert.assertTrue(user.getEmail().equals(TEST_EMAIL));
        Assert.assertTrue(user.getName().equals(TEST_NAME));
        Assert.assertTrue(user.getSurname().equals(TEST_SURNAME));
        Assert.assertTrue(user.getPhoneNumber().equals(TEST_PHONE_NUMBER));
    }


}