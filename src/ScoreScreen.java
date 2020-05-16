import javax.swing.*;

public class ScoreScreen {

    private static JPanel panel;

    public static void createScoreScreen(){
        panel = new JPanel();
        panel.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.setLayout(null);

        JLabel endLabel = new JLabel("The End!");
        endLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endLabel.setFont(GraphicalGame.sizedFont(140f));
        endLabel.setBounds(GraphicalGame.scaled(0, 94, 800, 75));

        panel.add(endLabel);

        JLabel yourScoreLabel = new JLabel("Your score");
        yourScoreLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        yourScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yourScoreLabel.setFont(GraphicalGame.sizedFont(60f));
        yourScoreLabel.setBounds(GraphicalGame.scaled(0, 248, 800, 50));

        panel.add(yourScoreLabel);

        JLabel scoreLabel = new JLabel("" + Farm.getScore());
        scoreLabel.setForeground(GraphicalGame.resources.tertiaryColor);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(GraphicalGame.sizedFont(300f));
        scoreLabel.setBounds(GraphicalGame.scaled(0, 342, 800, 150));

        panel.add(scoreLabel);

        // Background
        JLabel background = new JLabel(GraphicalGame.resources.setupBackground);
        background.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(background);
        JLabel test = new JLabel(GraphicalGame.resources.farmBackground);
        test.setBounds(0, 0, GraphicalGame.getWidth(), GraphicalGame.getHeight());
        panel.add(test);
    }

    public static JPanel getPanel() {
        return panel;
    }
}
