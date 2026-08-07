package analyzer;
import model.AnalysisResult;

import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {
    
    public AnalysisResult analyze(String text) {
        return new AnalysisResult(
            countCharacters(text),
            countWords(text),
            countLines(text),
            countCharacterFrequency(text)
        );
    }

    //=====helpers======

    private int countCharacters(String text){
        return text.length();
    }
    private int countWords(String text){
        if(text.isBlank()) return 0;
        return (text.split("\\s+").length);
    }
    private int countLines(String text){
        if(text.isBlank()) return 0;
        int lineCount = 1;
        for(int i = 0 ; i < text.length() ; i++){
            if(text.charAt(i) == '\n') lineCount++;
        }
        return lineCount;
    }
    private Map<Character, Integer> countCharacterFrequency(String text){
        if(text.isBlank()) return new HashMap<>();
        text = text.toLowerCase();
        Map<Character,Integer> frequency = new HashMap<>();
        for (char ch : text.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        return frequency;
    }
}
