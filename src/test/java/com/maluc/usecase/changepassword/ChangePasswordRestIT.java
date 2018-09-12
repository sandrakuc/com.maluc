package com.maluc.usecase.changepassword;

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

public class ChangePasswordRestIT extends CheckUserIT {

    public static final String NEW_PASSWORD = "New890Password";
    public static final String INVALID_PASSWORD = "Invalid69";

    @Before
    public void addUserToDataBase() throws SQLException {
        createUser();
    }

    @After
    public void deleteUserFromDataBase() throws SQLException{
        deleteUser();
    }

    @Test
    public void shouldSuccessfullyChangePassword(){
        WebTarget target = target("/")
                .path(TEST_LOGIN)
                .path("changePassword")
                .queryParam("oldPassword", TEST_PASSWORD)
                .queryParam("newPassword", NEW_PASSWORD)
                .queryParam("confirmNewPassword", NEW_PASSWORD);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertTrue(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
        UserRepo userRepo = new UserRepoImpl();
        User user = userRepo.getByLogin(TEST_LOGIN);
        checkFoundUser(user);
    }

    @Test
    public void shouldGetErrorDueToGivingInvalidOldPassword(){
        WebTarget target = target("/")
                .path(TEST_LOGIN)
                .path("changePassword")
                .queryParam("oldPassword", INVALID_PASSWORD)
                .queryParam("newPassword", NEW_PASSWORD)
                .queryParam("confirmNewPassword", NEW_PASSWORD);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertFalse(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
    }

    @Test
    public void shouldGetErroeDueToGivingDifferenceNewPasswords(){
        WebTarget target = target("/")
                .path(TEST_LOGIN)
                .path("changePassword")
                .queryParam("oldPassword", TEST_PASSWORD)
                .queryParam("newPassword", NEW_PASSWORD)
                .queryParam("confirmNewPassword", INVALID_PASSWORD);
        Response response = target.request().put(Entity.entity(User.class, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertFalse(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));
    }

    @Override
    public void checkFoundUser(User user){
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getLogin().equals(TEST_LOGIN));
        Assert.assertTrue(user.getPassword().equals(NEW_PASSWORD));
        Assert.assertTrue(user.getEmail().equals(TEST_EMAIL));
        Assert.assertTrue(user.getName().equals(TEST_NAME));
        Assert.assertTrue(user.getSurname().equals(TEST_SURNAME));
        Assert.assertTrue(user.getPhoneNumber().equals(TEST_PHONE_NUMBER));
    }
}
