package app;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
public class CommandLineArguments {
    private List<Path> paths;
    private int k;
    private boolean ignoreCase;

    public CommandLineArguments(List<Path> paths, int k, boolean ignoreCase) {
        this.paths = paths;
        this.k = k;
        this.ignoreCase = ignoreCase;
    }
    public CommandLineArguments(List<Path> paths, boolean ignoreCase) {
        this.paths = paths;
        this.ignoreCase = ignoreCase;
        this.k = Integer.MAX_VALUE;
    }

    public static CommandLineArguments parse(String[] args){
        int k = Integer.MAX_VALUE;
        boolean topFlagFound = false;
        boolean localIgnoreCase = false;
        List<Path> pathList = new ArrayList<>();
        for(int i = 0 ; i < args.length ; i++){
            switch (args[i]){
                case "--top" -> {
                    if(i+1 >= args.length){
                        throw new IllegalArgumentException("Buddy wheres k? --top what?");
                    }
                    try{
                        k = Integer.parseInt(args[++i]);
                    }catch(Exception e){
                        throw new IllegalArgumentException("k should be a valid integer");
                    }
                    if(k <= 0){
                        throw new IllegalArgumentException("k should be positive bruh");
                    }
                    topFlagFound = true;
                }
                case "--ignore-case" -> {
                    localIgnoreCase = true;    
                }
                default -> {
                    if(args[i].startsWith("-")){
                        throw new IllegalArgumentException("File path buddy?");
                    }
                    pathList.add(Path.of(args[i]));   
                }
            }
        }
        if(pathList.isEmpty()) throw new IllegalArgumentException("File path is missing");
        if(topFlagFound && k != Integer.MAX_VALUE){
            return new CommandLineArguments(pathList, k, localIgnoreCase);
        }
        return new CommandLineArguments(pathList, localIgnoreCase);
    }

    public List<Path> getPath(){
        return paths;
    }

    public int getK(){
        return k;
    }

    public boolean getIgnoreCase(){
        return ignoreCase;
    }
}
