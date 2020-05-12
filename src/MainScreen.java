import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {

    private static JPanel panel;

    private static JLabel moneyLabel;
    private static JLabel actionsLabel;
    private static JLabel dayLabel;

    private static JLabel[] cropFieldButtonBackgrounds;
    private static JLabel[] cropIcons;

    public static void createMainScreen(){
        panel = new JPanel();
        panel.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.setLayout(null);

        //---------------BORDER-----------------
        Color textColor = new Color(96, 66, 15);

        moneyLabel = new JLabel("" + Farm.money);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moneyLabel.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(28f)));
        moneyLabel.setForeground(textColor);
        moneyLabel.setBounds(GraphicalGame.scaled(106, 19, 88, 28));
        panel.add(moneyLabel);

        actionsLabel = new JLabel("" + FarmerActions.getRemainingActions());
        actionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        actionsLabel.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(28f)));
        actionsLabel.setForeground(textColor);
        actionsLabel.setBounds(GraphicalGame.scaled(253, 19, 30, 28));
        panel.add(actionsLabel);

        dayLabel = new JLabel("Day " + Farm.getCurrentDay() + "/" + Farm.getGameLength());
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(28f)));
        dayLabel.setForeground(textColor);
        dayLabel.setBounds(GraphicalGame.scaled(531, 19, 158, 28));
        panel.add(dayLabel);

        JLabel border = new JLabel(GraphicalGame.resources.borderImage);
        border.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(border);
        //--------------------------------------

        JButton barnButton = newImageButton();
        barnButton.setBounds(GraphicalGame.scaled(257, 192, 154, 152));
        JLabel barnButtonBackground = buttonBackground(barnButton, GraphicalGame.resources.barn);
        panel.add(barnButton);
        panel.add(barnButtonBackground);

        JButton houseButton = newImageButton();
        houseButton.setBounds(GraphicalGame.scaled(8, 217, 202, 115));
        JLabel houseButtonBackground = buttonBackground(houseButton, GraphicalGame.resources.farmhouse);
        panel.add(houseButton);
        panel.add(houseButtonBackground);

        // Path tracks
        JLabel pathTracks = new JLabel(GraphicalGame.resources.pathTracks);
        pathTracks.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(pathTracks);


        // Crop Fields
        JButton[] cropFieldButtons = new JButton[6];
        cropFieldButtonBackgrounds = new JLabel[6];
        cropIcons = new JLabel[6];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                JButton cropFieldButton = newImageButton();
                cropFieldButton.setBounds(GraphicalGame.scaled(45 + (j * 110), 369 + (i * 96), 132, 101));
                JLabel cropIcon = buttonBackground(cropFieldButton, GraphicalGame.resources.wheat);
                cropIcon.setVisible(false);
                JLabel cropFieldButtonBackground = buttonBackground(cropFieldButton, GraphicalGame.resources.unfertilizedCropField);
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
        storeButton.setBounds(GraphicalGame.scaled(630, 90, 120, 118));
        JLabel storeButtonIcon = buttonBackground(storeButton, GraphicalGame.resources.store);
        panel.add(storeButton);
        panel.add(storeButtonIcon);

        //Animal Pens
        JButton cowPenButton = newImageButton();
        cowPenButton.setBounds(GraphicalGame.scaled(594, 475, 130, 99));
        JLabel cowPenIcon = buttonBackground(cowPenButton, GraphicalGame.resources.cowPen);
        panel.add(cowPenButton);
        panel.add(cowPenIcon);

        JButton pigPenButton = newImageButton();
        pigPenButton.setBounds(GraphicalGame.scaled(599, 376, 130, 99));
        JLabel pigPenIcon = buttonBackground(pigPenButton, GraphicalGame.resources.pigPen);
        panel.add(pigPenButton);
        panel.add(pigPenIcon);

        JButton chickenPenButton = newImageButton();
        chickenPenButton.setBounds(GraphicalGame.scaled(599, 270, 130, 99));
        JLabel chickenPenIcon = buttonBackground(chickenPenButton, GraphicalGame.resources.chickenPen);
        panel.add(chickenPenButton);
        panel.add(chickenPenIcon);



        // Background
        JLabel farmLandscape = new JLabel(GraphicalGame.resources.farmLandscape);
        farmLandscape.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(farmLandscape);


        for(int i = 0; i < 6; i++){
            int finalI = i;
            cropFieldButtons[i].addActionListener(e -> {
                if (panel != GraphicalGame.getActivePanel())
                    return;
                MediumPanel newPanel = new MediumPanel(panel, "Crop Field " + (finalI + 1));
                newPanel.designateAsCropFieldPanel(finalI);
                GraphicalGame.addPanel(newPanel, panel);
            });
        }

        // Store action listener
        storeButton.addActionListener(e -> {
            MediumPanel newPanel = new MediumPanel(panel, "Store");
            newPanel.designateAsStorePanel();
            GraphicalGame.addPanel(newPanel, panel);
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

    public static void update(){
        moneyLabel.setText("" + Farm.money);
        actionsLabel.setText("" + FarmerActions.getRemainingActions());
        dayLabel.setText("Day " + Farm.getCurrentDay() + "/" + Farm.getGameLength());


    }

    public static void updateImages(){
        for (int i = 0; i < 6; i++){
            if (Farm.cropFields[i].getPlantedCrop() == null){
                cropIcons[i].setVisible(false);
            } else{
                switch (Farm.cropFields[i].getPlantedCrop()){
                    case WHEAT:
                        cropIcons[i].setIcon(GraphicalGame.resources.wheat);
                        cropIcons[i].setVisible(true);
                        break;
                    case CORN:
                        cropIcons[i].setIcon(GraphicalGame.resources.corn);
                        cropIcons[i].setVisible(true);
                        break;
                    case BEET:
                        cropIcons[i].setIcon(GraphicalGame.resources.beet);
                        cropIcons[i].setVisible(true);
                        break;
                    case SOYBEAN:
                        cropIcons[i].setIcon(GraphicalGame.resources.soybean);
                        cropIcons[i].setVisible(true);
                        break;
                    case KALE:
                        cropIcons[i].setIcon(GraphicalGame.resources.kale);
                        cropIcons[i].setVisible(true);
                        break;
                    case POTATO:
                        cropIcons[i].setIcon(GraphicalGame.resources.potato);
                        cropIcons[i].setVisible(true);
                        break;
                    default:
                        cropIcons[i].setVisible(false);
                        break;
                }
            }


            if (Farm.cropFields[i].isFertilized())
                cropFieldButtonBackgrounds[i].setIcon(GraphicalGame.resources.fertilizedCropField);
            else
                cropFieldButtonBackgrounds[i].setIcon(GraphicalGame.resources.unfertilizedCropField);
        }
    }

    public static JPanel getPanel() {
        return panel;
    }
}
