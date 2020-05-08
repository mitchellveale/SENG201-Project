import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;
import javax.imageio.ImageIO;

public class GraphicalGame {

    private static String windowTitle = "Shitty farm game";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static int width;
    private static int height;
    private static double scale;

    private static JFrame frame;

    public static Resources resources;

    public static void startGame(double scale){
        GraphicalGame.scale = scale;
        width = (int)(WIDTH * scale);
        height = (int)(HEIGHT * scale);

        resources = new Resources();

        initializeFrame();

        SetupScreen.createSetupScreen();
        frame.getContentPane().add(SetupScreen.getPanel());

        frame.setVisible(true);

    }

    private static void initializeFrame(){
        frame = new JFrame(windowTitle);
        frame.pack();
        Insets insets = frame.getInsets();
        frame.setBounds(0, 0, width + insets.left + insets.right, height + insets.top + insets.bottom);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
    }

    public static void begin(){
        SetupScreen.getPanel().setVisible(false);
        frame.getContentPane().remove(SetupScreen.getPanel());
        // TODO: create the main panel
    }

    public static Font sizedFont(float size){
        return resources.font.deriveFont(size);
    }

    public static int scaled(int value){
        return (int)(value * scale);
    }

    public static float scaled(float value){
        return (float)(scaled((int) value));
    }

    public static void centerBounds(Component component, int x, int y, int width, int height){
        int newx = x - (width / 2);
        int newy = y - (height / 2);
        component.setBounds(newx, newy, width, height);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
