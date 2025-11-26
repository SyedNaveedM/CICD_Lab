package org.example;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoItem {
    private final int id;
    private final String description;
    private boolean done;
    private final LocalDateTime createdAt;

    public TodoItem(int id, String description) {
        this.id = id;
        this.description = description;
        this.done = false;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        String status = done ? "[X]" : "[ ]";
        return String.format("%d : %s %s",
                 id, description, status);
    }
}

