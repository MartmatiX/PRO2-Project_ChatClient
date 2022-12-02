package models.chatClients.database;

import models.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations {

    private final Connection connection;

    public JdbcDatabaseOperations(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url);
    }

    @Override
    public void addMessage(Message message) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO ChatMessages (author, text, created) "
                    + "VALUES ('" + message.getAuthor() + "', '" + message.getText() + "', '" + Timestamp.valueOf(message.getCreated()) + "')";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getMessages() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM ChatMessages";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Message> messagesFromDb = new ArrayList<>();
            while (resultSet.next()) {
                String author = resultSet.getString("author");
                String text = resultSet.getString("text");
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                Message message = new Message(author, text, created);
                messagesFromDb.add(message);
            }
            return messagesFromDb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
