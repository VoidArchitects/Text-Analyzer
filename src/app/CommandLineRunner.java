package app;
import analyzer.TextAnalyzer;
import model.AnalysisResult;
public class CommandLineRunner {
    
    private TextAnalyzer analyzer = new TextAnalyzer();
    public void start(){
        AnalysisResult result = analyzer.analyze("Hello, World!");
        System.out.println("Character Count: " + result.getCharacterCount());
        
    }
}
