# Text File Analyzer (Internship Task 5)

A robust Java-based desktop application developed as part of a software engineering internship. This utility provides comprehensive analysis of plain text files, offering statistical insights and search functionality through a user-friendly graphical interface (GUI) built with Java Swing.

## 🚀 Features

*   **Interactive File Selection:** Uses `JFileChooser` to allow users to browse and select files directly from their system.
*   **Real-time Statistics:**
    *   **Total Lines:** Accurately counts every newline break in the document.
    *   **Total Words:** Identifies words using whitespace-based tokenization (Regex).
    *   **Total Characters:** Calculates the total character count, providing a detailed scope of the file size.
*   **Advanced Word Search:**
    *   Case-insensitive matching.
    *   Automatic punctuation stripping (e.g., "Hello!" will match a search for "hello").
    *   Dynamic "Search / Refresh" button to update results without re-opening the file.
*   **Error Handling:** Integrated UI alerts to notify users of file reading errors or invalid selections.

## 🛠️ Technical Skills Demonstrated

*   **File Input/Output:** Implementation of `BufferedReader` and `FileReader` for memory-efficient text processing.
*   **Exception Handling:** Robust use of `try-with-resources` to ensure file streams are closed automatically and `IOExceptions` are handled gracefully.
*   **String Manipulation:** Utilization of Regular Expressions (Regex) for sophisticated word splitting (`\\s+`) and character filtering (`[^a-zA-Z]`).
*   **GUI Development:** Managing complex layouts using `BorderLayout`, `BoxLayout`, and `FlowLayout` to create a responsive desktop experience.

## 📂 Installation & Usage

### Prerequisites
*   Java Development Kit (JDK) 8 or higher.
*   A plain text editor for creating test data.

### Running the Application
1. **Clone the repository:**
   ```bash
   git clone https://github.com/hewageumesha/Text-File-Analyzer.git
   ```
2. **Compile the source code:**
   ```bash
   javac TextFileAnalyzer.java
   ```
3. **Launch the application:**
   ```bash
   java TextFileAnalyzer
   ```

## ⚠️ Important Note on File Formats
This application is optimized for **Plain Text (.txt)** files.

> [!IMPORTANT]
> Opening binary files such as **.pdf** or **.docx** will result in distorted statistics. These formats contain complex metadata and encoding that standard character-stream readers cannot interpret as plain text. For accurate results, please ensure you are using a standard `.txt` file.

## 📝 License
Distributed under the MIT License. See `LICENSE` for more information.

