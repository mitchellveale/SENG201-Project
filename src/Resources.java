import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {

    public final Font font;

    public final ImageIcon setupBackground;
    public final ImageIcon borderImage;
    public final ImageIcon testBackgroundImage;

    public Resources(){
        Font newFont;
        try {
            File font_file = new File("res/HVD_Poster.ttf");
            Font unsizedFont = Font.createFont(Font.TRUETYPE_FONT, font_file);
            newFont = unsizedFont.deriveFont(20f);
        }catch (Exception e){
            System.out.println("WARNING: Unable to locate game font, using backup font instead. Some elements may look a bit weird");
            newFont = new Font("Algerian", Font.BOLD, GraphicalGame.scaled(40));
        }
        font = newFont;
        setupBackground = new ImageIcon(scaledImage("res/Setup Background.png"));
        borderImage = new ImageIcon(scaledImage("res/Border.png"));
        testBackgroundImage = new ImageIcon(scaledImage("res/Test Background.png"));
    }

    private Image scaledImage(String path){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage.getScaledInstance(GraphicalGame.scaled(bufferedImage.getWidth()),GraphicalGame.scaled(bufferedImage.getHeight()), Image.SCALE_SMOOTH);
    }
}
