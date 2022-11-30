package models.chatClients;

import models.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InMemoryChatClient implements ChatClient {

    private String loggedUser;
    private final List<String> loggedUsers;
    private final List<Message> messages;

    private final List<ActionListener> listeners = new ArrayList<>();

    public InMemoryChatClient() {
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser, text));
        raiseEventMessageChanged();
    }

    @Override
    public void login(String userName) {
        loggedUser = userName;
        loggedUsers.add(loggedUser);
        addSystemMessage(Message.USER_LOGGED_IN, userName);
        raiseEventLoggedUsersChanged();
    }

    @Override
    public void logout() {
        addSystemMessage(Message.USER_LOGGED_OUT, loggedUser);
        loggedUsers.remove(loggedUser);
        loggedUser = null;
        raiseEventLoggedUsersChanged();
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
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    private void raiseEventLoggedUsersChanged(){
        for (ActionListener aL : listeners){
            aL.actionPerformed(new ActionEvent(this, 1, "userChanged"));
        }
    }

    private void raiseEventMessageChanged(){
        for (ActionListener aL : listeners){
            aL.actionPerformed(new ActionEvent(this, 2, "messagesChanged"));
        }
    }

    private void addSystemMessage(int type, String username){
        messages.add(new Message(type, username));
        raiseEventMessageChanged();
    }
}
