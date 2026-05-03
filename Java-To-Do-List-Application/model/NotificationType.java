package model;

import java.awt.*;

public enum NotificationType {
    SUCCESS(new Color(40, 167, 69)),
    ERROR(new Color(220, 53, 69)),
    INFO(new Color(0, 123, 255));

    private final Color color;
    NotificationType(Color color) { this.color = color; }
    public Color getColor() { return color; }
}
