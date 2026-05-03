package model;

public class TaskStatistics {
    private final int total;
    private final int completed;
    private final int pending;
    private final int overdue;

    public TaskStatistics(int total, int completed, int pending, int overdue) {
        this.total = total;
        this.completed = completed;
        this.pending = pending;
        this.overdue = overdue;
    }

    public int getTotal() {
        return total;
    }

    public int getCompleted() {
        return completed;
    }

    public int getPending() {
        return pending;
    }

    public int getOverdue() {
        return overdue;
    }

    public double getCompletionRate() {
        return total == 0 ? 0.0 : (completed * 100.0 / total);
    }
}