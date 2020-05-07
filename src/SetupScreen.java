import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SetupScreen{
    private static JPanel panel;

    public static void createSetupScreen(){

        panel = new JPanel();
        panel.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.setLayout(null);

        // "Create your farm!" title
        JLabel title = new JLabel("Create your new farm!");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(40f)));
        title.setForeground(new Color(255, 202, 112));
        //GraphicalGame.centerBounds(title, GraphicalGame.getWidth() / 2, GraphicalGame.scaled(120), GraphicalGame.scaled(600), GraphicalGame.scaled(40));
        title.setBounds(GraphicalGame.scaled(100), GraphicalGame.scaled(100), GraphicalGame.scaled(600), GraphicalGame.scaled(40));
        panel.add(title);

        // Day slider
        JSlider daySlider = new JSlider(JSlider.HORIZONTAL, 5, 10, 7);
        daySlider.setBounds(GraphicalGame.scaled(200), GraphicalGame.scaled(200), GraphicalGame.scaled(400), GraphicalGame.scaled(20));
        daySlider.setOpaque(false);
        JLabel maxValue = new JLabel("10");
        JLabel minValue = new JLabel("5");
        maxValue.setForeground(new Color(255, 202, 112));
        minValue.setForeground(new Color(255, 202, 112));
        maxValue.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(25f)));
        minValue.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(25f)));
        maxValue.setBounds(GraphicalGame.scaled(605), GraphicalGame.scaled(195), GraphicalGame.scaled(44), GraphicalGame.scaled(30));
        minValue.setBounds(GraphicalGame.scaled(181), GraphicalGame.scaled(195), GraphicalGame.scaled(44), GraphicalGame.scaled(30));
        panel.add(daySlider);
        panel.add(maxValue);
        panel.add(minValue);

        //Day slider label
        JLabel dayLabel = new JLabel("Game Length: 7 days");
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(GraphicalGame.sizedFont(GraphicalGame.scaled(27f)));
        dayLabel.setForeground(new Color(255, 202, 112));
        dayLabel.setBounds(GraphicalGame.scaled(215), GraphicalGame.scaled(168), GraphicalGame.scaled(370), GraphicalGame.scaled(30));

        // Listeners
        daySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dayLabel.setText("Game Length: " + daySlider.getValue() + " days");
            }
        });

        panel.add(dayLabel);


        // Background
        JLabel background = new JLabel(GraphicalGame.resources.setupBackground);
        background.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(background);
        JLabel test = new JLabel(GraphicalGame.resources.testBackgroundImage);
        test.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(test);
    }

    public static JPanel getPanel() {
        return panel;
    }
}
