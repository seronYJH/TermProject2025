package client;

/**
 * 클라이언트의 내부 게임 상태를 저장하고 관리하는 클래스.
 * 서버와 직접 통신하지 않으며, 닉네임, 점수, 현재 문제 번호, 연결 상태 등
 * 로컬에서 유지되어야 하는 정보를 추적하는 데 사용된다.
 */
public class ClientState {

    // 사용자 닉네임
    private String nickname;

    // 현재 점수
    private int score;

    // 현재 진행 중인 문제 번호 (0부터 시작)
    private int currentQuestionIndex;

    // 서버와의 연결 여부
    private boolean connected;

    /**
     * 생성자 - 닉네임을 입력받아 상태를 초기화한다.
     * @param nickname 사용자의 닉네임
     */
    public ClientState(String nickname) {
        this.nickname = nickname;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.connected = false;
    }

    /**
     * 점수를 증가시킨다.
     * @param points 증가시킬 점수
     */
    public void increaseScore(int points) {
        this.score += points;
    }

    /**
     * 현재 문제 번호를 1 증가시킨다.
     */
    public void nextQuestion() {
        this.currentQuestionIndex++;
    }

    /**
     * 서버 연결 상태를 설정한다.
     * @param connected 연결 여부 (true: 연결됨 / false: 끊김)
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    // ===== Getter 메서드 =====

    /**
     * 사용자 닉네임 반환
     * @return 닉네임 문자열
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 현재 점수 반환
     * @return 점수
     */
    public int getScore() {
        return score;
    }

    /**
     * 현재 문제 번호 반환
     * @return 문제 인덱스 (0부터 시작)
     */
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    /**
     * 서버 연결 여부 반환
     * @return true면 연결 상태
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * 디버깅용 상태 출력
     * @return 상태 정보를 문자열로 요약한 결과
     */
    @Override
    public String toString() {
        return "[닉네임: " + nickname +
               ", 점수: " + score +
               ", 현재문제: " + currentQuestionIndex +
               ", 연결됨: " + connected + "]";
    }
}
