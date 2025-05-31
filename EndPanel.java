import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

class EndPanel extends JPanel {
    DefaultListModel<String> rankingModel = new DefaultListModel<>();
    JList<String> rankingList = new JList<>(rankingModel);
    JButton exitBtn = new JButton("나가기");

    EndPanel(ActionListener exitListener) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("게임 결과", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(titleLabel, BorderLayout.NORTH);

        rankingList.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(rankingList);
        add(scrollPane, BorderLayout.CENTER);

        exitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        exitBtn.addActionListener(exitListener);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(exitBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * 랭킹 및 점수 데이터를 리스트에 세팅하는 메서드
     * @param rankingData "1등 닉네임 - 점수 (+얻는 점수)" 형식 문자열 리스트
     */
    void setRankingData(List<String> rankingData) {
        rankingModel.clear();
        for (String entry : rankingData) {
            rankingModel.addElement(entry);
        }
    }
}
