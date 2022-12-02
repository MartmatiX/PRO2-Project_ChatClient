package models;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class Message {

    @Expose()
    private String author;

    @Expose()
    private String text;

    @Expose()
    private LocalDateTime created;

    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 2;

    private static final String AUTHOR_SYSTEM = "System";

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        this.created = LocalDateTime.now();
    }

    public Message(int type, String username) {
        this.author = AUTHOR_SYSTEM;
        this.created = LocalDateTime.now();
        if (type == USER_LOGGED_IN) {
            text = username + " has joined the chat";
        } else if (type == USER_LOGGED_OUT) {
            text = username + " has left the chat";
        }
    }

    public Message(String author, String text, LocalDateTime created){
        this.author = author;
        this.text = text;
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        if (author.equals(AUTHOR_SYSTEM)) {
            return text + "\n";
        } else {
            String s = author + " [" + created + "]\n";
            s += text + "\n";
            return s;
        }
    }
}
