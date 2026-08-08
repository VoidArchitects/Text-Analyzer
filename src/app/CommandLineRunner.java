package app;
import java.io.IOException;
import java.util.Scanner;

import analyzer.TextAnalyzer;
import io.TextFileReader;
import model.AnalysisResult;
import report.ConsoleReportGenerator;
public class CommandLineRunner {
    
    private TextAnalyzer analyzer = new TextAnalyzer();
    private TextFileReader reader = new TextFileReader();
    private ConsoleReportGenerator reporter = new ConsoleReportGenerator();
    public void start(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String path = sc.nextLine();
        System.out.print("Enter top k ");
        int k = sc.nextInt();
        try{
            AnalysisResult result = analyzer.analyze(reader.read(path), k);
            reporter.generate(result);

        }catch(IOException e){
            System.out.println("Couldn't read file because " + e.getMessage());
        }
        sc.close();
    }
}
