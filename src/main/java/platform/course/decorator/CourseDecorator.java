package platform.decorator;

import platform.course.Course;
import platform.course.module.Module;
import platform.course.quiz.Quiz;
import platform.users.Student;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class CourseDecorator implements Course {
    protected final Course inner;

    protected CourseDecorator(Course inner) {
        this.inner = inner;
    }

	@Override
    public UUID getId() {
		return this.inner.getId();
	}

	@Override
	public String getTitle() {
		return this.inner.getTitle();
	}

	@Override
	public List<Module> getModules() {
		return this.inner.getModules();
	}

	@Override
	public List<Quiz> getQuizzes() {
		return this.inner.getQuizzes();
	}

	@Override
	public float getPrice() {
		return this.inner.getPrice();
	}

	@Override
	public int getDifficulty() {
		return this.inner.getDifficulty();
	}

	@Override
	public List<String> getFeatures() {
		return this.inner.getFeatures();
	}

	@Override
	public Set<Student> getEnrolledStudents() {
		return this.inner.getEnrolledStudents();
	}

	@Override
	public void addEnrolledStudent(Student student) {
		this.inner.addEnrolledStudent(student);
	}

	@Override
	public void removeEnrolledStudent(Student student) {
		this.inner.removeEnrolledStudent(student);
	}

	@Override
	public String toString() {
		return this.inner.toString();
	}

	@Override
	public int hashCode() {
		return this.inner.hashCode();
	}
}