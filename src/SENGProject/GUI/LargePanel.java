package SENGProject.GUI;

import SENGProject.Farm.Crop;
import SENGProject.Farm.Farm;
import SENGProject.Farm.Item;

import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * Class for making large panels
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class LargePanel extends JPanel{
    private final JPanel previousPanel;

    /**
     * Creates a large panel
     * @param previousPanel A JPanel which is the panel that came before this
     * @param title a string which is the title of the panel 
     */
    public LargePanel(JPanel previousPanel, String title){
        super();
        this.previousPanel = previousPanel;
        setBounds(GUIGame.scaled(200, 103, 400, 460));
        setLayout(null);
        setBorder(new LineBorder(GUIGame.resources.secondaryColor, GUIGame.scaled(3)));
        setBackground(GUIGame.resources.primaryColor);

        // The back button is always in the same position in this panel
        JButton backButton = new JButton("Back");
        backButton.setBounds(GUIGame.scaled(318, 0, 82, 40));
        add(backButton);

        JPanel thisPanel = this;
        backButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            GUIGame.deletePanel(thisPanel, previousPanel);
        });

        //TODO: Maybe add a picture background later but for now this is fine
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(GUIGame.scaled(0, 0, 400, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(GUIGame.resources.tertiaryColor);
        titleLabel.setBackground(GUIGame.resources.secondaryColor);
        titleLabel.setFont(GUIGame.sizedFont(40f));
        titleLabel.setOpaque(true);
        add(titleLabel);
    }

    /**
     * designates each cropfield a panel
     */
    public void designateAsPlantPanel(int cropField){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = cropOption(Crop.values()[i], cropField);
            newPanel.setBounds(GUIGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }

    }

    /**
     * designates each cropfield a panel for the store
     */
    public void designateAsBuySeedsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyCropOption(Crop.values()[i], true);
            newPanel.setBounds(GUIGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    /**
     * designates each item a panel for the store
     */
    public void designateAsBuyItemsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyItemOption(Item.values()[i], true);
            newPanel.setBounds(GUIGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    /**
     * designates each seed type a panel for the store
     */
    public void designateAsViewSeedsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyCropOption(Crop.values()[i], false);
            newPanel.setBounds(GUIGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    /**
     * designates each item a panel for the store
     */
    public void designateAsViewItemsPanel(){
        for (int i = 0; i < 6; i++){
            JPanel newPanel = buyItemOption(Item.values()[i], false);
            newPanel.setBounds(GUIGame.scaled(8, 45 + (69 * i), 384, 64));
            add(newPanel);
        }
    }

    private JPanel buyItemOption(Item item, boolean buyable){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(GUIGame.resources.quaternaryColor);
        panel.setBorder(new LineBorder(GUIGame.resources.secondaryColor, GUIGame.scaled(2)));


        JLabel youHaveLabel = new JLabel("You have");
        youHaveLabel.setBounds(GUIGame.scaled(0, 0, 57, 15));
        youHaveLabel.setForeground(GUIGame.resources.tertiaryColor);
        youHaveLabel.setFont(GUIGame.sizedFont(20f));
        youHaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        youHaveLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(youHaveLabel);

        JLabel amountLabel = new JLabel("" + item.getAmount());
        amountLabel.setBounds(GUIGame.scaled(0, 15, 57, 49));
        amountLabel.setForeground(GUIGame.resources.tertiaryColor);
        amountLabel.setFont(GUIGame.sizedFont(60f));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(amountLabel);

        JLabel itemLabel = new JLabel(item.getName());
        itemLabel.setBounds(GUIGame.scaled(57, 0, 263, 24));
        itemLabel.setForeground(GUIGame.resources.tertiaryColor);
        itemLabel.setFont(GUIGame.sizedFont(40f));
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(itemLabel);

        JTextArea itemDescription = new JTextArea(item.getDescription());
        itemDescription.setOpaque(false);
        itemDescription.setLineWrap(true);
        itemDescription.setWrapStyleWord(true);
        itemDescription.setForeground(GUIGame.resources.tertiaryColor);
        itemDescription.setFont(GUIGame.sizedFont(20f));
        itemDescription.setBounds(GUIGame.scaled(67, 24, 255, 40));
        panel.add(itemDescription);

        JButton buyButton = new JButton("Buy ($" + item.getPrice() + ")");

        buyButton.setBounds(GUIGame.scaled(320, 0, 64, 64));

        JPanel thisPanel = this;

        buyButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            item.buy();
            MainScreen.update();
            // Regenerate this panel
            LargePanel newPanel = new LargePanel(previousPanel, "Buy items");
            newPanel.designateAsBuyItemsPanel();
            GUIGame.addPanel(newPanel);
            GUIGame.deletePanel(thisPanel, newPanel);
        });

        if(Farm.getMoney() >= item.getPrice() && buyable)
            panel.add(buyButton);

        return panel;
    }

    private JPanel buyCropOption(Crop crop, boolean buyable){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(GUIGame.resources.quaternaryColor);
        panel.setBorder(new LineBorder(GUIGame.resources.secondaryColor, GUIGame.scaled(2)));


        JLabel cropName = new JLabel(crop.getName());
        cropName.setBounds(GUIGame.scaled(5, 0, 77, 20));
        cropName.setForeground(GUIGame.resources.tertiaryColor);
        cropName.setFont(GUIGame.sizedFont(30f));
        cropName.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(cropName);

        JLabel amountLabel = new JLabel("" + crop.getSeedAmount());
        amountLabel.setBounds(GUIGame.scaled(0, 20, 57, 49));
        amountLabel.setForeground(GUIGame.resources.tertiaryColor);
        amountLabel.setFont(GUIGame.sizedFont(60f));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(amountLabel);

        JLabel cropDescription = new JLabel(crop.description());
        cropDescription.setBounds(GUIGame.scaled(77, 0, 243, 24));
        cropDescription.setForeground(GUIGame.resources.tertiaryColor);
        cropDescription.setFont(GUIGame.sizedFont(20f));
        cropDescription.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(cropDescription);

        JTextArea cropAbilities = new JTextArea(crop.abilities());
        cropAbilities.setOpaque(false);
        cropAbilities.setLineWrap(true);
        cropAbilities.setWrapStyleWord(true);
        cropAbilities.setForeground(GUIGame.resources.tertiaryColor);
        cropAbilities.setFont(GUIGame.sizedFont(20f));
        cropAbilities.setBounds(GUIGame.scaled(57, 20, 265, 44));
        panel.add(cropAbilities);

        JButton buyButton = new JButton("Buy ($" + crop.getBuyPrice() + ")");

        buyButton.setBounds(GUIGame.scaled(320, 0, 64, 64));

        JPanel thisPanel = this;

        buyButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            crop.buy();
            MainScreen.update();
            // Regenerate this panel
            LargePanel newPanel = new LargePanel(previousPanel, "Buy seeds");
            newPanel.designateAsBuySeedsPanel();
            GUIGame.addPanel(newPanel);
            GUIGame.deletePanel(thisPanel, newPanel);
        });

        if(Farm.getMoney() >= crop.getBuyPrice() && buyable)
            panel.add(buyButton);

        return panel;
    }

    private JPanel cropOption(Crop crop, int cropField){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(GUIGame.resources.quaternaryColor);
        panel.setBorder(new LineBorder(GUIGame.resources.secondaryColor, GUIGame.scaled(2)));


        JLabel youHaveLabel = new JLabel("You have");
        youHaveLabel.setBounds(GUIGame.scaled(0, 0, 57, 15));
        youHaveLabel.setForeground(GUIGame.resources.tertiaryColor);
        youHaveLabel.setFont(GUIGame.sizedFont(20f));
        youHaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        youHaveLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(youHaveLabel);

        JLabel amountLabel = new JLabel("" + crop.getSeedAmount());
        amountLabel.setBounds(GUIGame.scaled(0, 15, 57, 49));
        amountLabel.setForeground(GUIGame.resources.tertiaryColor);
        amountLabel.setFont(GUIGame.sizedFont(60f));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(amountLabel);

        JLabel cropLabel = new JLabel(crop.getName());
        cropLabel.setBounds(GUIGame.scaled(57, 0, 263, 24));
        cropLabel.setForeground(GUIGame.resources.tertiaryColor);
        cropLabel.setFont(GUIGame.sizedFont(40f));
        cropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(cropLabel);

        JLabel growthLabel = new JLabel((crop.getBaseGrowTime() == 1) ? "Growth time: 1 day" : "Growth time: " + crop.getBaseGrowTime() + " days");
        growthLabel.setBounds(GUIGame.scaled(57, 24, 263, 20));
        growthLabel.setForeground(GUIGame.resources.tertiaryColor);
        growthLabel.setFont(GUIGame.sizedFont(23f));
        growthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(growthLabel);

        JLabel saleLabel = new JLabel("Sale value (Before Modifiers): $" + crop.getBaseYield() * crop.getSellPrice());
        saleLabel.setBounds(GUIGame.scaled(57, 44, 263, 20));
        saleLabel.setForeground(GUIGame.resources.tertiaryColor);
        saleLabel.setFont(GUIGame.sizedFont(23f));
        saleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(saleLabel);

        JButton plantButton = new JButton("Plant");

        plantButton.setBounds(GUIGame.scaled(320, 0, 64, 64));

        JPanel thisPanel = this;

        plantButton.addActionListener(e -> {
            if (thisPanel != GUIGame.getActivePanel())
                return;
            Farm.getCropFields()[cropField].PlantCrop(crop);
            MainScreen.updateImages();
            // Regenerate the previous panel
            MediumPanel newPanel = new MediumPanel(MainScreen.getPanel(), "Crop field " + (cropField + 1));
            newPanel.designateAsCropFieldPanel(cropField);
            GUIGame.addPanel(newPanel);
            GUIGame.deletePanel(previousPanel, newPanel);
            GUIGame.deletePanel(this, newPanel);
        });

        if(crop.getSeedAmount() > 0)
            panel.add(plantButton);

        return panel;
    }
}
