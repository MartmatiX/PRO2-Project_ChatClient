package models.chatClients.chatFileOperations;

import models.Message;

import java.util.List;

public interface ChatFileOperations {

    List<Message> loadMessages();

    void writeMessage(List<Message> messages);

    List<String> loadLoggedUsers();

    void writeLoggedUsers(List<String> users);

}
