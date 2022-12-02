package models.chatClients.database;

import java.sql.*;

public class DbInitializer {

    private final String driver;
    private final String url;

    public DbInitializer(String driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void init() {
        try {
            // Load driver
            Class.forName(driver);

            // Open connection
            Connection connecton = DriverManager.getConnection(url);

            DatabaseMetaData dbm = connecton.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "CHATMESSAGES", null);
            if (!tables.next()) {
                Statement statement = connecton.createStatement();
                String sql =
                        "CREATE TABLE ChatMessages "
                                + "(id INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                                + "CONSTRAINT ChatMessages_PK PRIMARY KEY, "
                                + "author VARCHAR(50), "
                                + "text VARCHAR(1000), "
                                + "created timestamp)";
                statement.executeUpdate(sql);
                statement.close();
                connecton.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
