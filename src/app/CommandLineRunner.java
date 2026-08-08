package app;
import java.io.IOException;

import analyzer.TextAnalyzer;
import io.TextFileReader;
import model.AnalysisResult;
import report.ConsoleReportGenerator;
public class CommandLineRunner {
    
    private TextAnalyzer analyzer = new TextAnalyzer();
    private TextFileReader reader = new TextFileReader();
    private ConsoleReportGenerator reporter = new ConsoleReportGenerator();

    public void start(CommandLineArguments argument){
        String path = argument.getPath();
        System.out.println("Searching file at path: " + path);
        int k = argument.getK();
        System.out.println("Evaluating top " + k );
        try{
            AnalysisResult result = analyzer.analyze(reader.read(path), k);
            reporter.generate(result);
        }catch(IOException e){
            System.out.println("Couldn't read file because " + e.getMessage());
        }
    }
}
