package platform.course.recommendation;

import java.util.List;

public class RecommendationContext {
	public final List<RecommendationCourse> availableCourses;

	public RecommendationContext(List<RecommendationCourse> availableCourses) {
		this.availableCourses = availableCourses;
	}
}
