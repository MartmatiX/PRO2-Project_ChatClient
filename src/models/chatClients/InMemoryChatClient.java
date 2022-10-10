package models.chatClients;

import models.Message;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InMemoryChatClient implements ChatClient {

    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    public InMemoryChatClient() {
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser, text));
    }

    @Override
    public void login(String userName) {
        loggedUser = userName;
        loggedUsers.add(loggedUser);
    }

    @Override
    public void logout() {
        loggedUsers.remove(loggedUser);
        loggedUser = null;
    }

    @Override
    public Boolean isAuthenticated() {
        return loggedUser != null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addActionListenerLoggedUsersChanged(ActionListener toAdd) {

    }

    @Override
    public void addActionListenerMessageChanged(ActionListener toAdd) {

    }
}
