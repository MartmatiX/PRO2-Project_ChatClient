package models.chatClients;

import models.Message;

import java.awt.event.ActionListener;
import java.util.List;

public interface ChatClient {

    void sendMessage(String text);

    void login(String userName);

    void logout();

    Boolean isAuthenticated();

    List<String> getLoggedUsers();

    List<Message> getMessages();

    void addActionListener(ActionListener listener);

}
