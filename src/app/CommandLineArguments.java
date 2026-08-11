package app;
public class CommandLineArguments {
    private String path;
    private int k;
    private boolean ignoreCase;

    public CommandLineArguments(String path, int k, boolean ignoreCase) {
    this.path = path;
    this.k = k;
    this.ignoreCase = ignoreCase;
    }

    public static CommandLineArguments parse(String[] args){
        if(args.length < 3){
            throw new IllegalArgumentException(
                "Usage: <file-path> --top <number> --ignore-case"
            );
        }
        String path = args[0];
        String option = args[1];
        if(!"--top".equals(option)) throw new IllegalArgumentException("Expected --top");
        int k;
        try{
            k = Integer.parseInt(args[2]);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Top k must be a number");
        }
        if(k<=0){
            throw new IllegalArgumentException(
                "k should be a natural number, i.e, 1,2,3,4........."
            );
        }
        boolean localIgnoreCase = false;
        if(args.length > 3 &&  args[3].equalsIgnoreCase("--ignore-case")){
            localIgnoreCase = true;
        }
        return new CommandLineArguments(path, k, localIgnoreCase);
    }

    public String getPath(){
        return path;
    }

    public int getK(){
        return k;
    }

    public boolean getIgnoreCase(){
        return ignoreCase;
    }
}
