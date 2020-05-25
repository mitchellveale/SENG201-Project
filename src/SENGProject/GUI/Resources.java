package SENGProject.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Instantiates and stores the Font, Images and Color resources that are used by the GUI
 */
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

    /**
     * sets all resources for the game to their correct values
     */
    public Resources(){
        Font newFont;
        try {
            InputStream is = getClass().getResourceAsStream("resources/HVD_Poster.ttf");
            Font unsizedFont = Font.createFont(Font.TRUETYPE_FONT, is);
            newFont = unsizedFont.deriveFont(20f);
        }catch (Exception e){
            System.out.println("WARNING: Unable to locate game font, using backup font instead. Some elements may look a bit weird");
            newFont = new Font("Algerian", Font.BOLD, GUIGame.scaled(40));
        }
        font = newFont;
        setupBackground = new ImageIcon(scaledImage("resources/Setup Background.png"));
        borderImage = new ImageIcon(scaledImage("resources/Border.png"));
        farmBackground = new ImageIcon(scaledImage("resources/Farm Background.png"));
        textFieldBackground = new ImageIcon(scaledImage("resources/TextFieldBackground.png"));
        beginButtonBackground = new ImageIcon((scaledImage("resources/Begin Button Background.png")));
        farmLandscape = new ImageIcon((scaledImage("resources/Farm Landscape.png")));
        farmhouse = new ImageIcon((scaledImage("resources/Farm House.png")));
        barn = new ImageIcon((scaledImage("resources/Barn.png")));
        pathTracks = new ImageIcon((scaledImage("resources/Path Tracks.png")));
        store = new ImageIcon((scaledImage("resources/Store.png")));
        fertilizedCropField = new ImageIcon((scaledImage("resources/Fertilized Crop Field.png")));
        unfertilizedCropField = new ImageIcon((scaledImage("resources/Unfertilized Crop Field.png")));
        cowPen = new ImageIcon((scaledImage("resources/Cow Pen.png")));
        chickenPen = new ImageIcon((scaledImage("resources/Chicken Pen.png")));
        pigPen = new ImageIcon((scaledImage("resources/Pig Pen.png")));
        wheat = new ImageIcon((scaledImage("resources/Wheat.png")));
        corn = new ImageIcon((scaledImage("resources/Corn.png")));
        soybean = new ImageIcon((scaledImage("resources/Soybeans.png")));
        beet = new ImageIcon((scaledImage("resources/Beets.png")));
        kale = new ImageIcon((scaledImage("resources/Kale.png")));
        potato = new ImageIcon((scaledImage("resources/Potatos.png")));

        primaryColor = new Color(151, 111, 41);
        secondaryColor = new Color(114, 72, 8);
        tertiaryColor = new Color(255, 183, 61);
        quaternaryColor = new Color(148, 97, 20);
    }

    private Image scaledImage(String path){
        BufferedImage bufferedImage = null;
        try {
            InputStream in = getClass().getResourceAsStream(path);
            bufferedImage = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Something went wrong when loading image '" + path + "'. Stack trace printed below\n\n");
            e.printStackTrace();
        }
        return Objects.requireNonNull(bufferedImage).getScaledInstance(GUIGame.scaled(bufferedImage.getWidth()), GUIGame.scaled(bufferedImage.getHeight()), Image.SCALE_SMOOTH);
    }
}
