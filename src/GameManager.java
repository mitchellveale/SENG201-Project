public class GameManager {
    public static void main(String[] args) {
        //We will probably want to default to using the gui when it is implemented
        boolean gui = false; // Make this default to true when the GUI has been implemented
        double scale = 1;
        //TODO: Make this check a bit less shit
        try {
            if (args.length == 2 || args.length == 4) {
                if (args[0].toLowerCase().equals("-gui") && args[1].toLowerCase().equals("true"))
                    gui = true;
                if (args.length == 4) {
                    if (args[2].toLowerCase().equals("-scale")) {
                        scale = Double.parseDouble(args[3]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid arguments");
            return;
        }
        if (gui)
            GraphicalGame.startGame(scale);
        else
            TextGame.startGame();
    }
}
