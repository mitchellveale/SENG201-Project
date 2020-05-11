import javax.swing.*;
import java.awt.*;

public class GraphicalGame {

    private static final String windowTitle = "Shitty farm game";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static int width;
    private static int height;
    private static double scale;

    private static JFrame frame;
    private static JLayeredPane pane;

    public static Resources resources;

    private static JPanel activePanel;

    public static void startGame(double scale){
        GraphicalGame.scale = scale;
        width = (int)(WIDTH * scale);
        height = (int)(HEIGHT * scale);

        resources = new Resources();

        initializeFrame();
        initializePanels();

        //pane.add(SetupScreen.getPanel());
        //activePanel = SetupScreen.getPanel();

        // TESTING
        Farm.createFarm(7, "Bob's Farm", "Bob", FarmType.SUBSIDISED_FARM);
        pane.add(MainScreen.getPanel());
        pane.moveToBack(MainScreen.getPanel());
        MainScreen.update();
        activePanel = MainScreen.getPanel();

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

    public static void begin(){
        pane.remove(SetupScreen.getPanel());

        pane.add(MainScreen.getPanel());
        pane.moveToBack(MainScreen.getPanel());
        MainScreen.update();
        setActivePanel(MainScreen.getPanel());
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

    public static Rectangle scaled(int x, int y, int width, int height){
        return new Rectangle(scaled(x), scaled(y), scaled(width), scaled(height));
    }

    public static void centerBounds(Component component, int x, int y, int width, int height){
        int newx = x - (width / 2);
        int newy = y - (height / 2);
        component.setBounds(newx, newy, width, height);
    }

    public static void deletePanel(JPanel panel, JPanel newActive){
        panel.setVisible(false);
        pane.remove(panel);
        setActivePanel(newActive);
    }

    public static void addPanel(JPanel panel, JPanel previousPanel){
        pane.add(panel);
        pane.moveToFront(panel);
        setActivePanel(panel);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static JPanel getActivePanel() {
        return activePanel;
    }

    public static void setActivePanel(JPanel activePanel) {
        GraphicalGame.activePanel = activePanel;
    }
}
