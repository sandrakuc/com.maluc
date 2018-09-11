package com.maluc.usecase.deleteuser;

import com.maluc.services.UserRestServices;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.maluc.user.UserStatementsProvider.TEST_LOGIN;
import static com.maluc.user.UserStatementsProvider.createUser;

public class DeleteUserAccountRestIT extends JerseyTest {

    @Override
    public Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(UserRestServices.class);
    }

    @Before
    public void addUserToDataBase() throws SQLException {
        createUser();
    }

    @Test
    public void shouldSuccesfullyDeleteUserAccount(){
        WebTarget target = target("/")
                .path(TEST_LOGIN)
                .path("delete");
        Response response = target.request().delete();
        Assert.assertTrue(Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily()));

    }

}
