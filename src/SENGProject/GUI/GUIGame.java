package SENGProject.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Manages the GUI Window and what to display
 */
public class GUIGame {

    private static final String windowTitle = "SENG 201 Project";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static int width;
    private static int height;
    private static double scale;

    private static JFrame frame;
    private static JLayeredPane pane;

    public static Resources resources;

    private static JPanel activePanel;

    /**
     * Starts the game
     * @param scale A double that scales the size of the window
     */
    public static void startGame(double scale){
        GUIGame.scale = scale;
        width = (int)(WIDTH * scale);
        height = (int)(HEIGHT * scale);

        resources = new Resources();

        initializeFrame();
        initializePanels();

        pane.add(SetupScreen.getPanel());
        activePanel = SetupScreen.getPanel();

        frame.setVisible(true);

    }

    private static void initializeFrame(){
        frame = new JFrame(windowTitle);
        frame.pack();
        Insets insets = frame.getInsets();
        frame.setBounds(0, 0, width + insets.left + insets.right, height + insets.top + insets.bottom);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        pane = new JLayeredPane();
        pane.setBounds(0, 0, width, height);
        pane.setLayout(null);
        frame.add(pane);
        frame.setResizable(false);
    }

    private static void initializePanels(){
        SetupScreen.createSetupScreen();
        MainScreen.createMainScreen();
    }

    /**
     * Removes setup screen and activates main screen
     */
    public static void begin(){
        pane.remove(SetupScreen.getPanel());

        pane.add(MainScreen.getPanel());
        pane.moveToBack(MainScreen.getPanel());
        MainScreen.update();
        setActivePanel(MainScreen.getPanel());
    }

    /**
     * @return The scaled font
     */
    public static Font sizedFont(float size){
        return resources.font.deriveFont(scaled(size) / 1.69f);
    }

    /**
     * @return An int of the scaled value 
     */
    public static int scaled(int value){
        return (int)(value * scale);
    }

    /**
     * @return A float of the scaled value 
     */
    public static float scaled(float value){
        return (float)(scaled((int) value));
    }

    /**
     * @return A scaled rectangle 
     */
    public static Rectangle scaled(int x, int y, int width, int height){
        return new Rectangle(scaled(x), scaled(y), scaled(width), scaled(height));
    }

    /**
     * removes a panel and sets the next active panel
     */
    public static void deletePanel(JPanel panel, JPanel newActive){
        panel.setVisible(false);
        pane.remove(panel);
        setActivePanel(newActive);
    }

    /**
     * Adds a panel and sets it to active 
     */
    public static void addPanel(JPanel panel){
        pane.add(panel);
        pane.moveToFront(panel);
        setActivePanel(panel);
    }

    /**
     * @return An int of the width
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return An int of the height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return the active panel object
     */
    public static JPanel getActivePanel() {
        return activePanel;
    }

    /**
     * Sets the active panel to the parameter
     */
    public static void setActivePanel(JPanel activePanel) {
        GUIGame.activePanel = activePanel;
    }
}
