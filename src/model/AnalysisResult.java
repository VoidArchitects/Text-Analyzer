package model;
import java.util.Map;
public class AnalysisResult {
    private int characterCount;
    private int wordCount;
    private int lineCount;
    private Map<Character, Integer> characterFrequency;

    public AnalysisResult(int characterCount,
        int wordCount,
        int lineCount,
        Map<Character, Integer> characterFrequency
    ){
        this.characterCount = characterCount;
        this.wordCount = wordCount;
        this.lineCount = lineCount;
        this.characterFrequency = Map.copyOf(characterFrequency);
    }
    //=====================GETTERS===================
    public int getCharacterCount() {
        return characterCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public Map<Character, Integer> getCharacterFrequency() {
        return Map.copyOf(characterFrequency);
    }

    //======================SETTERS====================
    public void setCharacterCount(int characterCount) {
        this.characterCount = characterCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public void setCharacterFrequency(Map<Character, Integer> characterFrequency) {
        this.characterFrequency = characterFrequency;
    }
}   