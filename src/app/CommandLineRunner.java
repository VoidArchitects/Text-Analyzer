package app;
import java.io.IOException;
import java.util.Scanner;

import analyzer.TextAnalyzer;
import io.TextFileReader;
import model.AnalysisResult;
public class CommandLineRunner {
    
    private TextAnalyzer analyzer = new TextAnalyzer();
    private TextFileReader reader = new TextFileReader();
    public void start(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String path = sc.nextLine();
        try{
            AnalysisResult result = analyzer.analyze(reader.read(path));
            System.out.println("Character Count: " + result.getCharacterCount());
        }catch(IOException e){
            System.out.println("Couldn't read file because " + e.getMessage());
        }
        sc.close();
    }
}
