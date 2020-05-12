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
    public final ImageIcon farmBackground;
    public final ImageIcon textFieldBackground;
    public final ImageIcon beginButtonBackground;
    public final ImageIcon farmLandscape;
    public final ImageIcon farmhouse;
    public final ImageIcon barn;
    public final ImageIcon pathTracks;
    public final ImageIcon store;
    public final ImageIcon fertilizedCropField;
    public final ImageIcon unfertilizedCropField;
    public final ImageIcon cowPen;
    public final ImageIcon chickenPen;
    public final ImageIcon pigPen;

    // Crop images
    public final ImageIcon wheat;
    public final ImageIcon corn;
    public final ImageIcon soybean;
    public final ImageIcon beet;
    public final ImageIcon kale;
    public final ImageIcon potato;

    //Colors
    public final Color primaryColor;
    public final Color secondaryColor;
    public final Color tertiaryColor;
    public final Color quaternaryColor;


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
        farmBackground = new ImageIcon(scaledImage("res/Farm Background.png"));
        textFieldBackground = new ImageIcon(scaledImage("res/TextFieldBackground.png"));
        beginButtonBackground = new ImageIcon((scaledImage("res/Begin Button Background.png")));
        farmLandscape = new ImageIcon((scaledImage("res/Farm Landscape.png")));
        farmhouse = new ImageIcon((scaledImage("res/Farm House.png")));
        barn = new ImageIcon((scaledImage("res/Barn.png")));
        pathTracks = new ImageIcon((scaledImage("res/Path Tracks.png")));
        store = new ImageIcon((scaledImage("res/Store.png")));
        fertilizedCropField = new ImageIcon((scaledImage("res/Fertilized Crop Field.png")));
        unfertilizedCropField = new ImageIcon((scaledImage("res/Unfertilized Crop Field.png")));
        cowPen = new ImageIcon((scaledImage("res/Cow Pen.png")));
        chickenPen = new ImageIcon((scaledImage("res/Chicken Pen.png")));
        pigPen = new ImageIcon((scaledImage("res/Pig Pen.png")));
        wheat = new ImageIcon((scaledImage("res/Wheat.png")));
        corn = new ImageIcon((scaledImage("res/Corn.png")));
        soybean = new ImageIcon((scaledImage("res/Soybeans.png")));
        beet = new ImageIcon((scaledImage("res/Beets.png")));
        kale = new ImageIcon((scaledImage("res/Kale.png")));
        potato = new ImageIcon((scaledImage("res/Potatos.png")));

        primaryColor = new Color(151, 111, 41);
        secondaryColor = new Color(114, 72, 8);
        tertiaryColor = new Color(255, 183, 61);
        quaternaryColor = new Color(148, 97, 20);
    }

    private Image scaledImage(String path){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Something went wrong when scaling required images");
        }
        return bufferedImage.getScaledInstance(GraphicalGame.scaled(bufferedImage.getWidth()),GraphicalGame.scaled(bufferedImage.getHeight()), Image.SCALE_SMOOTH);
    }
}
