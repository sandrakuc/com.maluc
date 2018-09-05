package com.maluc.services;

import com.maluc.usecase.login.InvalidLoginOrPasswordException;
import com.maluc.usecase.login.LoginQuery;
import com.maluc.usecase.login.LoginQueryHandler;
import com.maluc.user.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class UserRestServices {

    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@PathParam("login") String login, @QueryParam("password") String password) throws InvalidLoginOrPasswordException {
        LoginQuery loginQuery = new LoginQuery().builder()
                .login(login)
                .password(password)
                .build();
        LoginQueryHandler loginQueryHandler = new LoginQueryHandler();
        User user = loginQueryHandler.handle(loginQuery);
        return user;
    }
}
