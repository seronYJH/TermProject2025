public class QuizManager {
    private List<Quiz> quizzes;  // 문제 리스트 (Quiz는 문제 클래스)
    private String[] answer; //문제 리스트에 대한 답 리스트.
    private int currentIndex;

    public QuizManager(List<Quiz> quizzes) {
        this.quizzes = quizzes;
        this.currentIndex = 0;
    }

    public Quiz getCurrentQuiz() {
        if (currentIndex < quizzes.size()) {
            return quizzes.get(currentIndex);
        }
        return null;  // 문제 다 끝남
    }

    public boolean checkAnswer(String answer) {
        Quiz quiz = getCurrentQuiz();
        if (quiz == null) return false;

        boolean correct = quiz.isCorrectAnswer(answer);
        if (correct) {
            currentIndex++;
        }
        return correct;
    }

    public boolean hasMoreQuiz() {
        return currentIndex < quizzes.size();
    }
}