import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Pattern;

public class SetupScreen{
    private static JPanel panel;

    public static void createSetupScreen(){

        panel = new JPanel();
        panel.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.setLayout(null);

        Color primaryColor = new Color(50, 50, 51);

        // "Create your farm!" title
        JLabel title = new JLabel("Create your new farm!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(GraphicalGame.sizedFont(40f * 1.69f));
        title.setForeground(primaryColor);
        title.setBounds(GraphicalGame.scaled(100), GraphicalGame.scaled(100), GraphicalGame.scaled(600), GraphicalGame.scaled(40));
        panel.add(title);

        // Day slider
        JSlider daySlider = new JSlider(JSlider.HORIZONTAL, 5, 10, 7);
        daySlider.setBounds(GraphicalGame.scaled(200), GraphicalGame.scaled(200), GraphicalGame.scaled(400), GraphicalGame.scaled(20));
        daySlider.setOpaque(false);
        JLabel maxValue = new JLabel("10");
        JLabel minValue = new JLabel("5");
        maxValue.setForeground(primaryColor);
        minValue.setForeground(primaryColor);
        maxValue.setFont(GraphicalGame.sizedFont(25f * 1.69f));
        minValue.setFont(GraphicalGame.sizedFont(25f * 1.69f));
        maxValue.setBounds(GraphicalGame.scaled(605), GraphicalGame.scaled(195), GraphicalGame.scaled(44), GraphicalGame.scaled(30));
        minValue.setBounds(GraphicalGame.scaled(181), GraphicalGame.scaled(195), GraphicalGame.scaled(44), GraphicalGame.scaled(30));
        panel.add(daySlider);
        panel.add(maxValue);
        panel.add(minValue);

        //Day slider label
        JLabel dayLabel = new JLabel("Game Length: 7 days");
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(GraphicalGame.sizedFont(27f * 1.69f));
        dayLabel.setForeground(primaryColor);
        dayLabel.setBounds(GraphicalGame.scaled(215), GraphicalGame.scaled(168), GraphicalGame.scaled(370), GraphicalGame.scaled(30));
        panel.add(dayLabel);

        // Farmer name label
        JLabel farmerNameLabel = new JLabel("Farmer Name");
        farmerNameLabel.setFont(GraphicalGame.sizedFont(27f * 1.69f));
        farmerNameLabel.setForeground(primaryColor);
        farmerNameLabel.setBounds(GraphicalGame.scaled(151), GraphicalGame.scaled(253), GraphicalGame.scaled(180), GraphicalGame.scaled(30));
        panel.add(farmerNameLabel);

        // Farmer name text field
        // TODO: Should we ignore leading and trailing spaces?
        JTextField farmerNameTextField = new JTextField();
        farmerNameTextField.setOpaque(false);
        farmerNameTextField.setBorder(new LineBorder(primaryColor, 0));
        farmerNameTextField.setBounds(GraphicalGame.scaled(340), GraphicalGame.scaled(255), GraphicalGame.scaled(324), GraphicalGame.scaled(25));
        farmerNameTextField.setForeground(primaryColor);
        farmerNameTextField.setFont(GraphicalGame.sizedFont(20f * 1.69f));
        JLabel farmerNameTextBackground = new JLabel(GraphicalGame.resources.textFieldBackground);
        farmerNameTextBackground.setBounds(GraphicalGame.scaled(332), GraphicalGame.scaled(255), GraphicalGame.scaled(332), GraphicalGame.scaled(25));
        JLabel warningLabel = new JLabel("must be 3-15 character only containing letters and spaces");
        warningLabel.setFont(GraphicalGame.sizedFont(10f * 1.69f));
        warningLabel.setForeground(new Color(255, 0, 0));
        warningLabel.setBounds(GraphicalGame.scaled(332), GraphicalGame.scaled(280), GraphicalGame.scaled(332), GraphicalGame.scaled(15));
        panel.add(warningLabel);
        panel.add(farmerNameTextField);
        panel.add(farmerNameTextBackground);

        // Farm name label
        JLabel farmNameLabel = new JLabel("Farm Name");
        farmNameLabel.setFont(GraphicalGame.sizedFont(27f * 1.69f));
        farmNameLabel.setForeground(primaryColor);
        farmNameLabel.setBounds(GraphicalGame.scaled(165), GraphicalGame.scaled(310), GraphicalGame.scaled(140), GraphicalGame.scaled(30));
        panel.add(farmNameLabel);

        // Farm name text field
        JTextField farmNameTextField = new JTextField();
        farmNameTextField.setOpaque(false);
        farmNameTextField.setBorder(new LineBorder(primaryColor, 0));
        farmNameTextField.setBounds(GraphicalGame.scaled(340), GraphicalGame.scaled(312), GraphicalGame.scaled(324), GraphicalGame.scaled(25));
        farmNameTextField.setForeground(primaryColor);
        farmNameTextField.setFont(GraphicalGame.sizedFont(20f * 1.69f));
        JLabel farmNameTextBackground = new JLabel(GraphicalGame.resources.textFieldBackground);
        farmNameTextBackground.setBounds(GraphicalGame.scaled(332), GraphicalGame.scaled(312), GraphicalGame.scaled(332), GraphicalGame.scaled(25));
        panel.add(farmNameTextField);
        panel.add(farmNameTextBackground);

        // Farm Type label
        JLabel farmTypeLabel = new JLabel("Farm type");
        farmTypeLabel.setFont(GraphicalGame.sizedFont(27f * 1.69f));
        farmTypeLabel.setForeground(primaryColor);
        farmTypeLabel.setBounds(GraphicalGame.scaled(200), GraphicalGame.scaled(366), GraphicalGame.scaled(140), GraphicalGame.scaled(30));
        panel.add(farmTypeLabel);

        // Farm type combo box
        //TODO: Make this a bit prettier
        JComboBox farmTypeComboBox = new JComboBox();
        for (FarmType farmType : FarmType.values()){
            farmTypeComboBox.addItem(farmType.getName());
        }
        farmTypeComboBox.setFont(GraphicalGame.sizedFont(20f * 1.69f));
        farmTypeComboBox.setForeground(primaryColor);
        ((JLabel)farmTypeComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        farmTypeComboBox.setBackground(new Color(110, 89, 53));
        farmTypeComboBox.setBounds(GraphicalGame.scaled(385), GraphicalGame.scaled(366), GraphicalGame.scaled(225), GraphicalGame.scaled(25));
        panel.add(farmTypeComboBox);

        // Farm Type name label
        JLabel farmTypeNameLabel = new JLabel(FarmType.SUBSIDISED_FARM.getName() + ":");
        farmTypeNameLabel.setFont(GraphicalGame.sizedFont(27f * 1.69f));
        farmTypeNameLabel.setForeground(primaryColor);
        farmTypeNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        farmTypeNameLabel.setBounds(GraphicalGame.scaled(100), GraphicalGame.scaled(417), GraphicalGame.scaled(250), GraphicalGame.scaled(30));
        panel.add(farmTypeNameLabel);

        // Farm Type Description label
        JTextArea farmTypeDescriptionLabel = new JTextArea(FarmType.SUBSIDISED_FARM.getDescription());
        farmTypeDescriptionLabel.setOpaque(false);
        farmTypeDescriptionLabel.setLineWrap(true);
        farmTypeDescriptionLabel.setWrapStyleWord(true);
        farmTypeDescriptionLabel.setFont(GraphicalGame.sizedFont(25f * 1.69f));
        farmTypeDescriptionLabel.setForeground(primaryColor);
        farmTypeDescriptionLabel.setBounds(GraphicalGame.scaled(353), GraphicalGame.scaled(421), GraphicalGame.scaled(311), GraphicalGame.scaled(60));
        panel.add(farmTypeDescriptionLabel);

        // Begin Button
        JButton beginButton = new JButton("BEGIN!");
        beginButton.setOpaque(false);
        beginButton.setContentAreaFilled(false);
        beginButton.setBorderPainted(false);
        beginButton.setFocusPainted(false);
        beginButton.setFont(GraphicalGame.sizedFont(50f * 1.69f));
        beginButton.setForeground(primaryColor);
        beginButton.setBounds(GraphicalGame.scaled(250), GraphicalGame.scaled(490), GraphicalGame.scaled(300), GraphicalGame.scaled(60));
        JLabel beginButtonBackground = new JLabel(GraphicalGame.resources.beginButtonBackground);
        beginButtonBackground.setBounds(GraphicalGame.scaled(250), GraphicalGame.scaled(490), GraphicalGame.scaled(300), GraphicalGame.scaled(60));
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
            GraphicalGame.begin();
        });


        // Background
        JLabel background = new JLabel(GraphicalGame.resources.setupBackground);
        background.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(background);
        JLabel test = new JLabel(GraphicalGame.resources.farmBackground);
        test.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(test);
    }

    private static void enforceFarmerNameValidity(JTextField farmerName, JLabel warningLabel){
        warningLabel.setVisible(!farmerNameValid(farmerName.getText()));
    }

    private static boolean farmerNameValid(String name){
        return Pattern.matches("[a-zA-Z ]{3,15}", name);
    }

    public static JPanel getPanel() {
        return panel;
    }
}
