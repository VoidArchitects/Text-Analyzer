package report;

import model.AnalysisResult;

import java.util.Map;

public class ConsoleReportGenerator {

    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";

    private static final int WIDTH = 62;
    private static final int CONTENT_WIDTH = WIDTH - 2;

    public void generate(AnalysisResult result) {
        printHeader("TEXT ANALYSIS REPORT");
        printSummary(result);
        printFrequencySection("CHARACTER FREQUENCIES", result.getCharacterFrequency(), true);
        printFrequencySection("WORD FREQUENCIES", result.getWordsFrequency(), false);
        printTopSection("TOP " + result.getTopKWordsFrequency().size() + " WORDS", result.getTopKWordsFrequency(), false);
        printTopSection("TOP " + result.getTopKCharacterFrequency().size() + " CHARACTERS", result.getTopKCharacterFrequency(), true);
        printFooter();
    }

    // ============================================================
    // SUMMARY
    // ============================================================

    private void printSummary(AnalysisResult result) {
        printSectionHeader("SUMMARY");
        printStat("Characters", result.getCharacterCount());
        printStat("Words", result.getWordCount());
        printStat("Lines", result.getLineCount());
        printStat("Letters", result.getletterCount());
        printStat("Digits", result.getdigitCount());
        printStat("Whitespace", result.getWhiteSpaceCount());
        printStat("Symbols", result.getSymbolCount());
        printSectionFooter();
    }

    private void printStat(String label, int value) {
        String content = "%-18s : ".formatted(label);
        printColoredRow(content, GREEN + value + RESET);
    }

    // ============================================================
    // FREQUENCY SECTIONS
    // ============================================================

    private <T> void printFrequencySection(String title, Map<T, Integer> frequencyMap, boolean characterMode) {
        printSectionHeader(title);

        if (frequencyMap.isEmpty()) {
            printRow("No data available.");
            printSectionFooter();
            return;
        }

        int maxKeyWidth = getMaxKeyWidth(frequencyMap, characterMode);

        frequencyMap.forEach((key, value) -> {
            String displayKey = formatKey(key, characterMode);
            String content = String.format("%-" + (maxKeyWidth + 2) + "s : %d", displayKey, value);
            printRow(content, GREEN, String.valueOf(value));
        });

        printSectionFooter();
    }

    // ============================================================
    // TOP K
    // ============================================================

    private <T> void printTopSection(String title, Map<T, Integer> frequencyMap, boolean characterMode) {
        printSectionHeader(title);

        if (frequencyMap.isEmpty()) {
            printRow("No data available.");
            printSectionFooter();
            return;
        }

        int rank = 1;
        int maxKeyWidth = getMaxKeyWidth(frequencyMap, characterMode);

        for (Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
            String displayKey = formatKey(entry.getKey(), characterMode);
            String content = String.format("%-3d %-" + maxKeyWidth + "s : %d", rank++, displayKey, entry.getValue());
            printRow(content, GREEN, String.valueOf(entry.getValue()));
        }

        printSectionFooter();
    }

    // ============================================================
    // ROW FORMATTING
    // ============================================================

    private void printRow(String content) {
        String visibleContent = stripAnsi(content);

        if (visibleContent.length() > CONTENT_WIDTH) {
            visibleContent = visibleContent.substring(0, CONTENT_WIDTH);
            content = visibleContent;
        }

        int padding = CONTENT_WIDTH - visibleContent.length() + 1;

        System.out.print("║ ");
        System.out.print(content);
        System.out.print(" ".repeat(Math.max(0, padding)));
        System.out.println("║");
    }

    private void printRow(String content, String valueColor, String value) {
        if (valueColor != null && value != null) {
            int valueIndex = content.lastIndexOf(value);
            if (valueIndex >= 0) {
                String before = content.substring(0, valueIndex);
                String after = content.substring(valueIndex + value.length());
                content = before + valueColor + value + RESET + after;
            }
        }

        printRow(content);
    }

    private void printColoredRow(String content, String coloredValue) {
        printRow(content + coloredValue);
    }

    private String stripAnsi(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    // ============================================================
    // FORMATTING
    // ============================================================

    private <T> int getMaxKeyWidth(Map<T, Integer> map, boolean characterMode) {
        return map.keySet()
            .stream()
            .map(key -> formatKey(key, characterMode).length())
            .max(Integer::compareTo)
            .orElse(1);
    }

    private String formatKey(Object key, boolean characterMode) {
        if (!characterMode) {
            return String.valueOf(key);
        }

        char ch = (Character) key;

        return switch (ch) {
            case ' ' -> "[SPACE]";
            case '\n' -> "[NEWLINE]";
            case '\t' -> "[TAB]";
            case '\r' -> "[CARRIAGE RETURN]";
            case '\f' -> "[FORM FEED]";
            case '\b' -> "[BACKSPACE]";
            default -> "'" + ch + "'";
        };
    }

    // ============================================================
    // BOX DRAWING
    // ============================================================

    private void printHeader(String title) {
        System.out.println();
        System.out.println(CYAN + "╔" + "═".repeat(WIDTH) + "╗" + RESET);
        printRow(CYAN + center(title, CONTENT_WIDTH) + RESET);
        System.out.println(CYAN + "╠" + "═".repeat(WIDTH) + "╣" + RESET);
    }

    private void printSectionHeader(String title) {
        printRow(BOLD + title + RESET);
        System.out.println("╠" + "─".repeat(WIDTH) + "╣");
    }

    private void printSectionFooter() {
        System.out.println("╠" + "─".repeat(WIDTH) + "╣");
    }

    private void printFooter() {
        System.out.println(CYAN + "╚" + "═".repeat(WIDTH) + "╝" + RESET);
        System.out.println();
    }

    private String center(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }

        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }
}