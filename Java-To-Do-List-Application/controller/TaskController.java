package controller;

import model.NotificationType;
import model.Task;

public interface TaskController {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
    void toggleTaskStatus(int taskId);
    void editTask(Task task);
    void refreshView();
    void showNotification(String message, NotificationType type);
}