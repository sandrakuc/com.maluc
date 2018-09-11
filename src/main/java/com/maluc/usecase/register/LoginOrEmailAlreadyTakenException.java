package com.maluc.usecase.register;

public class LoginOrEmailAlreadyTakenException extends Exception {
    public String toString(){
        return "This login or email address is already taken";
    }
}
