package SENGProject.GUI;

import SENGProject.Farm.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Class for making small panels 
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class SmallPanel extends JPanel{
    private final JPanel previousPanel;

    public SmallPanel(JPanel previousPanel, String title){
        super();
        this.previousPanel = previousPanel;
        setBounds(GUIGame.scaled(228, 229, 345, 183));
        setLayout(null);
        setBorder(new LineBorder(GUIGame.resources.secondaryColor, GUIGame.scaled(3)));
        setBackground(GUIGame.resources.primaryColor);
        //TODO: Maybe add a picture background later but for now this is fine
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(GUIGame.scaled(0, 0, 345, 44));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(GUIGame.resources.tertiaryColor);
        titleLabel.setBackground(GUIGame.resources.secondaryColor);
        titleLabel.setFont(GUIGame.sizedFont(35f));
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    public void designateAsTendToCropPanel(int cropField){
        CropField thisCropField = Farm.getCropFields()[cropField];
        // JTextArea doesn't seem to have the ability to center text :(
        // also i hate this
        JLabel line1 = new JLabel("Would you like to use water or");
        JLabel line2 = new JLabel("Growth compound? (you have " + Item.GROWTH_COMPOUND.getAmount() + ")");
        JLabel line3 = new JLabel("This uses an action");
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GUIGame.scaled(0, 67, 345, 20));
        line2.setBounds(GUIGame.scaled(0, 87, 345, 20));
        line3.setBounds(GUIGame.scaled(0, 107, 345, 20));
        line1.setFont(GUIGame.sizedFont(30f));
        line2.setFont(GUIGame.sizedFont(30f));
        line3.setFont(GUIGame.sizedFont(30f));
        line1.setForeground(GUIGame.resources.secondaryColor);
        line2.setForeground(GUIGame.resources.secondaryColor);
        line3.setForeground(GUIGame.resources.secondaryColor);

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
            waterButton.setBounds(GUIGame.scaled(15, 147, 145, 30));
            backButton.setBounds(GUIGame.scaled(185, 147, 145, 30));
        }else{
            // 3 button config
            waterButton.setBounds(GUIGame.scaled(15, 147, 69, 30));
            growthButton.setBounds(GUIGame.scaled(94, 147, 174, 30));
            backButton.setBounds(GUIGame.scaled(278, 147, 52, 30));

            add(growthButton);
        }

        add(waterButton);
        add(backButton);

        // Water button listener
        waterButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0){
                FarmerActions.tendToCrop(Farm.getCropFields()[cropField], false);
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
                newPanel.designateAsCropFieldPanel(cropField);
                GUIGame.addPanel(newPanel);
                GUIGame.deletePanel(this, newPanel);
                GUIGame.deletePanel(previousPanel, newPanel);
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GUIGame.addPanel(newPanel);
            }
        });

        // Growth compound button
        growthButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0){
                FarmerActions.tendToCrop(Farm.getCropFields()[cropField], true);
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
                newPanel.designateAsCropFieldPanel(cropField);
                GUIGame.addPanel(newPanel);
                GUIGame.deletePanel(this, newPanel);
                GUIGame.deletePanel(previousPanel, newPanel);
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GUIGame.addPanel(newPanel);
            }
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsHarvestCropsPanel(int cropField){
        // i hate this
        JLabel line1 = new JLabel("Would you like to harvest all");
        JLabel line2 = new JLabel("mature crops? (uses an action)");
        JLabel line3 = new JLabel();
        int money = 0;
        for (CropField c : Farm.getCropFields()){
            money += c.harvestValue();
        }
        line3.setText("You will receive $" + money);

        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GUIGame.scaled(0, 67, 345, 20));
        line2.setBounds(GUIGame.scaled(0, 87, 345, 20));
        line3.setBounds(GUIGame.scaled(0, 107, 345, 20));
        line1.setFont(GUIGame.sizedFont(30f));
        line2.setFont(GUIGame.sizedFont(30f));
        line3.setFont(GUIGame.sizedFont(30f));
        line1.setForeground(GUIGame.resources.secondaryColor);
        line2.setForeground(GUIGame.resources.secondaryColor);
        line3.setForeground(GUIGame.resources.tertiaryColor);

        add(line1);
        add(line2);
        add(line3);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton harvestButton = new JButton("Harvest!");

        JPanel thisPanel = this;

        harvestButton.setBounds(GUIGame.scaled(15, 147, 145, 30));
        backButton.setBounds(GUIGame.scaled(185, 147, 145, 30));

        add(harvestButton);
        add(backButton);
        // Harvest button listener
        harvestButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0) {
                FarmerActions.harvestCrops();
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
                newPanel.designateAsCropFieldPanel(cropField);
                GUIGame.addPanel(newPanel);
                GUIGame.deletePanel(this, newPanel);
                GUIGame.deletePanel(previousPanel, newPanel);
                MainScreen.updateImages();
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GUIGame.addPanel(newPanel);
            }

        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsBuyAnimalsPanel(AnimalPen animal){
        JLabel infoLabel = new JLabel("Buy 0 " + animal.getAnimal().getName() + "s for $0?");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setForeground(GUIGame.resources.tertiaryColor);
        infoLabel.setBounds(GUIGame.scaled(0, 44, 345, 63));
        infoLabel.setFont(GUIGame.sizedFont(30f));

        add(infoLabel);

        //Slider
        int maxAnimals = Math.min((int)(Farm.getMoney() / animal.getAnimal().getbuyPrice()), (int) (animal.getCapacity() - animal.getAnimal().getCurrentCount()));
        JSlider animalSlider = new JSlider(JSlider.HORIZONTAL, 0, maxAnimals, 0);
        animalSlider.setBounds(GUIGame.scaled(51, 108, 241, 20));
        animalSlider.setOpaque(false);
        add(animalSlider);

        JLabel minValue = new JLabel("0");
        JLabel maxValue = new JLabel("" + maxAnimals);
        minValue.setForeground(GUIGame.resources.tertiaryColor);
        maxValue.setForeground(GUIGame.resources.tertiaryColor);
        minValue.setFont(GUIGame.sizedFont(30f));
        maxValue.setFont(GUIGame.sizedFont(30f));
        minValue.setBounds(GUIGame.scaled(30, 103, 30, 30));
        maxValue.setBounds(GUIGame.scaled(297, 103, 40, 30));
        add(minValue);
        add(maxValue);
        //buttons
        JButton backButton = new JButton("Back");
        JButton buyButton = new JButton("Buy");

        JPanel thisPanel = this;

        buyButton.setBounds(GUIGame.scaled(15, 147, 145, 30));
        backButton.setBounds(GUIGame.scaled(185, 147, 145, 30));

        add(buyButton);
        add(backButton);

        // Slider listener
        animalSlider.addChangeListener(e -> {
            if (animalSlider.getValue() == 1)
                infoLabel.setText("Buy 1 " + animal.getAnimal().getName() + " for $" + animal.getAnimal().getbuyPrice());
            else
                infoLabel.setText("Buy " + animalSlider.getValue() + " " + animal.getAnimal().getName() + "s for $" + animal.getAnimal().getbuyPrice() * animalSlider.getValue());
        });

        //buy button listener
        buyButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            animal.getAnimal().addAnimals(animalSlider.getValue());
            Farm.lessMoney(animal.getAnimal().getbuyPrice() * animalSlider.getValue());
            MainScreen.update();
            GUIGame.deletePanel(thisPanel, previousPanel);
        });

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsInventoryPanel(){
        JButton seedsButton = new JButton("Seeds");
        JButton itemsButton = new JButton("Items");
        JButton exitButton = new JButton("Exit");

        seedsButton.setBounds(GUIGame.scaled(73, 55, 203, 34));
        itemsButton.setBounds(GUIGame.scaled(73, 97, 203, 34));
        exitButton.setBounds(GUIGame.scaled(73, 140, 203, 34));

        add(seedsButton);
        add(itemsButton);
        add(exitButton);

        JPanel thisPanel = this;

        seedsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            LargePanel newPanel = new LargePanel(thisPanel, "View seeds");
            newPanel.designateAsViewSeedsPanel();
            GUIGame.addPanel(newPanel);
        });

        itemsButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            LargePanel newPanel = new LargePanel(thisPanel, "View items");
            newPanel.designateAsViewItemsPanel();
            GUIGame.addPanel(newPanel);
        });

        exitButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }


    public void designateAsTendToFarmPanel(){
        // i hate this, but this seems like the easiest way to get multiple *centered* lines of text
        JLabel line1 = new JLabel("Do you want to tend to your");
        JLabel line2 = new JLabel("farm's land? This will use an");
        JLabel line3 = new JLabel("Action and will increase your");
        JLabel line4 = new JLabel("farm's condition by 50%");


        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line4.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GUIGame.scaled(0, 57, 345, 20));
        line2.setBounds(GUIGame.scaled(0, 77, 345, 20));
        line3.setBounds(GUIGame.scaled(0, 97, 345, 20));
        line4.setBounds(GUIGame.scaled(0, 117, 345, 20));
        line1.setFont(GUIGame.sizedFont(30f));
        line2.setFont(GUIGame.sizedFont(30f));
        line3.setFont(GUIGame.sizedFont(30f));
        line4.setFont(GUIGame.sizedFont(30f));
        line1.setForeground(GUIGame.resources.secondaryColor);
        line2.setForeground(GUIGame.resources.secondaryColor);
        line3.setForeground(GUIGame.resources.secondaryColor);
        line4.setForeground(GUIGame.resources.secondaryColor);

        add(line1);
        add(line2);
        add(line3);
        add(line4);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton tendToFarmButton = new JButton("Tend to farm");

        JPanel thisPanel = this;

        tendToFarmButton.setBounds(GUIGame.scaled(15, 147, 145, 30));
        backButton.setBounds(GUIGame.scaled(185, 147, 145, 30));

        add(tendToFarmButton);
        add(backButton);

        tendToFarmButton.addActionListener(e -> {
            if (FarmerActions.getRemainingActions() > 0) {
                FarmerActions.tendToFarm();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), Farm.getFarmerName() + "'s farmhouse");
                newPanel.designateAsHousePanel();
                GUIGame.addPanel(newPanel);
                GUIGame.deletePanel(thisPanel, newPanel);
                GUIGame.deletePanel(previousPanel, newPanel);
                MainScreen.update();
            }else{
                SmallPanel newPanel = new SmallPanel(thisPanel, "No actions remaining");
                newPanel.designateAsNoActionsPanel();
                GUIGame.addPanel(newPanel);
            }
        });


        // Exit button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsLottoTicketPanel(){
        JLabel label = new JLabel("You won $" + Farm.useLottoTicket());
        MainScreen.update();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(GUIGame.resources.tertiaryColor);
        label.setBounds(GUIGame.scaled(0, 44, 345, 139));
        label.setFont(GUIGame.sizedFont(35f));

        JButton okButton = new JButton("OK");
        okButton.setBounds(GUIGame.scaled(15, 147, 315, 30));
        add(label);
        add(okButton);

        JPanel thisPanel = this;

        okButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);

            MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), Farm.getFarmerName() + "'s farmhouse");
            newPanel.designateAsHousePanel();
            GUIGame.addPanel(newPanel);
            GUIGame.deletePanel(thisPanel, newPanel);
            GUIGame.deletePanel(previousPanel, newPanel);
        });
    }

    public void designateAsFeedPanel(AnimalPen animal){
        // JTextArea doesn't seem to have the ability to center text :(
        // also i hate this
        JLabel line1 = new JLabel("Would you like to feed all of your");
        JLabel line2 = new JLabel("animals hay (" + Item.HAY.getAmount() + ") or breeding compound");
        JLabel line3 = new JLabel("(" + Item.BREEDING_COMPOUND.getAmount() + "). This uses an action");
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GUIGame.scaled(0, 67, 345, 20));
        line2.setBounds(GUIGame.scaled(0, 87, 345, 20));
        line3.setBounds(GUIGame.scaled(0, 107, 345, 20));
        line1.setFont(GUIGame.sizedFont(30f));
        line2.setFont(GUIGame.sizedFont(30f));
        line3.setFont(GUIGame.sizedFont(30f));
        line1.setForeground(GUIGame.resources.secondaryColor);
        line2.setForeground(GUIGame.resources.secondaryColor);
        line3.setForeground(GUIGame.resources.secondaryColor);

        add(line1);
        add(line2);
        add(line3);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton hayButton = new JButton("Hay");
        JButton breedingCompoundButton = new JButton("Breeding Compound");

        hayButton.setBounds(GUIGame.scaled(15, 147, 69, 30));
        breedingCompoundButton.setBounds(GUIGame.scaled(94, 147, 174, 30));
        backButton.setBounds(GUIGame.scaled(278, 147, 52, 30));

        add(backButton);
        add(breedingCompoundButton);
        add(hayButton);

        JPanel thisPanel = this;

        // Hay button listener
        if (Item.HAY.getAmount() > 0) {
            hayButton.addActionListener(e -> {
                if (thisPanel != GUIGame.getActivePanel())
                    return;
                if (FarmerActions.getRemainingActions() > 0) {
                    FarmerActions.feedAnimalsHay();
                    MainScreen.update();
                    MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), animal.getAnimal().getName() + " pen");
                    newPanel.designateAsAnimalPenPanel(animal);
                    GUIGame.addPanel(newPanel);
                    GUIGame.deletePanel(thisPanel, previousPanel);
                    GUIGame.deletePanel(previousPanel, newPanel);
                }
            });
        }

        if (Item.BREEDING_COMPOUND.getAmount() > 0){
            breedingCompoundButton.addActionListener(e -> {
                if (thisPanel != GUIGame.getActivePanel())
                    return;
                if (FarmerActions.getRemainingActions() > 0) {
                    FarmerActions.useBreedingCompound();
                    MainScreen.update();
                    MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), animal.getAnimal().getName() + " pen");
                    newPanel.designateAsAnimalPenPanel(animal);
                    GUIGame.addPanel(newPanel);
                    GUIGame.deletePanel(thisPanel, previousPanel);
                    GUIGame.deletePanel(previousPanel, newPanel);
                }
            });
        }

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsPlayPanel(AnimalPen animal){
        // JTextArea doesn't seem to have the ability to center text :(
        // also i hate this
        JLabel line1 = new JLabel("Would you like to play with all of your");
        JLabel line2 = new JLabel("animals or give them all treats");
        JLabel line3 = new JLabel("(you have " + Item.ANIMAL_TREATS.getAmount() + ") This uses an action");
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        line3.setHorizontalAlignment(SwingConstants.CENTER);
        line1.setBounds(GUIGame.scaled(0, 67, 345, 20));
        line2.setBounds(GUIGame.scaled(0, 87, 345, 20));
        line3.setBounds(GUIGame.scaled(0, 107, 345, 20));
        line1.setFont(GUIGame.sizedFont(28f));
        line2.setFont(GUIGame.sizedFont(28f));
        line3.setFont(GUIGame.sizedFont(28f));
        line1.setForeground(GUIGame.resources.secondaryColor);
        line2.setForeground(GUIGame.resources.secondaryColor);
        line3.setForeground(GUIGame.resources.secondaryColor);

        add(line1);
        add(line2);
        add(line3);

        // Buttons
        JButton backButton = new JButton("Back");
        JButton playButton = new JButton("Play");
        JButton giveTreatsButton = new JButton("Give treats");

        playButton.setBounds(GUIGame.scaled(15, 147, 69, 30));
        giveTreatsButton.setBounds(GUIGame.scaled(94, 147, 174, 30));
        backButton.setBounds(GUIGame.scaled(278, 147, 52, 30));

        add(backButton);
        add(giveTreatsButton);
        add(playButton);

        JPanel thisPanel = this;

        // play button listener
        playButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            if (FarmerActions.getRemainingActions() > 0) {
                FarmerActions.playWithAnimals();
                MainScreen.update();
                MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), animal.getAnimal().getName() + " pen");
                newPanel.designateAsAnimalPenPanel(animal);
                GUIGame.addPanel(newPanel);
                GUIGame.deletePanel(thisPanel, previousPanel);
                GUIGame.deletePanel(previousPanel, newPanel);
            }
        });

        if (Item.ANIMAL_TREATS.getAmount() > 0){
            giveTreatsButton.addActionListener(e -> {
                if (thisPanel != GUIGame.getActivePanel())
                    return;
                if (FarmerActions.getRemainingActions() > 0) {
                    FarmerActions.feedAnimalsTreats();
                    MainScreen.update();
                    MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), animal.getAnimal().getName() + " pen");
                    newPanel.designateAsAnimalPenPanel(animal);
                    GUIGame.addPanel(newPanel);
                    GUIGame.deletePanel(thisPanel, previousPanel);
                    GUIGame.deletePanel(previousPanel, newPanel);
                }
            });
        }

        // Back button listener
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }

    public void designateAsNoActionsPanel(){
        JLabel warningLabel = new JLabel("You have no actions remaining");
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setVerticalAlignment(SwingConstants.CENTER);
        warningLabel.setForeground(GUIGame.resources.tertiaryColor);
        warningLabel.setBounds(GUIGame.scaled(0, 44, 345, 139));
        warningLabel.setFont(GUIGame.sizedFont(35f));
        JButton okButton = new JButton("OK");
        okButton.setBounds(GUIGame.scaled(15, 147, 315, 30));
        add(warningLabel);
        add(okButton);

        JPanel thisPanel = this;

        okButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });
    }
}
