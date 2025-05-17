public class QuizManager {
    private QuizQ currentQuiz;         // Quiz 클레스에 존재하는 문제중 한 문제(QuizQ 배열에서)를 받을거임
    private String correctAnswer;     // 현재 문제의 정답
    private int right, wrong;                // 유저의 정답률을 구하기 위한 변수

    public QuizManager() {
        this.right = 0;
        this.wrong = 0;
        loadNextQuiz();
    }

    // 다음 문제를 불러오고 현재 문제/정답을 갱신
    public void loadNextQuiz() {
        this.currentQuiz = Quiz.getRandomQuiz(); // QuizQ에서 한 문제 가져올거임.
        this.correctAnswer = currentQuiz.getAnswer(); // Quiz 객체에서 문제에 대응하는 정답 가져오기
    }

    // 유저가 입력한 정답을 채점
     //return 값은 추후 GUI에서 결과 출력을 위한 값. (정답/오답)
    public boolean checkAnswer(String userAnswer) {
        boolean isCorrect = correctAnswer.trim().equalsIgnoreCase(userAnswer.trim());
        if (isCorrect) {
            right += 1;  // 맞았으면
        } else {
            wrong += 1;   // 틀렸으면
        }
        return isCorrect;
    }

    // 마지막에 점수를 보여주기 위한 코드
    public String getScore() {
        return right+"/"+right+wrong; // 전체 문제중 맞은 문제 수.
    }

    // 현재 문제 반환 (GUI에 표시용)
    public QuizQ getCurrentQuiz() {
        return currentQuiz;
    }
}