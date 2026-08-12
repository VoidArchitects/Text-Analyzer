$ErrorActionPreference = "Stop"

Write-Host "Compiling..."

javac -d out `
    src/app/Main.java `
    src/app/CommandLineRunner.java `
    src/io/TextFileReader.java `
    src/analyzer/TextAnalyzer.java `
    src/model/AnalysisResult.java `
    src/report/ConsoleReportGenerator.java `
    src/app/CommandLineArguments.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    exit 1
}

Write-Host "Running Text Analyzer on all test files..." -ForegroundColor Green

$testFiles = Get-ChildItem -Path test-files -Filter *.txt |
    ForEach-Object { $_.FullName }

# java -cp out app.Main $testFiles --top 5 --ignore-case
java -cp out app.Main test-files/large.txt --top 5 --ignore-case