package client.ui;

import client.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuizPanel extends JPanel {

    private JLabel questionLabel;
    private JButton[] optionButtons = new JButton[4];
    private JLabel timerLabel;

    private Player player;

    public QuizPanel(ActionListener[] optionListeners) {
        setLayout(new BorderLayout());

        questionLabel = new JLabel("문제가 여기에 표시됩니다", SwingConstants.CENTER);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton("선지 " + (i + 1));
            optionButtons[i].addActionListener(optionListeners[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        timerLabel = new JLabel("남은 시간: 10", SwingConstants.LEFT);
        timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bottomPanel.add(timerLabel, BorderLayout.WEST);

        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        itemPanel.add(new JButton("선지제거"));
        itemPanel.add(new JButton("타임+3"));
        bottomPanel.add(itemPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setQuestion(String question) {
        questionLabel.setText(question);
    }

    public void setOptions(String[] options) {
        for (int i = 0; i < 4 && i < options.length; i++) {
            optionButtons[i].setText(options[i]);
        }
    }

    public void setTimer(int seconds) {
        timerLabel.setText("남은 시간: " + seconds);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
