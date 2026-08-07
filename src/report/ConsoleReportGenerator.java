package report;
import java.util.Map;
import model.AnalysisResult;

public class ConsoleReportGenerator {
    
    public void generate(AnalysisResult result){
        Map<Character, Integer> map = result.getCharacterFrequency();
        Map<String, Integer> wordMap = result.getWordsFrequency();
        System.out.println(
            """
                ====================
                       REPORT
                ====================
                  1. Characters : %d
                  2. Words      : %d
                  3. Lines      : %d  

            """.formatted
            (
                result.getCharacterCount(),
                result.getWordCount(),
                result.getLineCount()
            )
        );
        System.out.println(
            """
                ---------------------
                CHARACTER FREQUENCIES
                ---------------------
                    
            """
        );
        StringBuilder sb = new StringBuilder();
        map.forEach((key, val) -> {
            // Convert problematic characters to readable labels
            String readableKey = switch (key) {
                case ' ' -> "[WhiteSpace]";
                case '\n' -> "[Newline]";
                case '\t' -> "[Tab]";
                case '\r' -> "[Carriage Return]";
                default -> String.valueOf(key); 
            };
            String formattedLine = "%-12s : %d\n".formatted(readableKey, val);
            sb.append(formattedLine);   
        });
        System.out.println(sb);

        System.out.println(
            """
                ---------------------
                WORD FREQUENCIES
                ---------------------
                    
            """
        );
        StringBuilder sbWord = new StringBuilder();
        wordMap.forEach((key, val) -> {
            String formattedLine = "%-12s : %d\n".formatted(key, val);
            sbWord.append(formattedLine);   
        });
        System.out.println(sbWord);
    }
}
