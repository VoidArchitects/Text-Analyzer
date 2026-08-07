package model;
import java.util.Map;
public class AnalysisResult {
    private int characterCount;
    private int wordCount;
    private int lineCount;
    private int letterCount;
    private int digitCount;
    private int whiteSpaceCount;
    private int symbolCount;
    private Map<Character, Integer> characterFrequency;

    public AnalysisResult(int characterCount,
        int wordCount,
        int lineCount,
        int letterCount,
        int digitCount,
        int whiteSpaceCount,
        int symbolCount,
        Map<Character, Integer> characterFrequency
    ){
        this.characterCount = characterCount;
        this.wordCount = wordCount;
        this.lineCount = lineCount;
        this.letterCount = letterCount;
        this.digitCount = digitCount;
        this.whiteSpaceCount = whiteSpaceCount;
        this.symbolCount = symbolCount;
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

    public int getletterCount() {
        return letterCount;
    }

    public int getdigitCount() {
        return digitCount;
    }

    public int getWhiteSpaceCount() {
        return whiteSpaceCount;
    } 
    public int getSymbolCount() {
        return symbolCount;
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
    public void setletterCount(int letterCount) {
        this.letterCount = letterCount;
    }

    public void setdigitCount(int digitCount) {
        this.digitCount = digitCount;
    }

    public void setWhiteSpaceCount(int whiteSpaceCount) {
        this.whiteSpaceCount = whiteSpaceCount;
    } 
    public void setSymbolCount(int symbolCount) {
        this.symbolCount = symbolCount;    
    }

    public void setCharacterFrequency(Map<Character, Integer> characterFrequency) {
        this.characterFrequency = characterFrequency;
    }
}   