package platform.course.recommendation;

import java.util.List;

import platform.course.Course;
import platform.course.recommendation.interfaces.RecommendationStrategy;

public class SkillBasedStrategy implements RecommendationStrategy {
	@Override
	public List<Course> recommend(RecommendationContext context) {
		return context.availableCourses.stream().sorted((a, b) -> a.difficulty - b.difficulty).map((course) -> course.course).toList();
	}
}
