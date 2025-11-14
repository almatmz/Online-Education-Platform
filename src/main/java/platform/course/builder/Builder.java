package platform.course.builder;

import java.util.List;
import java.util.UUID;

import platform.course.Course;
import platform.course.quiz.Quiz;
import platform.course.module.Module;

public interface Builder<T extends Builder<T>> {
	T setId(UUID id);
	T setTitle(String title);
	T setDifficulty(int difficulty);
	T setPrice(float price);
	T setModules(List<Module> modules);
	T setQuizzes(List<Quiz> quizzes);
	T setFeatures(List<String> features);
	void verify();
	Course build();
}
