package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private final TodoList todoList;
    private final Scanner scanner;

    public TodoApp() {
        this.todoList = new TodoList();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TodoApp app = new TodoApp();
        app.run();
    }

    public void run() {
        System.out.println("=====================================");
        System.out.println("      TO-DO LIST CLI APPLICATION     ");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddTask();
                    break;
                case 2:
                    handleListTasks();
                    break;
                case 3:
                    handleMarkDone();
                    break;
                case 4:
                    handleDeleteTask();
                    break;
                case 5:
                    handleClearCompleted();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Mark Task as Done");
        System.out.println("4. Delete Task");
        System.out.println("5. Clear Completed Tasks");
        System.out.println("6. Exit");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();  // consume leftover newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private String readLine() {
        System.out.print("Enter task description: ");
        return scanner.nextLine();
    }

    private void handleAddTask() {
        String desc = readLine();
        try {
            TodoItem item = todoList.addTask(desc);
            System.out.println("Added: " + item);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleListTasks() {
        List<TodoItem> tasks = todoList.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found. Your list is empty.");
            return;
        }
        System.out.println("Current Tasks:");
        for (TodoItem item : tasks) {
            System.out.println(item);
        }
    }

    private void handleMarkDone() {
        if (todoList.isEmpty()) {
            System.out.println("No tasks to mark as done.");
            return;
        }
        int id = readInt("Enter task ID to mark as done: ");
        boolean success = todoList.markDone(id);
        if (success) {
            System.out.println("Task #" + id + " marked as done.");
        } else {
            System.out.println("Task not found or already done.");
        }
    }

    private void handleDeleteTask() {
        if (todoList.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }
        int id = readInt("Enter task ID to delete: ");
        boolean success = todoList.deleteTask(id);
        if (success) {
            System.out.println("Task #" + id + " deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void handleClearCompleted() {
        int removed = todoList.clearCompleted();
        if (removed == 0) {
            System.out.println("No completed tasks to clear.");
        } else {
            System.out.println("Cleared " + removed + " completed task(s).");
        }
    }
}
