# Text Analyzer CLI

A command-line interface tool written in Java for analyzing text files, counting word frequencies, line counts, character counts, and generating readability statistics.

## Project Structure

```
text-analyzer-cli/
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── io/
│   │   └── FileLoader.java
│   ├── analyzer/
│   │   └── TextAnalyzer.java
│   └── model/
│       └── AnalysisResult.java
├── test-files/
│   └── sample.txt
├── README.md
└── .gitignore
```

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher.

### Compilation
From the project root directory, compile the Java source files:
```bash
javac -d out src/app/Main.java src/io/FileLoader.java src/analyzer/TextAnalyzer.java src/model/AnalysisResult.java
```

### Running the Application
```bash
java -cp out app.Main test-files/sample.txt
```
