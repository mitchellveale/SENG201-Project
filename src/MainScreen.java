import javax.swing.*;
import java.awt.*;
/**
 * A class which has the attributes and methods of the main gameplay screen 
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class MainScreen {

    private static JPanel panel;

    private static JLabel moneyLabel;
    private static JLabel actionsLabel;
    private static JLabel dayLabel;

    private static JLabel[] cropFieldButtonBackgrounds;
    private static JLabel[] cropIcons;

    /**
     * Creates the main screen for gameplay 
     */
    public static void createMainScreen(){
        panel = new JPanel();
        panel.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.setLayout(null);

        //---------------BORDER-----------------
        Color textColor = new Color(96, 66, 15);

        moneyLabel = new JLabel("" + Farm.getMoney());
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moneyLabel.setFont(GUIGame.sizedFont(28f * 1.69f));
        moneyLabel.setForeground(textColor);
        moneyLabel.setBounds(GUIGame.scaled(106, 19, 88, 28));
        panel.add(moneyLabel);

        actionsLabel = new JLabel("" + FarmerActions.getRemainingActions());
        actionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        actionsLabel.setFont(GUIGame.sizedFont(28f * 1.69f));
        actionsLabel.setForeground(textColor);
        actionsLabel.setBounds(GUIGame.scaled(253, 19, 30, 28));
        panel.add(actionsLabel);

        dayLabel = new JLabel("Day " + Farm.getCurrentDay() + "/" + Farm.getGameLength());
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(GUIGame.sizedFont(28f * 1.69f));
        dayLabel.setForeground(textColor);
        dayLabel.setBounds(GUIGame.scaled(531, 19, 158, 28));
        panel.add(dayLabel);

        JLabel border = new JLabel(GUIGame.resources.borderImage);
        border.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(border);
        //--------------------------------------

        JButton barnButton = newImageButton();
        barnButton.setBounds(GUIGame.scaled(257, 192, 154, 152));
        JLabel barnButtonBackground = buttonBackground(barnButton, GUIGame.resources.barn);
        panel.add(barnButton);
        panel.add(barnButtonBackground);

        JButton houseButton = newImageButton();
        houseButton.setBounds(GUIGame.scaled(8, 217, 202, 115));
        JLabel houseButtonBackground = buttonBackground(houseButton, GUIGame.resources.farmhouse);
        panel.add(houseButton);
        panel.add(houseButtonBackground);

        // Path tracks
        JLabel pathTracks = new JLabel(GUIGame.resources.pathTracks);
        pathTracks.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(pathTracks);


        // Crop Fields
        JButton[] cropFieldButtons = new JButton[6];
        cropFieldButtonBackgrounds = new JLabel[6];
        cropIcons = new JLabel[6];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                JButton cropFieldButton = newImageButton();
                cropFieldButton.setBounds(GUIGame.scaled(45 + (j * 110), 369 + (i * 96), 132, 101));
                JLabel cropIcon = buttonBackground(cropFieldButton, GUIGame.resources.wheat);
                cropIcon.setVisible(false);
                JLabel cropFieldButtonBackground = buttonBackground(cropFieldButton, GUIGame.resources.unfertilizedCropField);
                cropFieldButtons[j + (3 * i)] = cropFieldButton;
                cropIcons[j + (3 * i)] = cropIcon;
                cropFieldButtonBackgrounds[j + (3 * i)] = cropFieldButtonBackground;
            }
        }
        for (JButton button : cropFieldButtons){
            panel.add(button);
        }
        for (JLabel label : cropIcons){
            panel.add(label);
        }
        for (JLabel label : cropFieldButtonBackgrounds){
            panel.add(label);
        }

        JButton storeButton = newImageButton();
        storeButton.setBounds(GUIGame.scaled(630, 90, 120, 118));
        JLabel storeButtonIcon = buttonBackground(storeButton, GUIGame.resources.store);
        panel.add(storeButton);
        panel.add(storeButtonIcon);

        //Animal Pens
        JButton cowPenButton = newImageButton();
        cowPenButton.setBounds(GUIGame.scaled(594, 475, 130, 99));
        JLabel cowPenIcon = buttonBackground(cowPenButton, GUIGame.resources.cowPen);
        panel.add(cowPenButton);
        panel.add(cowPenIcon);

        JButton pigPenButton = newImageButton();
        pigPenButton.setBounds(GUIGame.scaled(599, 376, 130, 99));
        JLabel pigPenIcon = buttonBackground(pigPenButton, GUIGame.resources.pigPen);
        panel.add(pigPenButton);
        panel.add(pigPenIcon);

        JButton chickenPenButton = newImageButton();
        chickenPenButton.setBounds(GUIGame.scaled(599, 270, 130, 99));
        JLabel chickenPenIcon = buttonBackground(chickenPenButton, GUIGame.resources.chickenPen);
        panel.add(chickenPenButton);
        panel.add(chickenPenIcon);



        // Background
        JLabel farmLandscape = new JLabel(GUIGame.resources.farmLandscape);
        farmLandscape.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(farmLandscape);

        // Crop field button listeners
        for(int i = 0; i < 6; i++){
            int finalI = i;
            cropFieldButtons[i].addActionListener(e -> {
                if (panel != GUIGame.getActivePanel())
                    return;
                MediumPanel newPanel = new MediumPanel(panel, "Crop Field " + (finalI + 1));
                newPanel.designateAsCropFieldPanel(finalI);
                GUIGame.addPanel(newPanel);
            });
        }

        cowPenButton.addActionListener(e -> {
            if (panel != GUIGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(panel, "Cow pen");
            newPanel.designateAsAnimalPenPanel(Farm.getAnimalPens()[0]);
            GUIGame.addPanel(newPanel);
        });

        pigPenButton.addActionListener(e -> {
            if (panel != GUIGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(panel, "Pig pen");
            newPanel.designateAsAnimalPenPanel(Farm.getAnimalPens()[2]);
            GUIGame.addPanel(newPanel);
        });

        chickenPenButton.addActionListener(e -> {
            if (panel != GUIGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(panel, "Chicken pen");
            newPanel.designateAsAnimalPenPanel(Farm.getAnimalPens()[1]);
            GUIGame.addPanel(newPanel);
        });

        // Store action listener
        storeButton.addActionListener(e -> {
            if (panel != GUIGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(panel, "Store");
            newPanel.designateAsStorePanel();
            GUIGame.addPanel(newPanel);
        });

        // Barn action listener
        barnButton.addActionListener(e -> {
            if (panel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(panel, "Inventory");
            newPanel.designateAsInventoryPanel();
            GUIGame.addPanel(newPanel);
        });

        // house button action listener
        houseButton.addActionListener(e -> {
            if (panel != GUIGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(panel, Farm.getFarmerName() + "'s farmhouse");
            newPanel.designateAsHousePanel();
            GUIGame.addPanel(newPanel);
        });
    }

    private static JButton newImageButton(){
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    private static JLabel buttonBackground(JButton button, ImageIcon image){
        JLabel label = new JLabel(image);
        label.setBounds(button.getBounds());
        return label;
    }

    /**
     * Updates money, actions and day
     */
    public static void update(){
        moneyLabel.setText("" + Farm.getMoney());
        actionsLabel.setText("" + FarmerActions.getRemainingActions());
        dayLabel.setText("Day " + Farm.getCurrentDay() + "/" + Farm.getGameLength());


    }

    /**
     * Shows images of crops when they're planted
     */
    public static void updateImages(){
        for (int i = 0; i < 6; i++){
            if (Farm.getCropFields()[i].getPlantedCrop() == null){
                cropIcons[i].setVisible(false);
            } else{
                switch (Farm.getCropFields()[i].getPlantedCrop()){
                    case WHEAT:
                        cropIcons[i].setIcon(GUIGame.resources.wheat);
                        cropIcons[i].setVisible(true);
                        break;
                    case CORN:
                        cropIcons[i].setIcon(GUIGame.resources.corn);
                        cropIcons[i].setVisible(true);
                        break;
                    case BEET:
                        cropIcons[i].setIcon(GUIGame.resources.beet);
                        cropIcons[i].setVisible(true);
                        break;
                    case SOYBEAN:
                        cropIcons[i].setIcon(GUIGame.resources.soybean);
                        cropIcons[i].setVisible(true);
                        break;
                    case KALE:
                        cropIcons[i].setIcon(GUIGame.resources.kale);
                        cropIcons[i].setVisible(true);
                        break;
                    case POTATO:
                        cropIcons[i].setIcon(GUIGame.resources.potato);
                        cropIcons[i].setVisible(true);
                        break;
                    default:
                        cropIcons[i].setVisible(false);
                        break;
                }
            }


            if (Farm.getCropFields()[i].isFertilized())
                cropFieldButtonBackgrounds[i].setIcon(GUIGame.resources.fertilizedCropField);
            else
                cropFieldButtonBackgrounds[i].setIcon(GUIGame.resources.unfertilizedCropField);
        }
    }

    /**
     * 
     * @return the panel
     */
    public static JPanel getPanel() {
        return panel;
    }
}
