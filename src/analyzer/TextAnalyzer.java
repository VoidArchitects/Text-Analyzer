package analyzer;
import model.AnalysisResult;

public class TextAnalyzer {
    
    public AnalysisResult analyze(String text) {
        AnalysisResult result = new AnalysisResult();
        result.setCharacterCount(analyzeCharCount(text));
        result.setWordCount(analyzeWordCount(text));
        result.setLineCount(analyzeLineCount(text));
        return result;
    }

    //=====helpers======

    private int analyzeCharCount(String text){
        if(text.isBlank()) return 0;
        return text.length();
    }
    private int analyzeWordCount(String text){
        if(text.isBlank()) return 0;
        return (text.split("\\s+").length);
    }
    private int analyzeLineCount(String text){
        if(text.isBlank()) return 0;
        int lineCount = 1;
        for(int i = 0 ; i < text.length() ; i++){
            if(text.charAt(i) == '\n') lineCount++;
        }
        return lineCount;
    }
}
