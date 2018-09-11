package com.maluc.usecase.deleteuser;

import com.maluc.command.CommandHandler;
import com.maluc.user.UserRepo;
import com.maluc.user.UserRepoImpl;

public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, Void> {
    public Void handle(DeleteUserCommand command) throws Exception{
        UserRepo userRepo = new UserRepoImpl();
        userRepo.deleteUserByLogin(command.getLogin());
        return null;
    }
}
