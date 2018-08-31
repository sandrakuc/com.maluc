package com.maluc.user;

public class DatabaseData {

    private DatabaseData() throws Exception {
        throw new Exception(String.format("Sorry, but there's no %s instance for you!", this.getClass()));
    }

    public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

    public static final String USER = "maluc";
    public static final String PASSWORD = "maluc";
}
