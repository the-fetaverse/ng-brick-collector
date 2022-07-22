package configurations;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BrickCollectorDBCreds {
    // private fields
    private static BrickCollectorDBCreds instance;
    private String url;
    private String username;
    private String password;

    // Private constructor
    private BrickCollectorDBCreds() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(InputStream input = BrickCollectorDBCreds.class.getClassLoader().getResourceAsStream("application.properties")) {
                Properties props = new Properties();
                props.load(input);
                this.url = props.getProperty("db.url");
                this.username = props.getProperty("db.username");
                this.password = props.getProperty("db.password");
            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // public getter for the instance
    public static BrickCollectorDBCreds getInstance() {
        if (instance == null) {
            instance = new BrickCollectorDBCreds();
        }
        return instance;
    }

    // public getters for the application.properties
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}