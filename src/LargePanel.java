import javax.swing.*;
import javax.swing.border.LineBorder;

public class LargePanel extends JPanel{
    private final JPanel previousPanel;

    public LargePanel(JPanel previousPanel, String title){
        super();
        this.previousPanel = previousPanel;
        setBounds(GraphicalGame.scaled(200, 103, 400, 460));
        setLayout(null);
        setBorder(new LineBorder(GraphicalGame.resources.secondaryColor, GraphicalGame.scaled(3)));
        setBackground(GraphicalGame.resources.primaryColor);

        // The back button is always in the same position in this panel
        JButton backButton = new JButton("Back");
        backButton.setBounds(GraphicalGame.scaled(318, 0, 82, 40));
        add(backButton);

        JPanel thisPanel = this;
        backButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            GraphicalGame.deletePanel(thisPanel, previousPanel);
        });

        //TODO: Maybe add a picture background later but for now this is fine
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(GraphicalGame.scaled(0, 0, 400, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        titleLabel.setBackground(GraphicalGame.resources.secondaryColor);
        titleLabel.setFont(GraphicalGame.sizedFont(40f));
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    public void designateAsPlantPanel(int cropField){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = cropOption(Crop.values()[i], cropField);
            newPanel.setBounds(GraphicalGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }

    }

    public void designateAsBuySeedsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyCropOption(Crop.values()[i], true);
            newPanel.setBounds(GraphicalGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    public void designateAsBuyItemsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyItemOption(Item.values()[i], true);
            newPanel.setBounds(GraphicalGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    public void designateAsViewSeedsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyCropOption(Crop.values()[i], false);
            newPanel.setBounds(GraphicalGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    public void designateAsViewItemsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyItemOption(Item.values()[i], false);
            newPanel.setBounds(GraphicalGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    private JPanel buyItemOption(Item item, boolean buyable){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(GraphicalGame.resources.quaternaryColor);
        panel.setBorder(new LineBorder(GraphicalGame.resources.secondaryColor, GraphicalGame.scaled(2)));


        JLabel youHaveLabel = new JLabel("You have");
        youHaveLabel.setBounds(GraphicalGame.scaled(0, 0, 57, 15));
        youHaveLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        youHaveLabel.setFont(GraphicalGame.sizedFont(20f));
        youHaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        youHaveLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(youHaveLabel);

        JLabel amountLabel = new JLabel("" + item.getAmount());
        amountLabel.setBounds(GraphicalGame.scaled(0, 15, 57, 49));
        amountLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        amountLabel.setFont(GraphicalGame.sizedFont(60f));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(amountLabel);

        JLabel itemLabel = new JLabel(item.getName());
        itemLabel.setBounds(GraphicalGame.scaled(57, 0, 263, 24));
        itemLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        itemLabel.setFont(GraphicalGame.sizedFont(40f));
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(itemLabel);

        JTextArea itemDescription = new JTextArea(item.getDescription());
        itemDescription.setOpaque(false);
        itemDescription.setLineWrap(true);
        itemDescription.setWrapStyleWord(true);
        itemDescription.setForeground(GraphicalGame.resources.tertiaryColor);
        itemDescription.setFont(GraphicalGame.sizedFont(20f));
        itemDescription.setBounds(GraphicalGame.scaled(67, 24, 255, 40));
        panel.add(itemDescription);

        JButton buyButton = new JButton("Buy ($" + item.getPrice() + ")");

        buyButton.setBounds(GraphicalGame.scaled(320, 0, 64, 64));

        JPanel thisPanel = this;

        buyButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            item.buy();
            MainScreen.update();
            // Regenerate this panel
            LargePanel newPanel = new LargePanel(previousPanel, "Buy items");
            newPanel.designateAsBuyItemsPanel();
            GraphicalGame.addPanel(newPanel, previousPanel);
            GraphicalGame.deletePanel(thisPanel, newPanel);
        });

        if(Farm.money >= item.getPrice() && buyable)
            panel.add(buyButton);

        return panel;
    }

    private JPanel buyCropOption(Crop crop, boolean buyable){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(GraphicalGame.resources.quaternaryColor);
        panel.setBorder(new LineBorder(GraphicalGame.resources.secondaryColor, GraphicalGame.scaled(2)));


        JLabel cropName = new JLabel(crop.getName());
        cropName.setBounds(GraphicalGame.scaled(5, 0, 77, 20));
        cropName.setForeground(GraphicalGame.resources.tertiaryColor);
        cropName.setFont(GraphicalGame.sizedFont(30f));
        //cropName.setHorizontalAlignment(SwingConstants.CENTER);
        cropName.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(cropName);

        JLabel amountLabel = new JLabel("" + crop.getSeedAmount());
        amountLabel.setBounds(GraphicalGame.scaled(0, 20, 57, 49));
        amountLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        amountLabel.setFont(GraphicalGame.sizedFont(60f));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(amountLabel);

        JLabel cropDescription = new JLabel(crop.description());
        cropDescription.setBounds(GraphicalGame.scaled(77, 0, 243, 24));
        cropDescription.setForeground(GraphicalGame.resources.tertiaryColor);
        cropDescription.setFont(GraphicalGame.sizedFont(20f));
        cropDescription.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(cropDescription);

        JTextArea cropAbilities = new JTextArea(crop.abilities());
        cropAbilities.setOpaque(false);
        cropAbilities.setLineWrap(true);
        cropAbilities.setWrapStyleWord(true);
        cropAbilities.setForeground(GraphicalGame.resources.tertiaryColor);
        cropAbilities.setFont(GraphicalGame.sizedFont(20f));
        cropAbilities.setBounds(GraphicalGame.scaled(57, 20, 265, 44));
        panel.add(cropAbilities);

        JButton buyButton = new JButton("Buy ($" + crop.getBuyPrice() + ")");

        buyButton.setBounds(GraphicalGame.scaled(320, 0, 64, 64));

        JPanel thisPanel = this;

        buyButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            crop.buy();
            MainScreen.update();
            // Regenerate this panel
            LargePanel newPanel = new LargePanel(previousPanel, "Buy seeds");
            newPanel.designateAsBuySeedsPanel();
            GraphicalGame.addPanel(newPanel, previousPanel);
            GraphicalGame.deletePanel(thisPanel, newPanel);
        });

        if(Farm.money >= crop.getBuyPrice() && buyable)
            panel.add(buyButton);

        return panel;
    }

    private JPanel cropOption(Crop crop, int cropField){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(GraphicalGame.resources.quaternaryColor);
        panel.setBorder(new LineBorder(GraphicalGame.resources.secondaryColor, GraphicalGame.scaled(2)));


        JLabel youHaveLabel = new JLabel("You have");
        youHaveLabel.setBounds(GraphicalGame.scaled(0, 0, 57, 15));
        youHaveLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        youHaveLabel.setFont(GraphicalGame.sizedFont(20f));
        youHaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        youHaveLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(youHaveLabel);

        JLabel amountLabel = new JLabel("" + crop.getSeedAmount());
        amountLabel.setBounds(GraphicalGame.scaled(0, 15, 57, 49));
        amountLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        amountLabel.setFont(GraphicalGame.sizedFont(60f));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(amountLabel);

        JLabel cropLabel = new JLabel(crop.getName());
        cropLabel.setBounds(GraphicalGame.scaled(57, 0, 263, 24));
        cropLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        cropLabel.setFont(GraphicalGame.sizedFont(40f));
        cropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(cropLabel);

        JLabel growthLabel = new JLabel((crop.getBaseGrowTime() == 1) ? "Growth time: 1 day" : "Growth time: " + crop.getBaseGrowTime() + " days");
        growthLabel.setBounds(GraphicalGame.scaled(57, 24, 263, 20));
        growthLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        growthLabel.setFont(GraphicalGame.sizedFont(23f));
        growthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(growthLabel);

        JLabel saleLabel = new JLabel("Sale value (Before Modifiers): $" + crop.getBaseYield() * crop.getSellPrice());
        saleLabel.setBounds(GraphicalGame.scaled(57, 44, 263, 20));
        saleLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        saleLabel.setFont(GraphicalGame.sizedFont(23f));
        saleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(saleLabel);

        JButton plantButton = new JButton("Plant");

        plantButton.setBounds(GraphicalGame.scaled(320, 0, 64, 64));

        JPanel thisPanel = this;

        plantButton.addActionListener(e -> {
            if (thisPanel != GraphicalGame.getActivePanel())
                return;
            Farm.cropFields[cropField].PlantCrop(crop);
            MainScreen.updateImages();
            // Regenerate the previous panel
            MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + (cropField + 1));
            newPanel.designateAsCropFieldPanel(cropField);
            GraphicalGame.addPanel(newPanel, MainScreen.getPanel());
            GraphicalGame.deletePanel(previousPanel, newPanel);
            GraphicalGame.deletePanel(this, newPanel);
        });

        if(crop.getSeedAmount() > 0)
            panel.add(plantButton);

        return panel;
    }
}
