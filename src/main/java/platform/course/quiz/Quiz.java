package platform.course.quiz;

public class Quiz {
    private final String title;
    private final int questionsCount;

    public Quiz(String title, int questionsCount) {
        this.title = title;
        this.questionsCount = questionsCount;
    }

    public String getTitle() {
		return this.title;
	}

    public int getQuestionsCount() {
		return this.questionsCount;
	}
}