package service.impl;

import model.Task;
import model.TaskStatistics;
import model.TaskStatus;
import model.Priority;
import service.TaskService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        if (task == null) throw new IllegalArgumentException("model.Task cannot be null");
        tasks.add(task);
    }

    @Override
    public void updateTask(Task task) {
        if (task == null) throw new IllegalArgumentException("model.Task cannot be null");
        int index = findTaskIndex(task.getId());
        if (index == -1) throw new IllegalArgumentException("model.Task not found");
        tasks.set(index, task);
    }

    @Override
    public void deleteTask(int taskId) {
        tasks.removeIf(t -> t.getId() == taskId);
    }

    @Override
    public Task getTask(int taskId) {
        return tasks.stream()
                .filter(t -> t.getId() == taskId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByPriority(Priority priority) {
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> searchTasks(String query) {
        if (query == null || query.trim().isEmpty()) return getAllTasks();
        String lowerQuery = query.toLowerCase();
        return tasks.stream()
                .filter(t -> t.getTitle().toLowerCase().contains(lowerQuery) ||
                        t.getDescription().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    @Override
    public void toggleTaskStatus(int taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            if (task.getStatus() == TaskStatus.PENDING) {
                task.markAsComplete();
            } else {
                task.markAsPending();
            }
        }
    }

    @Override
    public TaskStatistics getStatistics() {
        return new TaskStatistics(
                tasks.size(),
                (int) tasks.stream().filter(t -> t.getStatus() == TaskStatus.COMPLETED).count(),
                (int) tasks.stream().filter(t -> t.getStatus() == TaskStatus.PENDING).count(),
                (int) tasks.stream().filter(Task::isOverdue).count()
        );
    }

    private int findTaskIndex(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) return i;
        }
        return -1;
    }
}
