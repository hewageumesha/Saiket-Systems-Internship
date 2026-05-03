package service;

import model.Task;
import model.TaskStatistics;
import model.TaskStatus;
import model.Priority;

import java.util.List;

public interface TaskService {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
    Task getTask(int taskId);
    List<Task> getAllTasks();
    List<Task> getTasksByStatus(TaskStatus status);
    List<Task> getTasksByPriority(Priority priority);
    List<Task> searchTasks(String query);
    void toggleTaskStatus(int taskId);
    TaskStatistics getStatistics();
}
