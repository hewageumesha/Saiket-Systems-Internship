package model;

import java.time.LocalDate;

public final class Task {
    private final int id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private final LocalDate createdDate;
    private LocalDate completedDate;

    private static int idCounter = 0;

    public Task(String title, String description, Priority priority) {
        this.id = ++idCounter;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.priority = priority;
        this.createdDate = LocalDate.now();
        this.completedDate = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }

    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        this.priority = priority;
    }

    public void markAsComplete() {
        if (this.status == TaskStatus.PENDING) {
            this.status = TaskStatus.COMPLETED;
            this.completedDate = LocalDate.now();
        }
    }

    public void markAsPending() {
        if (this.status == TaskStatus.COMPLETED) {
            this.status = TaskStatus.PENDING;
            this.completedDate = null;
        }
    }

    public boolean isOverdue() {
        return status == TaskStatus.PENDING &&
                createdDate.plusDays(7).isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return String.format("Task[id=%d, title=%s, status=%s, priority=%s]",
                id, title, status.getLabel(), priority.getLabel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}