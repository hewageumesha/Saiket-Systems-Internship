package ui;

import controller.TaskController;
import model.*;
import service.TaskService;
import service.impl.TaskServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessionalTaskManager extends JFrame implements TaskController {
    private final TaskService taskService;
    private JPanel taskListPanel;
    private JLabel statusLabel;
    private JTextField searchField;
    private JComboBox<FilterType> filterCombo;
    private Timer notificationTimer;

    private JLabel totalLabel, completedLabel, pendingLabel, overdueLabel, rateLabel;

    public ProfessionalTaskManager() {
        this.taskService = new TaskServiceImpl();
        initializeUI();
        loadSampleData();
    }

    private void initializeUI() {
        setTitle("Task Manager Pro - Internship Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));

        Color bgColor = new Color(248, 249, 250);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(bgColor);

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        contentPanel.setOpaque(false);

        contentPanel.add(createStatsPanel(), BorderLayout.NORTH);

        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setBorder(new EmptyBorder(10, 25, 15, 25));
        statusLabel.setForeground(new Color(108, 117, 125));

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 10));
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));
        panel.setBackground(new Color(33, 37, 41));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 2));
        titlePanel.setOpaque(false);

        JLabel logoLabel = new JLabel("Task Manager Pro");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logoLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Enterprise Task Management System");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(173, 181, 189));

        titlePanel.add(logoLabel);
        titlePanel.add(subtitleLabel);

        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 5));
        controlsPanel.setOpaque(false);

        searchField = new JTextField(18);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(73, 80, 87)),
                new EmptyBorder(10, 15, 10, 15)
        ));
        searchField.setBackground(new Color(52, 58, 64));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setToolTipText("Search tasks by title or description");

        searchField.setText("Search tasks...");
        searchField.setForeground(new Color(173, 181, 189));
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search tasks...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search tasks...");
                    searchField.setForeground(new Color(173, 181, 189));
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterAndDisplay(); }
            public void removeUpdate(DocumentEvent e) { filterAndDisplay(); }
            public void changedUpdate(DocumentEvent e) { filterAndDisplay(); }
        });

        filterCombo = new JComboBox<>(FilterType.values());
        filterCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        filterCombo.setBackground(new Color(52, 58, 64));
        filterCombo.setForeground(new Color(173, 181, 189));
        filterCombo.setBorder(new EmptyBorder(8, 12, 8, 12));
        filterCombo.addActionListener(e -> filterAndDisplay());

        RoundedButton addButton = new RoundedButton("+ New Task", 8);
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(new EmptyBorder(12, 24, 12, 24));
        addButton.addActionListener(e -> showTaskDialog(null));

        controlsPanel.add(new JLabel(" ") {{
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }});
        controlsPanel.add(searchField);
        controlsPanel.add(new JLabel("Filter:") {{ setForeground(Color.WHITE); }});
        controlsPanel.add(filterCombo);
        controlsPanel.add(addButton);

        panel.add(titlePanel, BorderLayout.WEST);
        panel.add(controlsPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 5, 15, 0));
        panel.setOpaque(false);

        totalLabel = createStatCard("TOTAL TASKS", "0", new Color(108, 117, 125), "");
        completedLabel = createStatCard("COMPLETED", "0", new Color(40, 167, 69), "");
        pendingLabel = createStatCard("PENDING", "0", new Color(255, 193, 7), "");
        overdueLabel = createStatCard("OVERDUE", "0", new Color(220, 53, 69), "");
        rateLabel = createStatCard("SUCCESS RATE", "0%", new Color(0, 123, 255), "");

        panel.add(totalLabel.getParent());
        panel.add(completedLabel.getParent());
        panel.add(pendingLabel.getParent());
        panel.add(overdueLabel.getParent());
        panel.add(rateLabel.getParent());

        return panel;
    }

    private JLabel createStatCard(String title, String value, Color color, String icon) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 226, 230)),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(color);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        titleLabel.setForeground(new Color(108, 117, 125));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        textPanel.setOpaque(false);
        textPanel.add(valueLabel);
        textPanel.add(titleLabel);

        card.add(iconLabel, BorderLayout.WEST);
        card.add(textPanel, BorderLayout.CENTER);

        card.putClientProperty("valueLabel", valueLabel);
        return valueLabel;
    }

    private void showTaskDialog(Task existingTask) {
        boolean isEdit = existingTask != null;
        JDialog dialog = new JDialog(this, isEdit ? "Edit Task" : "New Task", true);
        dialog.setSize(500, 520);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ===== HEADING =====
        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBackground(Color.WHITE);
        headingPanel.setBorder(new EmptyBorder(30, 0, 20, 0));

        JLabel headingLabel = new JLabel(isEdit ? "Edit Task" : "New Task", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headingLabel.setForeground(new Color(33, 37, 41));

        JLabel subHeadingLabel = new JLabel(isEdit ? "Update your task details below" : "Fill in the details to add a new task", SwingConstants.CENTER);
        subHeadingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subHeadingLabel.setForeground(new Color(108, 117, 125));

        JPanel headingTextPanel = new JPanel(new GridLayout(2, 1, 8, 8));
        headingTextPanel.setOpaque(false);
        headingTextPanel.add(headingLabel);
        headingTextPanel.add(subHeadingLabel);

        headingPanel.add(headingTextPanel, BorderLayout.CENTER);

        // Top colored border
        JPanel topBorder = new JPanel();
        topBorder.setBackground(isEdit ? new Color(0, 123, 255) : new Color(40, 167, 69));
        topBorder.setPreferredSize(new Dimension(0, 4));
        mainPanel.add(topBorder, BorderLayout.NORTH);

        mainPanel.add(headingPanel, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(10, 40, 10, 40));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.weightx = 1.0;

        addFormRow(formPanel, gbc, 0, "Title *", createStyledTextField(isEdit ? existingTask.getTitle() : ""));
        addFormRow(formPanel, gbc, 1, "Description", createStyledTextArea(isEdit ? existingTask.getDescription() : ""));
        addFormRow(formPanel, gbc, 2, "Priority", createPriorityCombo(isEdit ? existingTask.getPriority() : Priority.MEDIUM));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(20, 25, 30, 25));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelBtn.setBackground(new Color(233, 236, 239));
        cancelBtn.setForeground(new Color(73, 80, 87));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218)),
                new EmptyBorder(12, 30, 12, 30)
        ));
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> dialog.dispose());

        // ===== UPDATE TASK BUTTON - FIXED =====
        JButton saveBtn = new JButton(isEdit ? "Update Task" : "Create Task");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        if (isEdit) {
            saveBtn.setBackground(new Color(0, 123, 255)); // Blue for Edit
        } else {
            saveBtn.setBackground(new Color(40, 167, 69)); // Green for New
        }

        saveBtn.setFocusPainted(false);
        saveBtn.setOpaque(true);  // IMPORTANT: Must be true for background to show
        saveBtn.setContentAreaFilled(true);  // IMPORTANT: Must be true
        saveBtn.setBorder(BorderFactory.createEmptyBorder(12, 35, 12, 35));
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JTextField titleField = (JTextField) formPanel.getClientProperty("titleField");
        JTextArea descArea = (JTextArea) formPanel.getClientProperty("descArea");
        JComboBox<Priority> priorityCombo = (JComboBox<Priority>) formPanel.getClientProperty("priorityCombo");

        saveBtn.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String desc = descArea.getText().trim();
                Priority priority = (Priority) priorityCombo.getSelectedItem();

                if (title.isEmpty()) {
                    showValidationError(dialog, "Title is required");
                    return;
                }

                if (isEdit) {
                    existingTask.setTitle(title);
                    existingTask.setDescription(desc);
                    existingTask.setPriority(priority);
                    taskService.updateTask(existingTask);
                    showNotification("Task updated successfully!", NotificationType.SUCCESS);
                } else {
                    Task newTask = new Task(title, desc, priority);
                    taskService.addTask(newTask);
                    showNotification("Task created successfully!", NotificationType.SUCCESS);
                }

                dialog.dispose();
                refreshView();
            } catch (Exception ex) {
                showValidationError(dialog, ex.getMessage());
            }
        });

        buttonPanel.add(cancelBtn);
        buttonPanel.add(saveBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(new Color(73, 80, 87));
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);

        if (field instanceof JTextField) panel.putClientProperty("titleField", field);
        else if (field instanceof JScrollPane) panel.putClientProperty("descArea", ((JScrollPane) field).getViewport().getView());
        else if (field instanceof JComboBox) panel.putClientProperty("priorityCombo", field);
    }

    private JTextField createStyledTextField(String text) {
        JTextField field = new JTextField(text, 25);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218)),
                new EmptyBorder(10, 12, 10, 12)
        ));
        return field;
    }

    private JScrollPane createStyledTextArea(String text) {
        JTextArea area = new JTextArea(text, 3, 25);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(new EmptyBorder(10, 12, 10, 12));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218)));
        return scroll;
    }

    private JComboBox<Priority> createPriorityCombo(Priority selected) {
        JComboBox<Priority> combo = new JComboBox<>(Priority.values());
        combo.setSelectedItem(selected);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218)));
        return combo;
    }

    private void showValidationError(JDialog parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void addTask(Task task) {
        taskService.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskService.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Delete Task?</b><br>" + escapeHtml(task.getTitle()) + "</html>",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            taskService.deleteTask(task.getId());
            showNotification("Task deleted successfully!", NotificationType.SUCCESS);
            refreshView();
        }
    }

    @Override
    public void toggleTaskStatus(int taskId) {
        taskService.toggleTaskStatus(taskId);
        refreshView();
    }

    @Override
    public void editTask(Task task) {
        showTaskDialog(task);
    }

    @Override
    public void refreshView() {
        filterAndDisplay();
        updateStatistics();
    }

    @Override
    public void showNotification(String message, NotificationType type) {
        statusLabel.setText(message);
        statusLabel.setForeground(type.getColor());
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        if (notificationTimer != null) notificationTimer.stop();
        notificationTimer = new Timer(3000, e -> {
            statusLabel.setText("Ready");
            statusLabel.setForeground(new Color(108, 117, 125));
            statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        });
        notificationTimer.setRepeats(false);
        notificationTimer.start();
    }

    private void filterAndDisplay() {
        String search = searchField.getText();
        if (search.equals("Search tasks...")) search = "";

        FilterType filter = (FilterType) filterCombo.getSelectedItem();
        List<Task> filtered;

        switch (filter) {
            case PENDING:
                filtered = taskService.getTasksByStatus(TaskStatus.PENDING);
                break;
            case COMPLETED:
                filtered = taskService.getTasksByStatus(TaskStatus.COMPLETED);
                break;
            case HIGH_PRIORITY:
                filtered = taskService.getTasksByPriority(Priority.HIGH);
                break;
            case MEDIUM_PRIORITY:
                filtered = taskService.getTasksByPriority(Priority.MEDIUM);
                break;
            case LOW_PRIORITY:
                filtered = taskService.getTasksByPriority(Priority.LOW);
                break;
            default:
                filtered = taskService.getAllTasks();
        }

        if (!search.trim().isEmpty()) {
            String query = search.toLowerCase();
            filtered = filtered.stream()
                    .filter(t -> t.getTitle().toLowerCase().contains(query) ||
                            t.getDescription().toLowerCase().contains(query))
                    .collect(Collectors.toList());
        }

        displayTasks(filtered);
    }

    private void displayTasks(List<Task> tasks) {
        taskListPanel.removeAll();

        if (tasks.isEmpty()) {
            JPanel emptyPanel = new JPanel(new GridBagLayout());
            emptyPanel.setOpaque(false);

            JLabel emptyIcon = new JLabel("");
            emptyIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64));

            JLabel emptyLabel = new JLabel("No tasks found");
            emptyLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            emptyLabel.setForeground(new Color(173, 181, 189));

            JLabel emptySub = new JLabel("Create a new task to get started");
            emptySub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            emptySub.setForeground(new Color(173, 181, 189));

            JPanel textPanel = new JPanel(new GridLayout(3, 1, 5, 5));
            textPanel.setOpaque(false);
            textPanel.add(emptyIcon);
            textPanel.add(emptyLabel);
            textPanel.add(emptySub);

            emptyPanel.add(textPanel);
            taskListPanel.add(emptyPanel);
        } else {
            for (Task task : tasks) {
                TaskCard card = new TaskCard(task, this);
                taskListPanel.add(card);
                taskListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        taskListPanel.revalidate();
        taskListPanel.repaint();
    }

    private void updateStatistics() {
        TaskStatistics stats = taskService.getStatistics();

        totalLabel.setText(String.valueOf(stats.getTotal()));
        completedLabel.setText(String.valueOf(stats.getCompleted()));
        pendingLabel.setText(String.valueOf(stats.getPending()));
        overdueLabel.setText(String.valueOf(stats.getOverdue()));
        rateLabel.setText(String.format("%.1f%%", stats.getCompletionRate()));
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    private void loadSampleData() {
        taskService.addTask(new Task("Complete Java OOP Assignment",
                "Implement classes, inheritance, and polymorphism examples", Priority.HIGH));
        taskService.addTask(new Task("Review Spring Boot Documentation",
                "Study dependency injection and MVC architecture patterns", Priority.HIGH));
        taskService.addTask(new Task("Prepare for Technical Interview",
                "Practice coding problems on data structures and algorithms", Priority.MEDIUM));
        taskService.addTask(new Task("Update Resume",
                "Add recent internship experience and technical skills", Priority.MEDIUM));
        taskService.addTask(new Task("Team Meeting Preparation",
                "Prepare slides for weekly progress review meeting", Priority.LOW));

        Task completed1 = new Task("Setup Development Environment",
                "Install JDK, IDE, and configure build tools", Priority.HIGH);
        completed1.markAsComplete();
        taskService.addTask(completed1);

        Task completed2 = new Task("Git Repository Initialization",
                "Create repo, add .gitignore, and make initial commit", Priority.MEDIUM);
        completed2.markAsComplete();
        taskService.addTask(completed2);

        refreshView();
    }
}