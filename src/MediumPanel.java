import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediumPanel extends JPanel{
    private JPanel previousPanel;

    private JLabel titleLabel;

    public MediumPanel(JPanel previousPanel, String title){
        super();
        this.previousPanel = previousPanel;
        setBounds(GraphicalGame.scaled(195, 189, 411, 262));
        setLayout(null);
        setBorder(new LineBorder(GraphicalGame.resources.secondaryColor, GraphicalGame.scaled(3)));
        setBackground(GraphicalGame.resources.primaryColor);
        //TODO: Maybe add a picture background later but for now this is fine
        titleLabel = new JLabel(title);
        titleLabel.setBounds(GraphicalGame.scaled(0, 0, 411, 51));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        titleLabel.setBackground(GraphicalGame.resources.secondaryColor);
        titleLabel.setFont(GraphicalGame.sizedFont(40f));
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    public void designateAsCropFieldPanel(int cropField){
        CropField thisCropField = Farm.cropFields[cropField];
        JLabel plantedCropLabel = new JLabel();
        plantedCropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        if (Farm.cropFields[cropField].getPlantedCrop() == null){
            plantedCropLabel.setText("Planted Crop: None");
        }else{
            plantedCropLabel.setText("Planted Crop: " + Farm.cropFields[cropField].getPlantedCrop().getName());
        }
        plantedCropLabel.setBounds(GraphicalGame.scaled(0, 59, 411, 27));
        plantedCropLabel.setFont(GraphicalGame.sizedFont(30f));
        plantedCropLabel.setForeground(GraphicalGame.resources.secondaryColor);
        add(plantedCropLabel);

        JLabel cropStatusLabel = new JLabel();
        cropStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cropStatusLabel.setBounds(GraphicalGame.scaled(0, 95, 411, 25));

        JLabel cropDaysLeftLabel = new JLabel();
        cropDaysLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cropDaysLeftLabel.setBounds(GraphicalGame.scaled(0, 120, 411, 25));
        if (Farm.cropFields[cropField].getPlantedCrop() != null){
            int percentGrown = (int)(Farm.cropFields[cropField].getGrowthPercent() * 100);
            int dayLeft = Farm.cropFields[cropField].getRemainingGrowTime();
            cropDaysLeftLabel.setText((thisCropField.isMature()) ? "Mature!" : (dayLeft == 1) ? "1 Day until mature" : dayLeft + " Days until mature");
            cropStatusLabel.setText( percentGrown + "% Grown");
        }
        cropDaysLeftLabel.setFont(GraphicalGame.sizedFont(30f));
        cropStatusLabel.setFont(GraphicalGame.sizedFont(30f));
        cropStatusLabel.setForeground(GraphicalGame.resources.secondaryColor);
        cropDaysLeftLabel.setForeground(GraphicalGame.resources.secondaryColor);
        add(cropStatusLabel);
        add(cropDaysLeftLabel);

        JLabel fertilizerLabel = new JLabel();
        fertilizerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fertilizerLabel.setBounds(GraphicalGame.scaled(0, 165, 411, 25));
        fertilizerLabel.setText((thisCropField.isFertilized()) ? "This field is fertilized" : "This field is not fertilized");

        JLabel amountLabel = new JLabel();
        amountLabel.setText((!thisCropField.isFertilized()) ? "\nYou have " + Item.FERTILIZER.getAmount() + " fertilizer" : "");
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setBounds(GraphicalGame.scaled(0, 190, 411, 25));

        fertilizerLabel.setFont(GraphicalGame.sizedFont(30f));
        amountLabel.setFont(GraphicalGame.sizedFont(30f));
        fertilizerLabel.setForeground(GraphicalGame.resources.secondaryColor);
        amountLabel.setForeground(GraphicalGame.resources.secondaryColor);
        add(fertilizerLabel);
        add(amountLabel);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton plantCropButton = new JButton("Plant Crop");
        JButton fertilizeButton = new JButton("Fertilize");
        JButton harvestButton = new JButton(("Harvest Crops"));
        JButton tendToCropsButton = new JButton("Tend to crops");

        // needed for referring to this object in action listener overrides
        JPanel thisPanel = this;

        if ((thisCropField.isFertilized() || Item.FERTILIZER.getAmount() == 0) && thisCropField.getPlantedCrop() == null)
        {
            backButton.setBounds(GraphicalGame.scaled(215, 222, 180, 30));
            plantCropButton.setBounds(GraphicalGame.scaled(15, 222, 180, 30));
            add(plantCropButton);
        }else{
            // 3-button config
            backButton.setBounds(GraphicalGame.scaled(325, 222, 71, 30));
            if (thisCropField.getPlantedCrop() == null){
                plantCropButton.setBounds(GraphicalGame.scaled(15, 222, 141, 30));
                fertilizeButton.setBounds(GraphicalGame.scaled(165, 222, 151, 30));
                add(plantCropButton);
                add(fertilizeButton);
            }
        }

        add(backButton);


        // Plant crop listener
        plantCropButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            // Create A large panel and assign it to plant seeds
        });

        // Fertilize button listener
        fertilizeButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            thisCropField.fertilize();
            MediumPanel newPanel = new MediumPanel(previousPanel, titleLabel.getText());
            newPanel.designateAsCropFieldPanel(cropField);
            GraphicalGame.addPanel(newPanel, previousPanel);
            GraphicalGame.deletePanel(this, newPanel);
            MainScreen.updateImages();
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public JPanel getPreviousPanel() {
        return previousPanel;
    }
}
