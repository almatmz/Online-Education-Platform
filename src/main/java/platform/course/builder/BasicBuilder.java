package platform.course.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import platform.course.BasicCourse;
import platform.course.Course;
import platform.course.quiz.Quiz;
import platform.course.module.Module;

public class BasicBuilder<T extends BasicBuilder<T>> implements Builder<T> {
	private UUID id = UUID.randomUUID();
	private String title;
	private int difficulty;
	private float price;
	private List<Module> modules = new ArrayList<>();
	private List<Quiz> quizzes = new ArrayList<>();
	private List<String> features = new ArrayList<>();

	public BasicBuilder() {}

	@SuppressWarnings("unchecked")
	public T setId(UUID id) {
		this.id = id;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setTitle(String title) {
		this.title = title;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setPrice(float price) {
		this.price = price;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setModules(List<Module> modules) {
		this.modules = modules;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setFeatures(List<String> features) {
		this.features = features;
		return (T) this;
	}

	public void verify() {
		if (this.title == null) {
			throw new RuntimeException("Title is required!");
		}
	}

	public Course build() {
		this.verify();

		return new BasicCourse(this.id, this.title, this.modules, this.quizzes, this.price, this.difficulty, this.features);
	}
}