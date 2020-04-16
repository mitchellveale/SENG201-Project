public class Main {
    public static void main(String[] args) {
        //We will probably want to default to using the gui when it is implemented
        boolean gui = false; // Make this default to true when the GUI has been implemented
        //TODO: Make this check a bit less shit
        if (args.length == 2)
            if (args[0].toLowerCase().equals("-gui") && args[1].toLowerCase().equals("false"))
                gui = false;
        if (gui)
            //Run the game with the GUI
            ;
        else
            //Run the game in the command line
            ;
    }
}
