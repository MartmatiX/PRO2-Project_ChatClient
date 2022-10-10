package models;

import java.time.LocalDateTime;

public class Message {

    private String author;
    private String text;
    private LocalDateTime created;

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        this.created = LocalDateTime.now();
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
}
