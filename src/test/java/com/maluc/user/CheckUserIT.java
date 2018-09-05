package com.maluc.user;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;

import static com.maluc.user.UserStatementsProvider.*;

public class CheckUserIT extends JerseyTest {

    public void checkFoundUser(User user){
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getLogin().equals(TEST_LOGIN));
        Assert.assertTrue(user.getPassword().equals(TEST_PASSWORD));
        Assert.assertTrue(user.getEmail().equals(TEST_EMAIL));
        Assert.assertTrue(user.getName().equals(TEST_NAME));
        Assert.assertTrue(user.getSurname().equals(TEST_SURNAME));
        Assert.assertTrue(user.getPhoneNumber().equals(TEST_PHONE_NUMBER));
    }
}
