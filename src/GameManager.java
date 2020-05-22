/**
 * Highest level of control for the game.
 * Decides whether to run GUI or CLI
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class GameManager {
    public static void main(String[] args) {
        //We will probably want to default to using the gui when it is implemented
        boolean gui = true; // Make this default to true when the GUI has been implemented
        double scale = 1;
        for (int i = 0; i < args.length; i+=2){
            try {
                switch (args[i]) {
                    case "-gui":
                        gui = Boolean.parseBoolean(args[i + 1]);
                        break;
                    case "-scale":
                        scale = Double.parseDouble(args[i + 1]);
                }
            }catch (Exception e){
                if (args.length > i + 1)
                    System.out.println("Invalid value of '" + args[i+1] + "' for the flag '" + args[i] + "'");
                else
                    System.out.println("No value given for flag '" + args[i] + "'");
                return;
            }
        }
        if (gui)
            GUIGame.startGame(scale);
        else
            TextGame.startGame();
    }
}
