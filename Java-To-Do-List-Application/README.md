
# 📋 Task Manager Pro

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![Swing](https://img.shields.io/badge/GUI-Swing-orange)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> A professional desktop task management application built with Java Swing, demonstrating Object-Oriented Programming principles, MVC architecture, and modern UI/UX design patterns.

---

## ✨ Features

- ✅ **Task Management** — Create, edit, delete, and organize tasks
- 🎯 **Priority System** — High, Medium, and Low priority levels with color coding
- 📊 **Real-time Dashboard** — Live statistics (Total, Completed, Pending, Overdue, Success Rate)
- 🔍 **Smart Search** — Real-time filtering by title and description
- 🏷️ **Advanced Filtering** — Filter by status or priority
- ⚠️ **Overdue Detection** — Automatic flagging of overdue tasks
- 🎨 **Modern UI** — Clean, professional interface with custom components

---

## 🏗️ Architecture
```
src/
├── model/
│   ├── Priority.java              # Enum for type-safe priorities
│   ├── TaskStatus.java            # Enum for task states
│   ├── FilterType.java            # Enum for filter options
│   ├── NotificationType.java      # Enum for notification types
│   ├── Task.java                  # Core entity with validation
│   └── TaskStatistics.java        # Data transfer object
├── service/
│   ├── TaskService.java           # Business logic interface
│   └── TaskServiceImpl.java       # Implementation with streams
├── controller/
│   └── TaskController.java        # MVC controller interface
├── view/
│   ├── components/
│   │   ├── RoundedButton.java     # Reusable UI component
│   │   ├── TaskCard.java          # Custom task component
│   │   └── CheckBoxIcon.java      # Custom checkbox renderer
│   └── ProfessionalTaskManager.java  # Main JFrame application
└── Main.java                      # Application entry point
```

### Design Patterns Used

| Pattern | Implementation |
|---------|---------------|
| **MVC** | Separation of Model, View, and Controller |
| **Service Layer** | Interface-based business logic |
| **Observer** | Event-driven UI updates |
| **Factory** | Dialog creation for add/edit operations |

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/task-manager-pro.git
   cd task-manager-pro
   ```

2. **Compile the application**
   ```bash
   javac -d out src/Main.java src/model/*.java src/service/*.java src/controller/*.java src/view/components/*.java src/view/*.java
   ```

3. **Run the application**
   ```bash
   java -cp out Main
   ```

### IDE Setup (IntelliJ IDEA / Eclipse)

1. Open the project folder in your IDE
2. Mark `src` as Sources Root (Right-click → Mark Directory as → Sources Root)
3. Run `Main.java` (contains `main` method)

---

## 📖 Usage Guide

### Adding a Task
1. Click the **"+ New Task"** button in the top right
2. Fill in the title (required) and description (optional)
3. Select priority level: 🔴 High, 🟡 Medium, or 🟢 Low
4. Click **"Create Task"**

### Managing Tasks
| Action | How To |
|--------|--------|
| Mark Complete | Click the checkbox on the task card |
| Edit Task | Click the ✏️ icon |
| Delete Task | Click the 🗑️ icon and confirm |
| Search | Type in the search bar (top right) |
| Filter | Use the dropdown to filter by category |

### Understanding the Dashboard

| Statistic | Description |
|-----------|-------------|
| **Total Tasks** | All tasks in the system |
| **Completed** | Tasks marked as done |
| **Pending** | Tasks awaiting completion |
| **Overdue** | Pending tasks older than 7 days |
| **Success Rate** | Percentage of completed tasks |

---

## 🛠️ Technical Stack

| Technology | Purpose |
|------------|---------|
| **Java 17** | Core language |
| **Java Swing** | Desktop GUI framework |
| **AWT** | Low-level graphics and events |
| **Java Time API** | Date handling (`LocalDate`) |
| **Java Streams** | Functional data operations |

---

## 🎓 Key Concepts Demonstrated

### Object-Oriented Programming
- **Encapsulation** — Private fields with controlled access
- **Inheritance** — Extending `JPanel`, `JFrame`, `JButton`
- **Polymorphism** — Interface-based service layer
- **Abstraction** — `TaskService` interface hides implementation

### Data Structures
- `ArrayList<Task>` — Dynamic task storage
- Java 8 Streams — Filtering, mapping, collecting
- Enums — Type-safe constants (`Priority`, `TaskStatus`)

### Conditional Logic
- Input validation with exception handling
- Priority-based color coding
- Status-dependent UI rendering
- Overdue date calculations

---

## 📝 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

> 💡 **Note:** This project was developed as part of an internship program to demonstrate proficiency in Java OOP, Swing GUI development, and software architecture principles.
```
