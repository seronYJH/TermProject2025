package client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChoicePanel extends JPanel {
    public ChoicePanel(ActionListener singleModeAction, ActionListener multiModeAction) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("모드를 선택하세요", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        JButton singleBtn = new JButton("싱글 모드");
        JButton multiBtn = new JButton("멀티 모드");

        singleBtn.addActionListener(singleModeAction);
        multiBtn.addActionListener(multiModeAction);

        buttonPanel.add(singleBtn);
        buttonPanel.add(multiBtn);

        add(buttonPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
    }
}
