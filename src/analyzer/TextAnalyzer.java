package analyzer;
import model.AnalysisResult;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {
    private boolean ignoreCase;
    public AnalysisResult analyze(String text, int k, boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return new AnalysisResult(
            countCharacters(text),
            countWords(text),
            countLines(text),
            countLetters(text),
            countDigits(text),
            countWhitespaces(text),
            countSymbols(text),
            countCharacterFrequency(text),
            countWordFrequency(text),
            topKCharacters(countCharacterFrequency(text), k),
            topKWords(countWordFrequency(text), k)
        );
    }
    public AnalysisResult analyze(String text, boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return new AnalysisResult(
            countCharacters(text),
            countWords(text),
            countLines(text),
            countLetters(text),
            countDigits(text),
            countWhitespaces(text),
            countSymbols(text),
            countCharacterFrequency(text),
            countWordFrequency(text)
        );
    }

    //=====helpers======

    private int countCharacters(String text){
        return text.length();
    }
    private int countWords(String text){
        if(text.isBlank()) return 0;
        return (text.trim().split("\\s+").length);
    }
    private int countLines(String text){
        if(text.isBlank()) return 0;
        int lineCount = 1;
        for(int i = 0 ; i < text.length() ; i++){
            if(text.charAt(i) == '\n') lineCount++;
        }
        return lineCount;
    }
    private int countLetters(String text){
        if(text.isBlank()) return 0;
        int letterCount = 0;
        for(int i = 0 ; i < text.length() ; i++){
            if(Character.isLetter(text.charAt(i))) letterCount++;
        }
        return letterCount;
    }
    private int countDigits(String text){
        if(text.isBlank()) return 0;
        int digitCount = 0;
        for(int i = 0 ; i < text.length() ; i++){
            if(Character.isDigit(text.charAt(i))) digitCount++;
        }
        return digitCount;
    }
    private int countWhitespaces(String text){
        if(text.isBlank()) return 0;
        int whiteSpaceCount = 0;
        for(int i = 0 ; i < text.length() ; i++){
            if(Character.isWhitespace(text.charAt(i))) whiteSpaceCount++;
        }
        return whiteSpaceCount;
    }
    private int countSymbols(String text){
        if(text.isBlank()) return 0;
        int symbolCount = 0;
        for (int i = 0; i < text.length(); i++) {
        if (isJavaSymbol(text.charAt(i))) {
            symbolCount++;
        }
    }
        return symbolCount;
    }
    private Map<Character, Integer> countCharacterFrequency(String text){
        if(text.isBlank()) return new HashMap<>();
        if(ignoreCase) text = text.toLowerCase();
        Map<Character,Integer> characterFrequency = new HashMap<>();
        for (char ch : text.toCharArray()) {
            characterFrequency.put(ch, characterFrequency.getOrDefault(ch, 0) + 1);
        }
        return characterFrequency;
    }
    private Map<String, Integer> countWordFrequency(String text){
        if(text.isBlank()) return new HashMap<>();
        if(ignoreCase) text = text.toLowerCase();
        text = text.replaceAll("[^\\p{L}\\p{N}]"," ");
        Map<String,Integer> wordFrequency = new HashMap<>();
        String[] words = text.split("\\s+");
        for(String word : words){
            if(word.isEmpty()) continue;
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency;
    }
    private Map<String, Integer> topKWords(Map<String, Integer> map, int k){
        if(map.isEmpty() || k <= 0) return new LinkedHashMap<>();
        return map.entrySet().stream()
        .sorted(Comparator.comparing(Map.Entry<String, Integer> :: getValue).reversed().thenComparing(Map.Entry<String, Integer> :: getKey))
        .limit(k)
        .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (a,b) -> a, LinkedHashMap :: new));
    }
    private Map<Character, Integer> topKCharacters(Map<Character, Integer> map, int k){
        if(map.isEmpty() || k <= 0) return new LinkedHashMap<>();
        return map.entrySet().stream()
        .sorted(Comparator.comparing(Map.Entry<Character, Integer> :: getValue).reversed().thenComparing(Map.Entry<Character, Integer> :: getKey))
        .limit(k)
        .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue, (a,b) -> a, LinkedHashMap :: new));
    }

    private boolean isJavaSymbol(char ch) {
        int type = Character.getType(ch);
        return type == Character.MATH_SYMBOL || 
            type == Character.CURRENCY_SYMBOL || 
            type == Character.MODIFIER_SYMBOL || 
            type == Character.OTHER_SYMBOL;
    }
}
