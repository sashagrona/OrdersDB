package net.bigmir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String url;
    private String user;
    private String password;

    public ConnectionFactory() {
        DBProperties dbProperties = new DBProperties();
        this.url = dbProperties.getUrl();
        this.user = dbProperties.getUser();
        this.password = dbProperties.getPassword();
    }
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
