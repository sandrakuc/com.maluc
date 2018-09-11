package com.maluc.user;

import java.sql.SQLException;

public class UserStatementsProvider {
    public static final String TEST_LOGIN = "login123";
    public static final String TEST_PASSWORD = "password123";
    public static final String TEST_EMAIL = "email@email.com";
    public static final String TEST_NAME = "Janusz";
    public static final String TEST_SURNAME = "Nosacz";
    public static final String TEST_PHONE_NUMBER = "123456789";

    public static final String NON_EXISTING_LOGIN = "another69";
    public static final String INVALID_PASSWORD = "Invalid123";

    public static final User SAMPLE_USER = new User().builder()
            .login(TEST_LOGIN)
            .password(TEST_PASSWORD)
            .email(TEST_EMAIL)
            .name(TEST_NAME)
            .surname(TEST_SURNAME)
            .phoneNumber(TEST_PHONE_NUMBER)
            .build();

    public static void createUser() throws SQLException {
        UserRepo userRepo = new UserRepoImpl();
        userRepo.save(SAMPLE_USER);
    }

    public static void deleteUser() throws SQLException {
        deleteUser(TEST_LOGIN);
    }

    public static void deleteUser(String login) throws SQLException{
        UserRepo userRepo = new UserRepoImpl();
        userRepo.deleteUserByLogin(login);
    }
}
