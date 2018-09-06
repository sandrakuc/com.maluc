package com.maluc.user;

import com.maluc.services.UserRestServices;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;

import javax.ws.rs.core.Application;

import static com.maluc.user.UserStatementsProvider.*;

public class CheckUserIT extends JerseyTest {
    @Override
    public Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(UserRestServices.class);
    }

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
