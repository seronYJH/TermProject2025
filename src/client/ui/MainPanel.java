package client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    public MainPanel(ActionListener onStartClicked) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("멀티 퀴즈 게임", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        JButton startButton = new JButton("게임 시작");
        startButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        startButton.addActionListener(onStartClicked);

        JPanel centerPanel = new JPanel();
        centerPanel.add(startButton);
        add(centerPanel, BorderLayout.CENTER);
    }
}
