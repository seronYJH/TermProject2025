package client.ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Map<String, JPanel> panelMap = new HashMap<>();

    public MainFrame() {
        setTitle("멀티 퀴즈 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        setContentPane(cardPanel);
    }

    public void addPanel(String name, JPanel panel) {
        panelMap.put(name, panel);
        cardPanel.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}
