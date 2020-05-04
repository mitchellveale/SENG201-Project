import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        frame.getContentPane().setPreferredSize(new Dimension((int)(width * scale), (int)(height * scale)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel borderImage = new JLabel(new ImageIcon(scaledImage("res/Border.png")));
        frame.add(borderImage);


        frame.setVisible(true);
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
