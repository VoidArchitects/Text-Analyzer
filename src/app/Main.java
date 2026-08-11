package app;
public class Main {
    public static void main(String[] args) {
        CommandLineRunner clr = new CommandLineRunner();
        try{
            CommandLineArguments arguments = CommandLineArguments.parse(args);
            System.out.println(arguments.getPath());
            System.out.println(arguments.getK());
            System.out.println(arguments.getIgnoreCase());
            clr.start(arguments);     
        }catch(IllegalArgumentException e){
            System.err.println("Error : " + e.getMessage());
        }
    }
}