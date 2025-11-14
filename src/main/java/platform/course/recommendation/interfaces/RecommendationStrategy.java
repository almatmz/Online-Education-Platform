package platform.course.recommendation.interfaces;

import java.util.List;

import platform.course.Course;
import platform.course.recommendation.RecommendationContext;

public interface RecommendationStrategy {
	List<Course> recommend(RecommendationContext context);
}
