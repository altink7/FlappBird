package ranking;

import service.RankingService;

import javax.swing.*;

public class UsernameInputDialog extends JFrame {
    private JTextField usernameField;
    public static boolean INPUT_FINISHED = false;
    private final int score;

    public UsernameInputDialog(int score) {
        this.score = score;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Enter Your Username");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel promptLabel = new JLabel("Enter your username:");
        usernameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            saveScore(username, score);
            dispose();
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                INPUT_FINISHED = true;
            }
        });

        add(promptLabel);
        add(usernameField);
        add(submitButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveScore(String username, int score) {
        RankingService.saveRanking(username, score);
        INPUT_FINISHED = true;
    }
}
