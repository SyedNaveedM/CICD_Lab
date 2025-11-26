package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TodoList {

    private final List<TodoItem> items = new ArrayList<>();
    private int nextId = 1;

    public TodoItem addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty.");
        }
        TodoItem item = new TodoItem(nextId++, description.trim());
        items.add(item);
        return item;
    }

    public List<TodoItem> getAllTasks() {
        return Collections.unmodifiableList(items);
    }

    public boolean markDone(int id) {
        TodoItem item = findById(id);
        if (item != null && !item.isDone()) {
            item.markDone();
            return true;
        }
        return false;
    }

    public boolean deleteTask(int id) {
        Iterator<TodoItem> it = items.iterator();
        while (it.hasNext()) {
            TodoItem item = it.next();
            if (item.getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public int clearCompleted() {
        int removed = 0;
        Iterator<TodoItem> it = items.iterator();
        while (it.hasNext()) {
            TodoItem item = it.next();
            if (item.isDone()) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    private TodoItem findById(int id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}

