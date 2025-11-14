package platform.course.recommendation;

import java.util.List;

import platform.course.Course;
import platform.course.recommendation.interfaces.RecommendationStrategy;

public class PopularCourseStrategy implements RecommendationStrategy {
	@Override
	public List<Course> recommend(RecommendationContext context) {
		return context.availableCourses.stream()
										.filter((course) -> course.getEnrolledStudents().size() > 0)
										.sorted((a, b) -> b.getEnrolledStudents().size() - a.getEnrolledStudents().size())
										.toList();
	}
}
