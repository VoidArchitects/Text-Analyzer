$ErrorActionPreference = "Stop"

Write-Host "Compiling..."

javac -d out `
    src/app/Main.java `
    src/app/CommandLineRunner.java `
    src/io/TextFileReader.java `
    src/analyzer/TextAnalyzer.java `
    src/model/AnalysisResult.java `
    src/report/ConsoleReportGenerator.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    exit 1
}

Write-Host "Running Text Analyzer..." -ForegroundColor Green

java -cp out app.Main