package SENGProject.GUI;

import SENGProject.Farm.AnimalPen;
import SENGProject.Farm.CropField;
import SENGProject.Farm.Farm;
import SENGProject.Farm.Item;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Class for making medium panels
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class MediumPanel extends JPanel{
    private final JPanel previousPanel;

    private final JLabel titleLabel;

    public MediumPanel(JPanel previousPanel, String title){
        super();
        this.previousPanel = previousPanel;
        setBounds(GUIGame.scaled(195, 189, 411, 262));
        setLayout(null);
        setBorder(new LineBorder(GUIGame.resources.secondaryColor, GUIGame.scaled(3)));
        setBackground(GUIGame.resources.primaryColor);
        //TODO: Maybe add a picture background later but for now this is fine
        titleLabel = new JLabel(title);
        titleLabel.setBounds(GUIGame.scaled(0, 0, 411, 51));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(GUIGame.resources.tertiaryColor);
        titleLabel.setBackground(GUIGame.resources.secondaryColor);
        titleLabel.setFont(GUIGame.sizedFont(40f));
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    public void designateAsCropFieldPanel(int cropField){
        CropField thisCropField = Farm.getCropFields()[cropField];
        JLabel plantedCropLabel = new JLabel();
        plantedCropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        if (Farm.getCropFields()[cropField].getPlantedCrop() == null){
            plantedCropLabel.setText("Planted Crop: None");
        }else{
            plantedCropLabel.setText("Planted Crop: " + Farm.getCropFields()[cropField].getPlantedCrop().getName());
        }
        plantedCropLabel.setBounds(GUIGame.scaled(0, 59, 411, 27));
        plantedCropLabel.setFont(GUIGame.sizedFont(30f));
        plantedCropLabel.setForeground(GUIGame.resources.secondaryColor);
        add(plantedCropLabel);

        JLabel cropStatusLabel = new JLabel();
        cropStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cropStatusLabel.setBounds(GUIGame.scaled(0, 95, 411, 25));

        JLabel cropDaysLeftLabel = new JLabel();
        cropDaysLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cropDaysLeftLabel.setBounds(GUIGame.scaled(0, 120, 411, 25));
        if (Farm.getCropFields()[cropField].getPlantedCrop() != null){
            int percentGrown = (int)(Farm.getCropFields()[cropField].getGrowthPercent() * 100);
            int dayLeft = Farm.getCropFields()[cropField].getRemainingGrowTime();
            cropDaysLeftLabel.setText((thisCropField.isMature()) ? "Mature!" : (dayLeft == 1) ? "1 Day until mature" : dayLeft + " Days until mature");
            cropStatusLabel.setText( percentGrown + "% Grown");
        }
        cropDaysLeftLabel.setFont(GUIGame.sizedFont(30f));
        cropStatusLabel.setFont(GUIGame.sizedFont(30f));
        cropStatusLabel.setForeground(GUIGame.resources.secondaryColor);
        cropDaysLeftLabel.setForeground(GUIGame.resources.secondaryColor);
        add(cropStatusLabel);
        add(cropDaysLeftLabel);

        JLabel fertilizerLabel = new JLabel();
        fertilizerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fertilizerLabel.setBounds(GUIGame.scaled(0, 165, 411, 25));
        fertilizerLabel.setText((thisCropField.isFertilized()) ? "This field is fertilized" : "This field is not fertilized");

        JLabel amountLabel = new JLabel();
        amountLabel.setText((!thisCropField.isFertilized()) ? "\nYou have " + Item.FERTILIZER.getAmount() + " fertilizer" : "");
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setBounds(GUIGame.scaled(0, 190, 411, 25));

        fertilizerLabel.setFont(GUIGame.sizedFont(30f));
        amountLabel.setFont(GUIGame.sizedFont(30f));
        fertilizerLabel.setForeground(GUIGame.resources.secondaryColor);
        amountLabel.setForeground(GUIGame.resources.secondaryColor);
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
            backButton.setBounds(GUIGame.scaled(215, 222, 180, 30));
            plantCropButton.setBounds(GUIGame.scaled(15, 222, 180, 30));
            add(plantCropButton);
        }else{
            // 3-button config
            backButton.setBounds(GUIGame.scaled(325, 222, 71, 30));
            if (thisCropField.getPlantedCrop() == null){
                plantCropButton.setBounds(GUIGame.scaled(15, 222, 141, 30));
                fertilizeButton.setBounds(GUIGame.scaled(165, 222, 151, 30));
                add(plantCropButton);
                add(fertilizeButton);
            }else{
                tendToCropButton.setBounds(GUIGame.scaled(15, 222, 141, 30));
                harvestButton.setBounds(GUIGame.scaled(165, 222, 151, 30));
                add(tendToCropButton);
                add(harvestButton);
            }
        }

        add(backButton);

        // Tend to crops button listener
        tendToCropButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Tend to crop");
            newPanel.designateAsTendToCropPanel(cropField);
            GUIGame.addPanel(newPanel);
        });

        // Harvest Crops button listener
        harvestButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Harvest crops");
            newPanel.designateAsHarvestCropsPanel(cropField);
            GUIGame.addPanel(newPanel);
        });

        // Plant crop button listener
        plantCropButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            // Create A large panel and assign it to plant seeds
            LargePanel newPanel = new LargePanel(this, "Plant crop");
            newPanel.designateAsPlantPanel(cropField);
            GUIGame.addPanel(newPanel);
        });

        // Fertilize button listener
        fertilizeButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            thisCropField.fertilize();
            MediumPanel newPanel = new MediumPanel(previousPanel, titleLabel.getText());
            newPanel.designateAsCropFieldPanel(cropField);
            GUIGame.addPanel(newPanel);
            GUIGame.deletePanel(thisPanel, newPanel);
            MainScreen.updateImages();
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsStorePanel(){
        JButton buySeedsButton = new JButton("Buy seeds");
        JButton buyItemsButton = new JButton("Buy items");
        JButton buyAnimalsButton = new JButton("Buy animals");
        JButton exitButton = new JButton("Exit");

        buySeedsButton.setBounds(GUIGame.scaled(43, 62, 334, 40));
        buyItemsButton.setBounds(GUIGame.scaled(43, 110, 334, 40));
        buyAnimalsButton.setBounds(GUIGame.scaled(43, 158, 334, 40));
        exitButton.setBounds(GUIGame.scaled(43, 206, 334, 40));

        add(buySeedsButton);
        add(buyItemsButton);
        add(buyAnimalsButton);
        add(exitButton);

        JPanel thisPanel = this;

        // Buy seeds button listener
        buySeedsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            LargePanel newPanel = new LargePanel(thisPanel, "Buy seeds");
            newPanel.designateAsBuySeedsPanel();
            GUIGame.addPanel(newPanel);
        });

        //Buy items button listener
        buyItemsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            LargePanel newPanel = new LargePanel(thisPanel, "Buy items");
            newPanel.designateAsBuyItemsPanel();
            GUIGame.addPanel(newPanel);
        });

        // Buy animals button listener
        buyAnimalsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            MediumPanel newPanel = new MediumPanel(thisPanel, "Buy animals");
            newPanel.designateAsBuyAnimalsPanel();
            GUIGame.addPanel(newPanel);
        });


        // Exit button listener
        exitButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel()){
                System.out.println("Not the active panel active is" + GUIGame.getActivePanel());
                return;}
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsBuyAnimalsPanel(){
        JButton buyCowsButton = new JButton("Buy cows");
        JButton buyPigsButton = new JButton("Buy pigs");
        JButton buyChickensButton = new JButton("Buy chickens");
        JButton exitButton = new JButton("Exit");

        buyCowsButton.setBounds(GUIGame.scaled(43, 62, 334, 40));
        buyPigsButton.setBounds(GUIGame.scaled(43, 110, 334, 40));
        buyChickensButton.setBounds(GUIGame.scaled(43, 158, 334, 40));
        exitButton.setBounds(GUIGame.scaled(43, 206, 334, 40));

        add(buyCowsButton);
        add(buyPigsButton);
        add(buyChickensButton);
        add(exitButton);

        JPanel thisPanel = this;

        // Buy cow listener
        buyCowsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Buy cows");
            newPanel.designateAsBuyAnimalsPanel(Farm.getAnimalPens()[0]);
            GUIGame.addPanel(newPanel);
        });

        // Buy pig listener
        buyPigsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Buy pigs");
            newPanel.designateAsBuyAnimalsPanel(Farm.getAnimalPens()[2]);
            GUIGame.addPanel(newPanel);
        });

        // Buy chicken listener
        buyChickensButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Buy chickens");
            newPanel.designateAsBuyAnimalsPanel(Farm.getAnimalPens()[1]);
            GUIGame.addPanel(newPanel);
        });

        // Exit button listener
        exitButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsHousePanel(){
        JButton tendToFarmButton = new JButton("Tend to farm land (" + (int)(Farm.getFarmCondition() * 100) + "%)");
        JButton nextDayButton = new JButton((Farm.isFinalDay() ? "Finish Game" : "Next day"));
        JButton lottoTicketButton = new JButton("Use lotto ticket (You have " + Item.LOTTO_TICKET.getAmount() + ")");
        JButton exitButton = new JButton("Exit");

        tendToFarmButton.setBounds(GUIGame.scaled(43, 62, 334, 40));
        nextDayButton.setBounds(GUIGame.scaled(43, 110, 334, 40));
        lottoTicketButton.setBounds(GUIGame.scaled(43, 158, 334, 40));
        exitButton.setBounds(GUIGame.scaled(43, 206, 334, 40));

        add(tendToFarmButton);
        add(nextDayButton);
        add(lottoTicketButton);
        add(exitButton);

        JPanel thisPanel = this;

        tendToFarmButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Tend to farm land");
            newPanel.designateAsTendToFarmPanel();
            GUIGame.addPanel(newPanel);
        });

        nextDayButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            if (Farm.isFinalDay()){
                ScoreScreen.createScoreScreen();
                GUIGame.addPanel(ScoreScreen.getPanel());
            }
            else {
                Farm.nextDay();
                MainScreen.update();
                GUIGame.deletePanel(thisPanel, previousPanel);
            }
        });

        if(Item.LOTTO_TICKET.getAmount() > 0) {
            lottoTicketButton.addActionListener(e -> {
                if (thisPanel != GUIGame.getActivePanel())
                    return;
                SmallPanel newPanel = new SmallPanel(thisPanel, "Lotto Ticket");
                newPanel.designateAsLottoTicketPanel();
                GUIGame.addPanel(newPanel);
            });
        }

        // Exit button listener
        exitButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel()){
                return;}
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsAnimalPenPanel(AnimalPen animal){
        JLabel amountLabel = new JLabel("Amount: " + animal.getAnimal().getCurrentCount() + "/" + animal.getCapacity());
        JLabel happinessLabel = new JLabel("Happiness: " + animal.getAnimal().getHappiness() + "/10");
        JLabel healthinessLabel = new JLabel("Healthiness: " + animal.getAnimal().getHealthiness() + "/10");
        JLabel dailyIncomeLabel = new JLabel("Daily income: $" + Math.round(animal.getAnimal().getdailyIncome() * 100) / 100.0 + " per " + animal.getAnimal().getName());

        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        happinessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthinessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dailyIncomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        amountLabel.setFont(GUIGame.sizedFont(30f));
        happinessLabel.setFont(GUIGame.sizedFont(30f));
        healthinessLabel.setFont(GUIGame.sizedFont(30f));
        dailyIncomeLabel.setFont(GUIGame.sizedFont(30f));

        amountLabel.setForeground(GUIGame.resources.secondaryColor);
        happinessLabel.setForeground(GUIGame.resources.secondaryColor);
        healthinessLabel.setForeground(GUIGame.resources.secondaryColor);
        dailyIncomeLabel.setForeground(GUIGame.resources.secondaryColor);

        amountLabel.setBounds(GUIGame.scaled(0, 60, 411, 30));
        happinessLabel.setBounds(GUIGame.scaled(0, 90, 411, 30));
        healthinessLabel.setBounds(GUIGame.scaled(0, 120, 411, 30));
        dailyIncomeLabel.setBounds(GUIGame.scaled(0, 150, 411, 30));

        add(amountLabel);
        add(happinessLabel);
        add(healthinessLabel);
        add(dailyIncomeLabel);

        JButton backButton = new JButton("Back");
        JButton feedButton = new JButton("Feed");
        JButton playButton = new JButton("Play");

        // needed for referring to this object in action listener overrides
        JPanel thisPanel = this;

        backButton.setBounds(GUIGame.scaled(325, 222, 71, 30));
        feedButton.setBounds(GUIGame.scaled(15, 222, 141, 30));
        playButton.setBounds(GUIGame.scaled(165, 222, 151, 30));

        add(backButton);
        add(feedButton);
        add(playButton);

        feedButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Feed Animals");
            newPanel.designateAsFeedPanel(animal);
            GUIGame.addPanel(newPanel);
        });

        playButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            SmallPanel newPanel = new SmallPanel(thisPanel, "Play With Animals");
            newPanel.designateAsPlayPanel(animal);
            GUIGame.addPanel(newPanel);
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }
}
