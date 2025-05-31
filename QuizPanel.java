import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class QuizPanel extends JPanel {
    JLabel questionLabel = new JLabel("문제가 여기 표시됩니다.");
    JButton[] optionButtons = new JButton[4];
    JButton optRemoveBtn = new JButton("선지 제거");
    JButton timerIncreaseBtn = new JButton("타임 증가");
    JButton skipBtn = new JButton("문제 스킵");
    JLabel timerLabel = new JLabel("타이머: 30");

    QuizPanel(ActionListener listener) {
        setLayout(new BorderLayout(10,10));

        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for(int i=0; i<4; i++) {
            optionButtons[i] = new JButton("선지 " + (i+1));
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionButtons[i].addActionListener(listener);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        optRemoveBtn.addActionListener(listener);
        timerIncreaseBtn.addActionListener(listener);
        skipBtn.addActionListener(listener);
        itemPanel.add(optRemoveBtn);
        itemPanel.add(timerIncreaseBtn);
        itemPanel.add(skipBtn);
        add(itemPanel, BorderLayout.SOUTH);

        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(timerLabel, BorderLayout.EAST);
    }
}

class OptRemPanel extends JPanel {
    JLabel infoLabel = new JLabel("선지가 제거되었습니다.");
    JButton closeBtn = new JButton("확인");

    OptRemPanel(ActionListener listener) {
        setLayout(new BorderLayout());
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(infoLabel, BorderLayout.CENTER);
        closeBtn.addActionListener(listener);
        add(closeBtn, BorderLayout.SOUTH);
    }
}

class SkipPanel extends JPanel {
    JLabel infoLabel = new JLabel("누군가 문제스킵권을 사용하였습니다.");
    JButton closeBtn = new JButton("확인");

    SkipPanel(ActionListener listener) {
        setLayout(new BorderLayout());
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(infoLabel, BorderLayout.CENTER);
        closeBtn.addActionListener(listener);
        add(closeBtn, BorderLayout.SOUTH);
    }
}

class RightPanel extends JPanel {
    RightPanel() {
        setBackground(Color.GREEN);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("정답입니다!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label, BorderLayout.CENTER);
    }
}

class WrongPanel extends JPanel {
    WrongPanel() {
        setBackground(Color.RED);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("틀렸습니다!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label, BorderLayout.CENTER);
    }
}

class AnswerPanel extends JPanel {
    JLabel answerLabel = new JLabel("정답: ");
    JLabel timerLabel = new JLabel("다음 문제까지: 5초");

    AnswerPanel() {
        setLayout(new BorderLayout(10,10));
        answerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(answerLabel, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.SOUTH);
    }

    void setAnswer(String answer) {
        answerLabel.setText("정답: " + answer);
    }

    void setTimer(int seconds) {
        timerLabel.setText("다음 문제까지: " + seconds + "초");
    }
}
