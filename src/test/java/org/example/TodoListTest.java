package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class TodoListTest {

    @Test
    public void testAddTask() {
        TodoList list = new TodoList();
        list.addTask("Test");
        assertEquals(1, list.getAllTasks().size());
        assertEquals("Test", list.getAllTasks().get(0).getDescription());
    }

    @Test
    public void testMarkDone() {
        TodoList list = new TodoList();
        list.addTask("Task 1");
        assertTrue(list.markDone(1));
        assertTrue(list.getAllTasks().get(0).isDone());
    }
}
