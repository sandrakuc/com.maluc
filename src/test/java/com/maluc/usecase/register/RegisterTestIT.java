package com.maluc.usecase.register;

import com.maluc.user.CheckUserIT;
import com.maluc.user.User;
import com.maluc.user.UserRepo;
import com.maluc.user.UserRepoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.maluc.user.UserStatementsProvider.*;

public class RegisterTestIT extends CheckUserIT {

    public static final String NEW_LOGIN = "nowak5";
    public static final String NEW_EMAIL = "jan_nowak@o2.pl";
    public static final String NEW_PASSWORD = "qwerty123";
    public static final String NEW_NAME = "Jan";
    public static final String NEW_SURNAME = "Nowak";
    public static final String NEW_PHONE_NUMBER = "888999000";

    @Before
    public void addUserToDataBase() throws SQLException {
        createUser();
    }

    @After
    public void deleteUserFromDataBase() throws SQLException{
        deleteUser();
        deleteUser(NEW_LOGIN);
    }

    @Test
    public void shouldSuccessfullyRegisterNewUser(){
        WebTarget target = target("/register")
                .queryParam("login", NEW_LOGIN)
                .queryParam("email", NEW_EMAIL)
                .queryParam("password", NEW_PASSWORD)
                .queryParam("confirmPassword", NEW_PASSWORD)
                .queryParam("name", NEW_NAME)
                .queryParam("surname", NEW_SURNAME)
                .queryParam("phoneNumber", NEW_PHONE_NUMBER);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertTrue(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
        UserRepo userRepo = new UserRepoImpl();
        User user = userRepo.getByLogin(NEW_LOGIN);
        checkFoundUser(user, NEW_PHONE_NUMBER);
    }

    @Test
    public void shouldSuccessfullyRegisterNewUserWithoutPhoneNumber(){
        WebTarget target = target("/register")
                .queryParam("login", NEW_LOGIN)
                .queryParam("email", NEW_EMAIL)
                .queryParam("password", NEW_PASSWORD)
                .queryParam("confirmPassword", NEW_PASSWORD)
                .queryParam("name", NEW_NAME)
                .queryParam("surname", NEW_SURNAME)
                .queryParam("phoneNumber", null);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertTrue(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
        UserRepo userRepo = new UserRepoImpl();
        User user = userRepo.getByLogin(NEW_LOGIN);
        checkFoundUser(user, null);
    }

    @Test
    public void shouldGetErrorDueToRegisterNewUserWithTakenLogin(){
        WebTarget target = target("/register")
                .queryParam("login", TEST_LOGIN)
                .queryParam("email", NEW_EMAIL)
                .queryParam("password", NEW_PASSWORD)
                .queryParam("confirmPassword", NEW_PASSWORD)
                .queryParam("name", NEW_NAME)
                .queryParam("surname", NEW_SURNAME)
                .queryParam("phoneNumber", null);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertFalse(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
    }

    @Test
    public void shouldGetErrorDueToRegisterNewUserWithTakenEmail(){
        WebTarget target = target("/register")
                .queryParam("login", NEW_LOGIN)
                .queryParam("email", TEST_EMAIL)
                .queryParam("password", NEW_PASSWORD)
                .queryParam("confirmPassword", NEW_PASSWORD)
                .queryParam("name", NEW_NAME)
                .queryParam("surname", NEW_SURNAME)
                .queryParam("phoneNumber", null);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertFalse(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
    }

    public void checkFoundUser(User user, String expectedPhoneNumber){
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getLogin().equals(NEW_LOGIN));
        Assert.assertTrue(user.getPassword().equals(NEW_PASSWORD));
        Assert.assertTrue(user.getEmail().equals(NEW_EMAIL));
        Assert.assertTrue(user.getName().equals(NEW_NAME));
        Assert.assertTrue(user.getSurname().equals(NEW_SURNAME));
        if(expectedPhoneNumber == null){
            Assert.assertNull(user.getPhoneNumber());
        }else {
            Assert.assertTrue(user.getPhoneNumber().equals(expectedPhoneNumber));
        }
    }
}
