package app;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.nio.file.Path;
import analyzer.TextAnalyzer;
import io.TextFileReader;
import model.AnalysisResult;
import report.ConsoleReportGenerator;
public class CommandLineRunner {
    
    private TextAnalyzer analyzer = new TextAnalyzer();
    private TextFileReader reader = new TextFileReader();
    private ConsoleReportGenerator reporter = new ConsoleReportGenerator();

    public void start(CommandLineArguments argument){
        Map<Path, AnalysisResult> map = new LinkedHashMap<>();
        List<Path> pathList = argument.getPath();
        System.out.println("Searching file(s) at path(s): " + pathList);

        boolean ignoreCase = argument.getIgnoreCase();
        System.out.println("Ignore cases? : " + (ignoreCase? "Yes" : "No"));

        int k = argument.getK();
        if(k != Integer.MAX_VALUE){
            System.out.println("Evaluating top " + k );
        }
        else{
            System.out.println("Analyzing!!!");
        }
        //=========================================needs fixing==================================
        for(Path path : pathList){
            try {
                if(k == Integer.MAX_VALUE){
                    map.put(path, analyzer.analyze(reader.read(path), ignoreCase));
                }
                else{
                    map.put(path, analyzer.analyze(reader.read(path), k, ignoreCase));
                }
            } catch(IOException e) {
                System.err.println("Bad Input for file: " + path);
            }
        }
        for(Map.Entry<Path, AnalysisResult> entry : map.entrySet()){
            System.out.println(">>> File name : " + entry.getKey().getFileName());
            reporter.generate(entry.getValue());
        }
    }
}
