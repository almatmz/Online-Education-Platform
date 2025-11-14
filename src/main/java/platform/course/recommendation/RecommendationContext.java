package platform.course.recommendation;

import java.util.List;

import platform.course.Course;
import platform.users.Student;

public class RecommendationContext {
	public final List<Course> availableCourses;
	public final Student student;

	public RecommendationContext(List<Course> availableCourses, Student student) {
		this.availableCourses = availableCourses;
		this.student = student;
	}

	public RecommendationContext(List<Course> availableCourses) {
		this.availableCourses = availableCourses;
		this.student = null;
	}
}
