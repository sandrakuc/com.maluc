package com.maluc.usecase.register;

public class DifferentPasswordsException extends Exception {
    @Override
    public String toString() {
        return "Password and confirmation password are different!";
    }
}
