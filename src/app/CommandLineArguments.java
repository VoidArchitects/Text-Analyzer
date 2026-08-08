package app;
public class CommandLineArguments {
    private String path;
    private int k;

    public CommandLineArguments(String path, int k) {
    this.path = path;
    this.k = k;
    }

    public static CommandLineArguments parse(String[] args){
        if(args.length < 3){
            throw new IllegalArgumentException(
                "Usage: <file-path> --top <number>"
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
        return new CommandLineArguments(path, k);
    }

    public String getPath(){
        return path;
    }

    public int getK(){
        return k;
    }
}
