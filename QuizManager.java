public class QuizManager {
    private List<Quiz> currentGameQuizList;
    private currentIndex = 0;

    public QuizManager(QuizRepository repo) {
        // 게임 시작할 때 문제지를 받아서
        this.currentGameQuizList = new ArrayList<>(repo.getQuizList());
    }

        if (currentIndex < quizzes.size()) {
    public Quiz getCurrentQuiz() {
            return currentGameQuizList.get(currentIndex);
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