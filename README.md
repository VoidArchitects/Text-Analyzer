# Text Analyzer CLI

A Java-based command-line tool for analyzing one or multiple text files and generating detailed text statistics.

Text Analyzer CLI provides character, word, line, letter, digit, whitespace, symbol, and frequency analysis directly from the command line. It also supports top-K frequency analysis and optional case-insensitive processing.

Built as a modular Java project to practice object-oriented design, file handling, collections, streams, exception handling, command-line argument parsing, and clean separation of responsibilities.

---

## Features

### Text Statistics

For each input file, the analyzer can calculate:

- Character count
- Word count
- Line count
- Letter count
- Digit count
- Whitespace count
- Symbol count

### Frequency Analysis

- Character frequency
- Word frequency
- Top-K most frequent characters
- Top-K most frequent words
- Frequency results are ordered by:
  1. Frequency in descending order
  2. Lexicographical order when frequencies are equal

### Command-Line Options

- Analyze one or multiple files
- `--top <number>` for top-K analysis
- `--ignore-case` for case-insensitive frequency analysis
- Combine multiple options in a single command
- Validate invalid command-line arguments
- Continue analyzing other files when one file cannot be read

---

## Requirements

- Java 15 or higher

The project uses Java features introduced after Java 11, including text blocks.

---

## Project Structure

```text
text-analyzer-cli/
├── src/
│   ├── app/
│   │   ├── Main.java
│   │   ├── CommandLineArguments.java
│   │   └── CommandLineRunner.java
│   ├── analyzer/
│   │   └── TextAnalyzer.java
│   ├── io/
│   │   └── TextFileReader.java
│   ├── model/
│   │   └── AnalysisResult.java
│   └── report/
│       └── ConsoleReportGenerator.java
├── test-files/
│   └── sample.txt
├── README.md
├── run.ps1
└── .gitignore
```

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/VoidArchitects/Text-Analyzer.git
cd text-analyzer-cli
```

### 2. Compile

From the project root:

```bash
javac -d out src/app/*.java src/analyzer/*.java src/io/*.java src/model/*.java src/report/*.java
```

### 3. Run

Analyze a single file:

```bash
java -cp out app.Main test-files/sample.txt
```

---

## Usage

### Analyze a Single File

```bash
java -cp out app.Main test-files/sample.txt
```

This performs a full analysis without limiting the frequency results.

### Top-K Analysis

Use `--top` followed by a positive integer:

```bash
java -cp out app.Main test-files/sample.txt --top 5
```

This limits the character and word frequency sections to the five most frequent entries.

### Case-Insensitive Analysis

Use `--ignore-case`:

```bash
java -cp out app.Main test-files/sample.txt --ignore-case
```

For example, uppercase and lowercase versions of the same letter or word are treated as the same entry during frequency analysis.

### Combine Options

Options can be combined:

```bash
java -cp out app.Main test-files/sample.txt --top 5 --ignore-case
```

This performs a case-insensitive analysis and displays the top five characters and words.

---

## Multiple File Analysis

Multiple file paths can be supplied in the same command:

```bash
java -cp out app.Main test-files/sample.txt test-files/sample2.txt --top 5
```

Each file is:

1. Read independently
2. Analyzed independently
3. Stored with its corresponding analysis result
4. Reported separately

The application maintains the relationship between each file and its analysis using:

```text
Map<Path, AnalysisResult>
```

This allows every generated report to remain associated with the file it belongs to.

---

## Command-Line Syntax

The general command format is:

```text
java -cp out app.Main <file-path> [<file-path> ...] [options]
```

### File Paths

One or more file paths can be supplied.

```text
<file-path> [<file-path> ...]
```

Example:

```bash
java -cp out app.Main file1.txt file2.txt file3.txt
```

### `--top <number>`

Limits the frequency results to the specified number of entries.

```bash
java -cp out app.Main file.txt --top 10
```

The value must be a positive integer.

### `--ignore-case`

Enables case-insensitive frequency analysis.

```bash
java -cp out app.Main file.txt --ignore-case
```

### Combined Example

```bash
java -cp out app.Main file1.txt file2.txt --top 10 --ignore-case
```

---

## Output

The generated report contains sections for:

```text
Character Count
Word Count
Line Count
Letter Count
Digit Count
Whitespace Count
Symbol Count

Character Frequency
Word Frequency

Top K Characters
Top K Words
```

When multiple files are analyzed, each report is labeled with its corresponding file.

Frequency results are deterministic:

```text
Higher frequency
       ↓
Lower frequency
       ↓
Lexicographical order for ties
```

This makes the output predictable and easier to inspect.

---

## Error Handling

The application validates command-line arguments and handles common errors such as:

* Missing file paths
* Missing `--top` value
* Non-numeric top-K values
* Non-positive top-K values
* Unknown command-line options
* Files that cannot be read

When analyzing multiple files, an unreadable file does not prevent the remaining valid files from being analyzed.

---

## Architecture

The application separates responsibilities across several components:

```text
                    Main
                     │
                     ▼
          CommandLineArguments
                     │
                     ▼
           CommandLineRunner
              │      │      │
              ▼      ▼      ▼
      TextFileReader TextAnalyzer
                         │
                         ▼
                  AnalysisResult
                         │
                         ▼
             ConsoleReportGenerator
```

### `CommandLineArguments`

Responsible for parsing and validating command-line arguments.

### `CommandLineRunner`

Coordinates the application flow:

* Receives parsed arguments
* Reads input files
* Runs analysis
* Associates files with their results
* Generates reports

### `TextFileReader`

Responsible for reading text from the provided file paths.

### `TextAnalyzer`

Performs the actual text analysis and frequency calculations.

### `AnalysisResult`

Represents the complete analysis result for a single file.

### `ConsoleReportGenerator`

Formats and prints analysis results to the console.

---

## Design Goals

The project was designed around a few simple principles:

* Keep responsibilities separated
* Keep file handling independent from analysis
* Keep analysis independent from reporting
* Use Java collections appropriately
* Validate user input at the CLI boundary
* Make frequency output deterministic
* Allow multiple files to be processed independently

---

## Version

**V1.0**

V1.0 represents the completed command-line version of Text Analyzer CLI.

The application currently focuses on reliable text analysis through a Java command-line interface. More advanced visualization and comparison features are intentionally outside the scope of this version.

---

## License

This project is intended as a personal learning and portfolio project.
