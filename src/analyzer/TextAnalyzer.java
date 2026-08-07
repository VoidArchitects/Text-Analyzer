package analyzer;
import model.AnalysisResult;

public class TextAnalyzer {
    
    public AnalysisResult analyze(String text) {
        AnalysisResult result = new AnalysisResult();
        result.setCharacterCount(text.length());
        return result;
    }
}
