import javax.swing.*;

/**
 * A class with the attributes and methods of the final score screen
 * @author Mitchell Veale and Ryan Bellamy 
 *
 */
public class ScoreScreen {

    private static JPanel panel;

    /**
     * Creates the final score screen
     */
    public static void createScoreScreen(){
        panel = new JPanel();
        panel.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.setLayout(null);

        JLabel endLabel = new JLabel("The End!");
        endLabel.setForeground(GUIGame.resources.tertiaryColor);
        endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endLabel.setFont(GUIGame.sizedFont(140f));
        endLabel.setBounds(GUIGame.scaled(0, 94, 800, 75));

        panel.add(endLabel);

        JLabel yourScoreLabel = new JLabel("Your score");
        yourScoreLabel.setForeground(GUIGame.resources.tertiaryColor);
        yourScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yourScoreLabel.setFont(GUIGame.sizedFont(60f));
        yourScoreLabel.setBounds(GUIGame.scaled(0, 248, 800, 50));

        panel.add(yourScoreLabel);

        JLabel scoreLabel = new JLabel("" + Farm.getScore());
        scoreLabel.setForeground(GUIGame.resources.tertiaryColor);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(GUIGame.sizedFont(300f));
        scoreLabel.setBounds(GUIGame.scaled(0, 342, 800, 150));

        panel.add(scoreLabel);

        // Background
        JLabel background = new JLabel(GUIGame.resources.setupBackground);
        background.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(background);
        JLabel test = new JLabel(GUIGame.resources.farmBackground);
        test.setBounds(0, 0, GUIGame.getWidth(), GUIGame.getHeight());
        panel.add(test);
    }

    /**
     * 
     * @return the panel
     */
    public static JPanel getPanel() {
        return panel;
    }
}
