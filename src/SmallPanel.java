import javax.swing.*;
import javax.swing.border.LineBorder;

public class SmallPanel extends JPanel{
    private JPanel previousPanel;

    private JLabel titleLabel;

    public SmallPanel(JPanel previousPanel, String title){
        super();
        this.previousPanel = previousPanel;
        setBounds(GraphicalGame.scaled(228, 229, 345, 183));
        setLayout(null);
        setBorder(new LineBorder(GraphicalGame.resources.secondaryColor, GraphicalGame.scaled(3)));
        setBackground(GraphicalGame.resources.primaryColor);
        //TODO: Maybe add a picture background later but for now this is fine
        titleLabel = new JLabel(title);
        titleLabel.setBounds(GraphicalGame.scaled(0, 0, 345, 44));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        titleLabel.setBackground(GraphicalGame.resources.secondaryColor);
        titleLabel.setFont(GraphicalGame.sizedFont(35f));
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    public void designateAsTendToCropPanel(int cropField){
        CropField thisCropField = Farm.cropFields[cropField];
        // JTextArea doesn't seem to have the ability to center text :(
        // also i hate this
        JLabel line1 = new JLabel("Would you like to use water or");
        JLabel line2 = new JLabel("Growth compound? (you have " + Item.GROWTH_COMPOUND.getAmount() + ")");
        JLabel line3 = new JLabel("This uses an action");
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GraphicalGame.scaled(0, 67, 345, 20));
        line2.setBounds(GraphicalGame.scaled(0, 87, 345, 20));
        line3.setBounds(GraphicalGame.scaled(0, 107, 345, 20));
        line1.setFont(GraphicalGame.sizedFont(30f));
        line2.setFont(GraphicalGame.sizedFont(30f));
        line3.setFont(GraphicalGame.sizedFont(30f));
        line1.setForeground(GraphicalGame.resources.secondaryColor);
        line2.setForeground(GraphicalGame.resources.secondaryColor);
        line3.setForeground(GraphicalGame.resources.secondaryColor);

        add(line1);
        add(line2);
        add(line3);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton waterButton = new JButton("Water");
        JButton growthButton = new JButton("Growth Compound");

        JPanel thisPanel = this;


        if (Item.GROWTH_COMPOUND.getAmount() == 0){
            // 2 button config
            waterButton.setBounds(GraphicalGame.scaled(15, 147, 145, 30));
            backButton.setBounds(GraphicalGame.scaled(185, 147, 145, 30));
        }else{
            // 3 button config
            waterButton.setBounds(GraphicalGame.scaled(15, 147, 69, 30));
            growthButton.setBounds(GraphicalGame.scaled(94, 147, 174, 30));
            backButton.setBounds(GraphicalGame.scaled(278, 147, 52, 30));

            add(growthButton);
        }

        add(waterButton);
        add(backButton);

        // Water button listener
        waterButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0){
                FarmerActions.tendToCrop(Farm.cropFields[cropField], false);
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
                newPanel.designateAsCropFieldPanel(cropField);
                GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
                GraphicalGame.deletePanel(this, newPanel);
                GraphicalGame.deletePanel(previousPanel, newPanel);
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GraphicalGame.addPanel(newPanel, thisPanel);
            }
        });

        // Growth compound button
        growthButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0){
                FarmerActions.tendToCrop(Farm.cropFields[cropField], true);
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
                newPanel.designateAsCropFieldPanel(cropField);
                GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
                GraphicalGame.deletePanel(this, newPanel);
                GraphicalGame.deletePanel(previousPanel, newPanel);
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GraphicalGame.addPanel(newPanel, thisPanel);
            }
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsHarvestCropsPanel(int cropField){
        // i hate this
        JLabel line1 = new JLabel("Would you like to harvest all");
        JLabel line2 = new JLabel("mature crops? (uses an action)");
        JLabel line3 = new JLabel();
        int money = 0;
        for (CropField c : Farm.cropFields){
            money += c.harvestValue();
        }
        line3.setText("You will receive $" + money);

        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GraphicalGame.scaled(0, 67, 345, 20));
        line2.setBounds(GraphicalGame.scaled(0, 87, 345, 20));
        line3.setBounds(GraphicalGame.scaled(0, 107, 345, 20));
        line1.setFont(GraphicalGame.sizedFont(30f));
        line2.setFont(GraphicalGame.sizedFont(30f));
        line3.setFont(GraphicalGame.sizedFont(30f));
        line1.setForeground(GraphicalGame.resources.secondaryColor);
        line2.setForeground(GraphicalGame.resources.secondaryColor);
        line3.setForeground(GraphicalGame.resources.tertiaryColor);

        add(line1);
        add(line2);
        add(line3);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton harvestButton = new JButton("Harvest!");

        JPanel thisPanel = this;

        harvestButton.setBounds(GraphicalGame.scaled(15, 147, 145, 30));
        backButton.setBounds(GraphicalGame.scaled(185, 147, 145, 30));

        add(harvestButton);
        add(backButton);
        // Harvest button listener
        harvestButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0) {
                FarmerActions.harvestCrops();
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
                newPanel.designateAsCropFieldPanel(cropField);
                GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
                GraphicalGame.deletePanel(this, newPanel);
                GraphicalGame.deletePanel(previousPanel, newPanel);
                MainScreen.updateImages();
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GraphicalGame.addPanel(newPanel, thisPanel);
            }

        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsBuyAnimalsPanel(AnimalPen animal){
        JLabel infoLabel = new JLabel("Buy 0 " + animal.holdingAnimal.getName() + "s for $0?");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        infoLabel.setBounds(GraphicalGame.scaled(0, 44, 345, 63));
        infoLabel.setFont(GraphicalGame.sizedFont(30f));

        add(infoLabel);

        //Slider
        int maxAnimals = Math.min(Farm.money / animal.holdingAnimal.getbuyPrice(), (int) animal.capacity - animal.holdingAnimal.getCurrentCount());
        JSlider animalSlider = new JSlider(JSlider.HORIZONTAL, 0, maxAnimals, 0);
        animalSlider.setBounds(GraphicalGame.scaled(51, 108, 241, 20));
        animalSlider.setOpaque(false);
        add(animalSlider);

        JLabel minValue = new JLabel("0");
        JLabel maxValue = new JLabel("" + maxAnimals);
        minValue.setForeground(GraphicalGame.resources.tertiaryColor);
        maxValue.setForeground(GraphicalGame.resources.tertiaryColor);
        minValue.setFont(GraphicalGame.sizedFont(30f));
        maxValue.setFont(GraphicalGame.sizedFont(30f));
        minValue.setBounds(GraphicalGame.scaled(30, 103, 30, 30));
        maxValue.setBounds(GraphicalGame.scaled(297, 103, 40, 30));
        add(minValue);
        add(maxValue);
        //buttons
        JButton backButton = new JButton("Back");
        JButton buyButton = new JButton("Buy");

        JPanel thisPanel = this;

        buyButton.setBounds(GraphicalGame.scaled(15, 147, 145, 30));
        backButton.setBounds(GraphicalGame.scaled(185, 147, 145, 30));

        add(buyButton);
        add(backButton);

        // Slider listener
        animalSlider.addChangeListener(e -> {
            if (animalSlider.getValue() == 1)
                infoLabel.setText("Buy 1 " + animal.holdingAnimal.getName() + " for $" + animal.holdingAnimal.getbuyPrice());
            else
                infoLabel.setText("Buy " + animalSlider.getValue() + " " + animal.holdingAnimal.getName() + "s for $" + animal.holdingAnimal.getbuyPrice() * animalSlider.getValue());
        });

        //buy button listener
        buyButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            animal.holdingAnimal.CurrentCount += animalSlider.getValue();
            Farm.money -= animal.holdingAnimal.getbuyPrice() * animalSlider.getValue();
            MainScreen.update();
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsNoActionsPanel(){
        JLabel warningLabel = new JLabel("You have no actions remaining");
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setVerticalAlignment(SwingConstants.CENTER);
        warningLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        warningLabel.setBounds(GraphicalGame.scaled(0, 44, 345, 139));
        warningLabel.setFont(GraphicalGame.sizedFont(35f));
        JButton okButton = new JButton("OK");
        okButton.setBounds(GraphicalGame.scaled(15, 147, 315, 30));
        add(warningLabel);
        add(okButton);

        JPanel thisPanel = this;

        okButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public JPanel getPreviousPanel() {
        return previousPanel;
    }
}
