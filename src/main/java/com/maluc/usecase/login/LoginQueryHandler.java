package com.maluc.usecase.login;

import com.maluc.query.QueryHandler;
import com.maluc.user.User;
import com.maluc.user.UserRepo;
import com.maluc.user.UserRepoImpl;

public class LoginQueryHandler implements QueryHandler<LoginQuery, User> {
    public User handle(LoginQuery query) throws InvalidLoginOrPasswordException {
        UserRepo userRepo = new UserRepoImpl();
        User user = userRepo.getByLogin(query.getLogin());
        if(user == null)
            throw new InvalidLoginOrPasswordException();
        else{
            if(query.getPassword().equals(user.getPassword())){
                return user;
            }else {
                throw new InvalidLoginOrPasswordException();
            }
        }
    }
}
