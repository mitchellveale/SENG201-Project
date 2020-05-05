import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicalGame {

    private static String windowTitle = "Shitty farm game";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static int width;
    private static int height;
    private static double scale;

    private static JFrame frame;

    private static JLabel setupBackground;
    private static JLabel borderImage;

    public static void startGame(double scale){
        GraphicalGame.scale = scale;
        width = (int)(WIDTH * scale);
        height = (int)(HEIGHT * scale);

        assignResources();


        frame = new JFrame();
        frame.pack();
        Insets insets = frame.getInsets();
        frame.setBounds(0, 0, width + insets.left + insets.right, height + insets.top + insets.bottom);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);


        enableSetupScreen();

        frame.setVisible(true);

    }

    private static void assignResources(){
        setupBackground = new JLabel(new ImageIcon(scaledImage("res/Setup Background.png")));
        borderImage = new JLabel(new ImageIcon(scaledImage("res/Border.png")));
    }

    private static void enableMainScreen() {
        frame.getContentPane().add(borderImage);
    }

    public static void enableSetupScreen(){
        JLabel title = new JLabel("Create your new farm!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, scaled(30)));
        centerBounds(title, width / 2, scaled(100), scaled(400), scaled(50));
        frame.getContentPane().add(title);

        setupBackground.setBounds(0, 0, width, height);
        frame.getContentPane().add(setupBackground);

    }

    private static int scaled(int value){
        return (int)(value * scale);
    }

    private static void centerBounds(Component component, int x, int y, int width, int height){
        int newx = x - (width / 2);
        int newy = y - (height /2);
        component.setBounds(newx, newy, width, height);
    }


    private static Image scaledImage(String path){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage.getScaledInstance((int)(bufferedImage.getWidth() * scale), (int)(bufferedImage.getHeight() * scale), Image.SCALE_SMOOTH);
    }
}
