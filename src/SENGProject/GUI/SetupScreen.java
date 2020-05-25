package SENGProject.GUI;

import SENGProject.Farm.Farm;
import SENGProject.Farm.FarmType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * A class for the attributes and methods of the setup screen 
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class SetupScreen{
    private static JPanel panel;

    /**
     * Creates the setup screen 
     */
    public static void createSetupScreen(){

        panel = new JPanel();
        panel.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.setLayout(null);

        Color primaryColor = new Color(255, 183, 61);

        // "Create your farm!" title
        JLabel title = new JLabel("Create your new farm!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(GUIGame.sizedFont(40f * 1.69f));
        title.setForeground(primaryColor);
        title.setBounds(GUIGame.scaled(100), GUIGame.scaled(100), GUIGame.scaled(600), GUIGame.scaled(40));
        panel.add(title);

        // Day slider
        JSlider daySlider = new JSlider(JSlider.HORIZONTAL, 5, 10, 7);
        daySlider.setBounds(GUIGame.scaled(200), GUIGame.scaled(200), GUIGame.scaled(400), GUIGame.scaled(20));
        daySlider.setOpaque(false);
        JLabel maxValue = new JLabel("10");
        JLabel minValue = new JLabel("5");
        maxValue.setForeground(primaryColor);
        minValue.setForeground(primaryColor);
        maxValue.setFont(GUIGame.sizedFont(25f * 1.69f));
        minValue.setFont(GUIGame.sizedFont(25f * 1.69f));
        maxValue.setBounds(GUIGame.scaled(605), GUIGame.scaled(195), GUIGame.scaled(44), GUIGame.scaled(30));
        minValue.setBounds(GUIGame.scaled(181), GUIGame.scaled(195), GUIGame.scaled(44), GUIGame.scaled(30));
        panel.add(daySlider);
        panel.add(maxValue);
        panel.add(minValue);

        //Day slider label
        JLabel dayLabel = new JLabel("Game Length: 7 days");
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(GUIGame.sizedFont(27f * 1.69f));
        dayLabel.setForeground(primaryColor);
        dayLabel.setBounds(GUIGame.scaled(215), GUIGame.scaled(168), GUIGame.scaled(370), GUIGame.scaled(30));
        panel.add(dayLabel);

        // Farmer name label
        JLabel farmerNameLabel = new JLabel("Farmer Name");
        farmerNameLabel.setFont(GUIGame.sizedFont(27f * 1.69f));
        farmerNameLabel.setForeground(primaryColor);
        farmerNameLabel.setBounds(GUIGame.scaled(151), GUIGame.scaled(253), GUIGame.scaled(180), GUIGame.scaled(30));
        panel.add(farmerNameLabel);

        // Farmer name text field
        // TODO: Should we ignore leading and trailing spaces?
        JTextField farmerNameTextField = new JTextField();
        farmerNameTextField.setOpaque(false);
        farmerNameTextField.setBorder(new LineBorder(primaryColor, 0));
        farmerNameTextField.setBounds(GUIGame.scaled(340), GUIGame.scaled(255), GUIGame.scaled(324), GUIGame.scaled(25));
        farmerNameTextField.setForeground(primaryColor);
        farmerNameTextField.setFont(GUIGame.sizedFont(20f * 1.69f));
        JLabel farmerNameTextBackground = new JLabel(GUIGame.resources.textFieldBackground);
        farmerNameTextBackground.setBounds(GUIGame.scaled(332), GUIGame.scaled(255), GUIGame.scaled(332), GUIGame.scaled(25));
        JLabel warningLabel = new JLabel("must be 3-15 character only containing letters and spaces");
        warningLabel.setFont(GUIGame.sizedFont(10f * 1.69f));
        warningLabel.setForeground(new Color(255, 0, 0));
        warningLabel.setBounds(GUIGame.scaled(332), GUIGame.scaled(280), GUIGame.scaled(332), GUIGame.scaled(15));
        panel.add(warningLabel);
        panel.add(farmerNameTextField);
        panel.add(farmerNameTextBackground);

        // Farm name label
        JLabel farmNameLabel = new JLabel("Farm Name");
        farmNameLabel.setFont(GUIGame.sizedFont(27f * 1.69f));
        farmNameLabel.setForeground(primaryColor);
        farmNameLabel.setBounds(GUIGame.scaled(165), GUIGame.scaled(310), GUIGame.scaled(140), GUIGame.scaled(30));
        panel.add(farmNameLabel);

        // Farm name text field
        JTextField farmNameTextField = new JTextField();
        farmNameTextField.setOpaque(false);
        farmNameTextField.setBorder(new LineBorder(primaryColor, 0));
        farmNameTextField.setBounds(GUIGame.scaled(340), GUIGame.scaled(312), GUIGame.scaled(324), GUIGame.scaled(25));
        farmNameTextField.setForeground(primaryColor);
        farmNameTextField.setFont(GUIGame.sizedFont(20f * 1.69f));
        JLabel farmNameTextBackground = new JLabel(GUIGame.resources.textFieldBackground);
        farmNameTextBackground.setBounds(GUIGame.scaled(332), GUIGame.scaled(312), GUIGame.scaled(332), GUIGame.scaled(25));
        panel.add(farmNameTextField);
        panel.add(farmNameTextBackground);

        // Farm Type label
        JLabel farmTypeLabel = new JLabel("Farm type");
        farmTypeLabel.setFont(GUIGame.sizedFont(27f * 1.69f));
        farmTypeLabel.setForeground(primaryColor);
        farmTypeLabel.setBounds(GUIGame.scaled(200), GUIGame.scaled(366), GUIGame.scaled(140), GUIGame.scaled(30));
        panel.add(farmTypeLabel);

        // Farm type combo box
        //TODO: Make this a bit prettier
        JComboBox farmTypeComboBox = new JComboBox();
        for (FarmType farmType : FarmType.values()){
            farmTypeComboBox.addItem(farmType.getName());
        }
        farmTypeComboBox.setFont(GUIGame.sizedFont(20f * 1.69f));
        farmTypeComboBox.setForeground(primaryColor);
        ((JLabel)farmTypeComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        farmTypeComboBox.setBackground(new Color(110, 89, 53));
        farmTypeComboBox.setBounds(GUIGame.scaled(385), GUIGame.scaled(366), GUIGame.scaled(225), GUIGame.scaled(25));
        panel.add(farmTypeComboBox);

        // Farm Type name label
        JLabel farmTypeNameLabel = new JLabel(FarmType.SUBSIDISED_FARM.getName() + ":");
        farmTypeNameLabel.setFont(GUIGame.sizedFont(27f * 1.69f));
        farmTypeNameLabel.setForeground(primaryColor);
        farmTypeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        farmTypeNameLabel.setBounds(GUIGame.scaled(100), GUIGame.scaled(417), GUIGame.scaled(250), GUIGame.scaled(30));
        panel.add(farmTypeNameLabel);

        // Farm Type Description label
        JTextArea farmTypeDescriptionLabel = new JTextArea(FarmType.SUBSIDISED_FARM.getDescription());
        farmTypeDescriptionLabel.setOpaque(false);
        farmTypeDescriptionLabel.setLineWrap(true);
        farmTypeDescriptionLabel.setWrapStyleWord(true);
        farmTypeDescriptionLabel.setFont(GUIGame.sizedFont(25f * 1.69f));
        farmTypeDescriptionLabel.setForeground(primaryColor);
        farmTypeDescriptionLabel.setBounds(GUIGame.scaled(353), GUIGame.scaled(421), GUIGame.scaled(311), GUIGame.scaled(60));
        panel.add(farmTypeDescriptionLabel);

        // Begin Button
        JButton beginButton = new JButton("BEGIN!");
        beginButton.setOpaque(false);
        beginButton.setContentAreaFilled(false);
        beginButton.setBorderPainted(false);
        beginButton.setFocusPainted(false);
        beginButton.setFont(GUIGame.sizedFont(50f * 1.69f));
        beginButton.setForeground(primaryColor);
        beginButton.setBounds(GUIGame.scaled(250), GUIGame.scaled(490), GUIGame.scaled(300), GUIGame.scaled(60));
        JLabel beginButtonBackground = new JLabel(GUIGame.resources.beginButtonBackground);
        beginButtonBackground.setBounds(GUIGame.scaled(250), GUIGame.scaled(490), GUIGame.scaled(300), GUIGame.scaled(60));
        panel.add(beginButton);
        panel.add(beginButtonBackground);

        // Listeners
        daySlider.addChangeListener(e -> dayLabel.setText("Game Length: " + daySlider.getValue() + " days"));

        farmerNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enforceFarmerNameValidity(farmerNameTextField, warningLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enforceFarmerNameValidity(farmerNameTextField, warningLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enforceFarmerNameValidity(farmerNameTextField, warningLabel);
            }
        });

        farmTypeComboBox.addItemListener(e -> {
            int newIndex = farmTypeComboBox.getSelectedIndex();
            farmTypeNameLabel.setText(FarmType.values()[newIndex].getName() + ":");
            farmTypeDescriptionLabel.setText(FarmType.values()[newIndex].getDescription());
        });

        beginButton.addActionListener(e -> {
            if (warningLabel.isVisible())
                return;
            Farm.createFarm(daySlider.getValue(), farmNameTextField.getText(), farmerNameTextField.getText(), FarmType.values()[farmTypeComboBox.getSelectedIndex()]);
            GUIGame.begin();
        });


        // Background
        JLabel background = new JLabel(GUIGame.resources.setupBackground);
        background.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(background);
        JLabel test = new JLabel(GUIGame.resources.farmBackground);
        test.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(test);
    }

    private static void enforceFarmerNameValidity(JTextField farmerName, JLabel warningLabel){
        warningLabel.setVisible(!farmerNameValid(farmerName.getText()));
    }

    private static boolean farmerNameValid(String name){
        return Pattern.matches("[a-zA-Z ]{3,15}", name);
    }

    /**
     * 
     * @return the panel 
     */
    public static JPanel getPanel() {
        return panel;
    }
}
