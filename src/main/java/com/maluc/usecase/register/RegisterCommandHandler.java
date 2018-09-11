package com.maluc.usecase.register;

import com.maluc.command.CommandHandler;
import com.maluc.user.User;
import com.maluc.user.UserRepo;
import com.maluc.user.UserRepoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterCommandHandler implements CommandHandler<RegisterCommand, Void> {
    public Void handle(RegisterCommand command) throws DifferentPasswordsException, LoginOrEmailAlreadyTakenException, SQLException {
        if(!command.getPassword().equals(command.getConfirmPassword())){
            throw new DifferentPasswordsException();
        }else{
            UserRepo userRepo = new UserRepoImpl();
            List<User> users = userRepo.getUserList();
            List<String> userLogins = new ArrayList<String>();
            List<String> userEmails = new ArrayList<String>();
            for(User user : users){
                userLogins.add(user.getLogin());
                userEmails.add(user.getEmail());
            }
            if(userLogins.contains(command.getLogin()) || userEmails.contains(command.getEmail())){
                throw new LoginOrEmailAlreadyTakenException();
            }else {
                User user = User.builder()
                        .login(command.getLogin())
                        .email(command.getEmail())
                        .password(command.getPassword())
                        .name(command.getName())
                        .surname(command.getSurname())
                        .phoneNumber(command.getPhoneNumber()!=null?command.getPhoneNumber():null)
                        .build();
                userRepo.save(user);
            }
        }
        return null;
    }
}
