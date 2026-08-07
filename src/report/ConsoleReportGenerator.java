package report;
import java.util.Map;
import model.AnalysisResult;

public class ConsoleReportGenerator {
    
    public void generate(AnalysisResult result){
        Map<Character, Integer> map = result.getCharacterFrequency();
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
            "And the character frequencies are as follows"
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
    }
}
