package com.maluc.services;

import com.maluc.usecase.deleteuser.DeleteUserCommand;
import com.maluc.usecase.deleteuser.DeleteUserCommandHandler;
import com.maluc.usecase.login.InvalidLoginOrPasswordException;
import com.maluc.usecase.login.LoginQuery;
import com.maluc.usecase.login.LoginQueryHandler;
import com.maluc.usecase.register.DifferentPasswordsException;
import com.maluc.usecase.register.LoginOrEmailAlreadyTakenException;
import com.maluc.usecase.register.RegisterCommand;
import com.maluc.usecase.register.RegisterCommandHandler;
import com.maluc.user.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

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

    @PUT
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public void register(@QueryParam("login") String login,
                         @QueryParam("email") String email,
                         @QueryParam("password") String password,
                         @QueryParam("confirmPassword") String confirmPassword,
                         @QueryParam("name") String name,
                         @QueryParam("surname") String surname,
                         @QueryParam("phoneNumber") String phoneNumber) throws LoginOrEmailAlreadyTakenException, SQLException, DifferentPasswordsException {
        RegisterCommand command = RegisterCommand.builder()
                .login(login)
                .email(email)
                .password(password)
                .confirmPassword(confirmPassword)
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber != null ? phoneNumber : null)
                .build();
        RegisterCommandHandler registerCommandHandler = new RegisterCommandHandler();
        registerCommandHandler.handle(command);
    }

    @DELETE
    @Path("{login}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUserAccount(@PathParam("login") String login) throws Exception {
        DeleteUserCommand command = DeleteUserCommand.builder()
                .login(login)
                .build();
        DeleteUserCommandHandler deleteUserCommandHandler = new DeleteUserCommandHandler();
        deleteUserCommandHandler.handle(command);
    }

}
