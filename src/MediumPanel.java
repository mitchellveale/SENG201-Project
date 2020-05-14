import javax.swing.*;
import javax.swing.border.LineBorder;

public class MediumPanel extends JPanel{
    private final JPanel previousPanel;

    private final JLabel titleLabel;

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
        JButton tendToCropButton = new JButton("Tend to crops");

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
            }else{
                tendToCropButton.setBounds(GraphicalGame.scaled(15, 222, 141, 30));
                harvestButton.setBounds(GraphicalGame.scaled(165, 222, 151, 30));
                add(tendToCropButton);
                add(harvestButton);
            }
        }

        add(backButton);

        // Tend to crops button listener
        tendToCropButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Tend to crop");
            newPanel.designateAsTendToCropPanel(cropField);
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        // Harvest Crops button listener
        harvestButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Harvest crops");
            newPanel.designateAsHarvestCropsPanel(cropField);
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        // Plant crop button listener
        plantCropButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            // Create A large panel and assign it to plant seeds
            LargePanel newPanel = new LargePanel(this, "Plant crop");
            newPanel.designateAsPlantPanel(cropField);
            GraphicalGame.addPanel(newPanel, this);
        });

        // Fertilize button listener
        fertilizeButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            thisCropField.fertilize();
            MediumPanel newPanel = new MediumPanel(previousPanel, titleLabel.getText());
            newPanel.designateAsCropFieldPanel(cropField);
            GraphicalGame.addPanel(newPanel, previousPanel);
            GraphicalGame.deletePanel(thisPanel, newPanel);
            MainScreen.updateImages();
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsStorePanel(){
        JButton buySeedsButton = new JButton("Buy seeds");
        JButton buyItemsButton = new JButton("Buy items");
        JButton buyAnimalsButton = new JButton("Buy animals");
        JButton exitButton = new JButton("Exit");

        buySeedsButton.setBounds(GraphicalGame.scaled(43, 62, 334, 40));
        buyItemsButton.setBounds(GraphicalGame.scaled(43, 110, 334, 40));
        buyAnimalsButton.setBounds(GraphicalGame.scaled(43, 158, 334, 40));
        exitButton.setBounds(GraphicalGame.scaled(43, 206, 334, 40));

        add(buySeedsButton);
        add(buyItemsButton);
        add(buyAnimalsButton);
        add(exitButton);

        JPanel thisPanel = this;

        // Buy seeds button listener
        buySeedsButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            LargePanel newPanel = new LargePanel(thisPanel, "Buy seeds");
            newPanel.designateAsBuySeedsPanel();
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        //Buy items button listener
        buyItemsButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            LargePanel newPanel = new LargePanel(thisPanel, "Buy items");
            newPanel.designateAsBuyItemsPanel();
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        // Buy animals button listener
        buyAnimalsButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(thisPanel, "Buy animals");
            newPanel.designateAsBuyAnimalsPanel();
            GraphicalGame.addPanel(newPanel, thisPanel);
        });


        // Exit button listener
        exitButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel()){
                System.out.println("Not the active panel active is" + GraphicalGame.getActivePanel());
                return;}
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsBuyAnimalsPanel(){
        JButton buyCowsButton = new JButton("Buy cows");
        JButton buyPigsButton = new JButton("Buy pigs");
        JButton buyChickensButton = new JButton("Buy chickens");
        JButton exitButton = new JButton("Exit");

        buyCowsButton.setBounds(GraphicalGame.scaled(43, 62, 334, 40));
        buyPigsButton.setBounds(GraphicalGame.scaled(43, 110, 334, 40));
        buyChickensButton.setBounds(GraphicalGame.scaled(43, 158, 334, 40));
        exitButton.setBounds(GraphicalGame.scaled(43, 206, 334, 40));

        add(buyCowsButton);
        add(buyPigsButton);
        add(buyChickensButton);
        add(exitButton);

        JPanel thisPanel = this;

        // Buy cow listener
        buyCowsButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Buy cows");
            newPanel.designateAsBuyAnimalsPanel(Farm.cowPen);
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        // Buy pig listener
        buyPigsButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Buy pigs");
            newPanel.designateAsBuyAnimalsPanel(Farm.pigPen);
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        // Buy chicken listener
        buyChickensButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Buy chickens");
            newPanel.designateAsBuyAnimalsPanel(Farm.chickenPen);
            GraphicalGame.addPanel(newPanel, thisPanel);
        });

        // Exit button listener
        exitButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsHousePanel(){
        JButton tendToFarmButton = new JButton("Tend to farm land (" + (int)(Farm.farmCondition * 100) + "%)");
        JButton nextDayButton = new JButton((Farm.isFinalDay() ? "Finish Game" : "Next day"));
        JButton lottoTicketButton = new JButton("Use lotto ticket (You have " + Item.LOTTO_TICKET.getAmount() + ")");
        JButton exitButton = new JButton("Exit");

        tendToFarmButton.setBounds(GraphicalGame.scaled(43, 62, 334, 40));
        nextDayButton.setBounds(GraphicalGame.scaled(43, 110, 334, 40));
        lottoTicketButton.setBounds(GraphicalGame.scaled(43, 158, 334, 40));
        exitButton.setBounds(GraphicalGame.scaled(43, 206, 334, 40));

        add(tendToFarmButton);
        add(nextDayButton);
        add(lottoTicketButton);
        add(exitButton);

        JPanel thisPanel = this;


        // Exit button listener
        exitButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel()){
                System.out.println("Not the active panel active is" + GraphicalGame.getActivePanel());
                return;}
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public JPanel getPreviousPanel() {
        return previousPanel;
    }
}
