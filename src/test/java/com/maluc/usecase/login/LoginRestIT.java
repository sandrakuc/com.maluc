package com.maluc.usecase.login;

import com.maluc.user.CheckUserIT;
import com.maluc.user.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.maluc.user.UserStatementsProvider.*;

public class LoginRestIT extends CheckUserIT {

    @Before
    public void addUserToDataBase() throws SQLException {
        createUser();
    }

    @After
    public void deleteUserFromDataBase() throws SQLException{
        deleteUser();
    }

    @Test
    public void shouldLogUserToSystem(){
        WebTarget target = target("/")
                .path(TEST_LOGIN)
                .queryParam("password", TEST_PASSWORD);
        Response response = target.request().get();
        Assert.assertTrue(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
        User user = target.request().get(User.class);
        checkFoundUser(user);
    }

    @Test
    public void shouldGetErrorDueToLogByNonExistingLogin(){
        WebTarget target = target("/")
                .path(NON_EXISTING_LOGIN)
                .queryParam("password", TEST_PASSWORD);
        Response response = target.request().get();
        Assert.assertFalse(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
    }

    @Test
    public void shouldGetErrorDueToLogByInvalidPassword(){
        WebTarget target = target("/")
                .path(TEST_LOGIN)
                .queryParam("password", INVALID_PASSWORD);
        Response response = target.request().get();
        Assert.assertFalse(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
    }
}
