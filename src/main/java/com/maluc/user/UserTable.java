package com.maluc.user;

public class UserTable {
    private UserTable() throws Exception {
        throw new Exception(String.format("Sorry, but there's no %s instance for you!", this.getClass()));
    }
    public static final String SCHEMA = "MALUC";
    public static final String NAME = "USER_TABLE";

    public static final String USER_ID_COLUMN_NAME = "USER_ID";
    public static final String LOGIN_COLUMN_NAME = "LOGIN";
    public static final String PASSWORD_COLUMN_NAME = "PASSWORD";
    public static final String EMAIL_COLUMN_NAME = "EMAIL";
    public static final String PHONE_NUMBER_COLUMN_NAME = "PHONE_NUMBER";
    public static final String USER_NAME_COLUMN_NAME = "USER_NAME";
    public static final String USER_SURNAME_COLUMN_NAME = "USER_SURNAME";
}
