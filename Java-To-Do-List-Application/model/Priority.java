package model;

import java.awt.*;

public enum Priority {
    HIGH("High", new Color(220,53, 69), 1),
    MEDIUM("Medium", new Color(255,193, 7), 2),
    LOW("Low", new Color(40, 167, 69), 3);

    private final String label;
    private final Color color;
    private final int order;

    // constructor
    Priority(String label, Color color, int order) {
        this.label = label;
        this.color = color;
        this.order = order;
    }

    // getters
    public String getLabel() {
        return label;
    }

    public Color getColor() {
        return color;
    }

    public int getOrder() {
        return order;
    }

    public static Priority fromString(String priority) {
        for (Priority p : values()) {
            if (p.label.equalsIgnoreCase(priority)) return p;
        }
        return MEDIUM;
    }
}
