package com.maluc.usecase.login;

public class InvalidLoginOrPasswordException extends Exception {
    public String toString(){
        return "Invalid login or password!!!";
    }
}
