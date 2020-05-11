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

        Item.GROWTH_COMPOUND.buy();

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
            FarmerActions.tendToCrop(Farm.cropFields[cropField], false);
            MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
            newPanel.designateAsCropFieldPanel(cropField);
            GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
            GraphicalGame.deletePanel(this, newPanel);
            GraphicalGame.deletePanel(previousPanel, newPanel);
        });

        // Growth compound button
        growthButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            FarmerActions.tendToCrop(Farm.cropFields[cropField], true);
            MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
            newPanel.designateAsCropFieldPanel(cropField);
            GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
            GraphicalGame.deletePanel(this, newPanel);
            GraphicalGame.deletePanel(previousPanel, newPanel);
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

        // Harvest button listener
        harvestButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            FarmerActions.harvestCrops();
            MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + cropField);
            newPanel.designateAsCropFieldPanel(cropField);
            GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
            GraphicalGame.deletePanel(this, newPanel);
            GraphicalGame.deletePanel(previousPanel, newPanel);
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
