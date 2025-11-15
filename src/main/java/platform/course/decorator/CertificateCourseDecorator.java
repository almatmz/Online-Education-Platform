package platform.course.decorator;

import platform.course.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CertificateCourseDecorator extends CourseDecorator {
    private final float certificateFee;
    private final List<String> features;

    public CertificateCourseDecorator(Course inner, float certificateFee) {
        super(inner);

        this.certificateFee = certificateFee;
        this.features = new ArrayList<>(inner.getFeatures());
        this.features.add("certificate");
    }

    @Override
    public float getPrice() {
        return this.inner.getPrice() + certificateFee;
    }

    @Override
    public List<String> getFeatures() {
        return Collections.unmodifiableList(this.features);
    }

	@Override
	public String toString() {
		if (this.inner instanceof ExtraMaterialCourseDecorator) {
			return String.format("[Course (premium) | %s | %s id] %s price, %s difficulty, %s students, %s modules, %s quizzes, features: %s", this.getTitle(),this.getId(), this.getPrice(), this.getDifficulty(), this.getEnrolledStudents().size(), this.getModules().size(), this.getQuizzes().size(), this.getFeatures());
		}

		return String.format("[Course (w/ certificate) | %s | %s id] %s price, %s difficulty, %s students, %s modules, %s quizzes, features: %s", this.getTitle(),this.getId(),this.getPrice(), this.getDifficulty(), this.getEnrolledStudents().size(), this.getModules().size(), this.getQuizzes().size(), this.getFeatures());
	}
}