package com.maluc.usecase.changepassword;

import com.maluc.command.CommandHandler;
import com.maluc.usecase.login.InvalidLoginOrPasswordException;
import com.maluc.usecase.register.DifferentPasswordsException;
import com.maluc.user.User;
import com.maluc.user.UserRepo;
import com.maluc.user.UserRepoImpl;

public class ChangePasswordCommandHandler implements CommandHandler<ChangePasswordCommand, Void> {
    public Void handle(ChangePasswordCommand command) throws Exception {
        UserRepo userRepo = new UserRepoImpl();
        User user = userRepo.getByLogin(command.getLogin());
        if(!command.getOldPassword().equals(user.getPassword())){
            throw new InvalidLoginOrPasswordException();
        }else{
            if(!command.getNewPassword().equals(command.getConfirmNewPassword())){
                throw new DifferentPasswordsException();
            }else {
                userRepo.changePassword(command.getLogin(), command.getNewPassword());
            }
        }
        return null;
    }
}
