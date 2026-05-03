package model;

public enum TaskStatus {
    PENDING("Pending"),
    COMPLETED("Completed");

    private final String label;

    // constructor
    TaskStatus(String label) {
        this.label = label;
    }

    // getter
    public String getLabel() {
        return label;
    }
}
