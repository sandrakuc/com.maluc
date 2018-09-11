package com.maluc.command;

import com.maluc.usecase.register.DifferentPasswordsException;
import com.maluc.usecase.register.LoginOrEmailAlreadyTakenException;

import java.sql.SQLException;

public interface CommandHandler <T extends Command, V> {
    V handle(T command) throws DifferentPasswordsException, LoginOrEmailAlreadyTakenException, SQLException;
}
