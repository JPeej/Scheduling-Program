package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**Manages connection to MySQL database for CRUD.
 * mysql-connector-j-8.0.31. */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**Opens connection between program and MySQL database.
     * Call once at program start to open connection to MySQL database.
     * @throws if failed to connect.
     * */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**Getter for connection in CRUD methods.
     * Gets connection. Efficient compared to redundant open and close cycles.
     * @return Currently open Connection object that is open. */
    public static Connection getConnection() {
        return connection;
    }

    /**Closes connection between program and MySQL database.
     * Call once at program end to close connection to MySQL database.
     * @throws Exception race condition*/
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
        }
    }

}