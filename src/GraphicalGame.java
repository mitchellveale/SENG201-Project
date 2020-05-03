import javax.swing.*;

public class GraphicalGame {

    private static String windowTitle = "Shitty farm game";
    private static int width = 800;
    private static int height = 600;
    private static double scale;

    private static JFrame frame;

    public static void startGame(double scale){
        GraphicalGame.scale = scale;
        frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int)(width * scale), (int)(height * scale));

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
