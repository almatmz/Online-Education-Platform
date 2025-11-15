package platform.course;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import platform.course.module.Module;
import platform.course.quiz.Quiz;
import platform.users.Student;

public class BasicCourse implements Course {
    private final UUID id;
    private final String title;
    private final List<Module> modules;
    private final List<Quiz> quizzes;
    private final float price;
	private final int difficulty;
    private final List<String> features;

	private Set<Student> enrolledStudents = new HashSet<>();

    public BasicCourse(UUID id, String title, List<Module> modules, List<Quiz> quizzes, float price, int difficulty, List<String> features) {
		this.id = id;
		this.title = title;
		this.modules = modules;
		this.quizzes = quizzes;
		this.price = price;
		this.difficulty = difficulty;
		this.features = features;
	}

	@Override
    public UUID getId() {
		return this.id;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public List<Module> getModules() {
		return Collections.unmodifiableList(this.modules);
	}

	@Override
	public List<Quiz> getQuizzes() {
		return Collections.unmodifiableList(this.quizzes);
	}

	@Override
	public float getPrice() {
		return this.price;
	}

	@Override
	public int getDifficulty() {
		return this.difficulty;
	}

	@Override
	public List<String> getFeatures() {
		return Collections.unmodifiableList(this.features);
	}

	@Override
	public Set<Student> getEnrolledStudents() {
		return Collections.unmodifiableSet(this.enrolledStudents);
	}

	@Override
	public void addEnrolledStudent(Student student) {
		this.enrolledStudents.add(student);
	}

	@Override
	public void removeEnrolledStudent(Student student) {
		this.enrolledStudents.remove(student);
	}

	@Override
	public String toString() {
		return String.format("[Course | %s | %s] %s price, %s difficulty, %s students, %s modules, %s quizzes, features: %s", this.getTitle(), this.getId(), this.getPrice(), this.getDifficulty(), this.getEnrolledStudents().size(), this.getModules().size(), this.getQuizzes().size(), this.getFeatures());
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
