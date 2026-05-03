package ui;

import javax.swing.*;
import java.awt.*;

public class CheckBoxIcon implements Icon {
    private final boolean selected;
    private static final int SIZE = 22;

    public CheckBoxIcon(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (selected) {
            g2.setColor(new Color(40, 167, 69));
            g2.fillOval(x, y, SIZE, SIZE);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x + 6, y + 11, x + 10, y + 15);
            g2.drawLine(x + 10, y + 15, x + 16, y + 7);
        } else {
            g2.setColor(new Color(206, 212, 218));
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(x + 1, y + 1, SIZE - 2, SIZE - 2);
        }
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return SIZE + 4;
    }

    @Override
    public int getIconHeight() {
        return SIZE + 4;
    }
}