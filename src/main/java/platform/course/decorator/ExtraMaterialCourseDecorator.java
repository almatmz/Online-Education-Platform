package platform.course.decorator;

import platform.course.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtraMaterialCourseDecorator extends CourseDecorator {
    private final float extraMaterialFee;
    private final List<String> features;

    public ExtraMaterialCourseDecorator(Course inner, float extraMaterialFee) {
        super(inner);

        this.extraMaterialFee = extraMaterialFee;
        this.features = new ArrayList<>(inner.getFeatures());
        this.features.add("extra material");
    }

    @Override
    public float getPrice() {
        return this.inner.getPrice() + extraMaterialFee;
    }

    @Override
    public List<String> getFeatures() {
        return Collections.unmodifiableList(this.features);
    }

	@Override
	public String toString() {
		if (this.inner instanceof CertificateCourseDecorator) {
			return String.format("[Course (premium) | %s] %s price, %s difficulty, %s students, %s modules, %s quizzes, features: %s", this.getTitle(), this.getPrice(), this.getDifficulty(), this.getEnrolledStudents().size(), this.getModules().size(), this.getQuizzes().size(), this.getFeatures());
		}

		return String.format("[Course (w/ extra material) | %s] %s price, %s difficulty, %s students, %s modules, %s quizzes, features: %s", this.getTitle(), this.getPrice(), this.getDifficulty(), this.getEnrolledStudents().size(), this.getModules().size(), this.getQuizzes().size(), this.getFeatures());
	}
}