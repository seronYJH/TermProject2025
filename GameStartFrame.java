import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// === 화면 외 메서드 === //
class Player {
    String id;
    String password;
    String nickname;
    int coin;

    Player(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.coin = 0;
    }

    boolean IDEqual(String inputId) {
        return this.id.equals(inputId);
    }

    boolean PassWordEqual(String inputPw) {
        return this.password.equals(inputPw);
    }

    boolean NameEqual(String inputName) {
        return this.nickname.equals(inputName);
    }
}

// === 게임 시작 화면 === //
class MainPanel extends JPanel {
    MainPanel(ActionListener listener) {
        setLayout(new BorderLayout());
        JButton startBtn = new JButton("게임 시작");
        startBtn.setFont(new Font("Arial", Font.BOLD, 24));
        startBtn.addActionListener(listener);
        add(startBtn, BorderLayout.CENTER);
    }
}

class LoginPanel extends JPanel {
    JTextField idField = new JTextField(10);
    JPasswordField pwField = new JPasswordField(10);
    JButton loginBtn = new JButton("로그인");

    LoginPanel(ActionListener loginListener) {
        setLayout(new GridLayout(3, 2, 10, 10));
        add(new JLabel("아이디: ")); add(idField);
        add(new JLabel("비밀번호: ")); add(pwField);
        add(new JLabel()); add(loginBtn);
        loginBtn.addActionListener(loginListener);
    }
}

class MakeNamePanel extends JPanel {
    JTextField nameField = new JTextField(10);
    JButton confirmBtn = new JButton("확인");

    MakeNamePanel(ActionListener nameListener) {
        setLayout(new FlowLayout());
        add(new JLabel("닉네임을 입력하세요: "));
        add(nameField);
        add(confirmBtn);
        confirmBtn.addActionListener(nameListener);
    }
}

class ChoicePanel extends JPanel {
    JButton singleBtn = new JButton("싱글 모드");
    JButton multiBtn = new JButton("멀티 모드");

    ChoicePanel(ActionListener listener) {
        setLayout(new GridLayout(2, 1, 10, 10));
        singleBtn.setFont(new Font("Arial", Font.BOLD, 20));
        multiBtn.setFont(new Font("Arial", Font.BOLD, 20));
        add(singleBtn);
        add(multiBtn);
        singleBtn.addActionListener(listener);
        multiBtn.addActionListener(listener);
    }
}

// 메인 프레임 (패널 전환 관리)
class GameStartFrame extends JFrame implements ActionListener {
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    MainPanel mainMenu = new MainPanel(this);
    LoginPanel loginPanel = new LoginPanel(this);
    MakeNamePanel makeNamePanel = new MakeNamePanel(this);
    ChoicePanel choicePanel = new ChoicePanel(this);

    public GameStartFrame() {
        setTitle("퀴즈 게임 - 시작");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.add(mainMenu, "MAIN");
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(makeNamePanel, "MAKE_NAME");
        mainPanel.add(choicePanel, "CHOICE");
        add(mainPanel);
        cardLayout.show(mainPanel, "MAIN");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == mainMenu.getComponent(0)) {
            cardLayout.show(mainPanel, "LOGIN");
        } else if (src == loginPanel.loginBtn) {
            // 로그인 검증 로직 (추후 연동)
            cardLayout.show(mainPanel, "MAKE_NAME");
        } else if (src == makeNamePanel.confirmBtn) {
            cardLayout.show(mainPanel, "CHOICE");
        } else if (src == choicePanel.singleBtn) {
            System.out.println("싱글 모드 선택됨");
        } else if (src == choicePanel.multiBtn) {
            System.out.println("멀티 모드 선택됨");
        }
    }

    public static void main(String[] args) {
        new GameStartFrame();
    }
}
