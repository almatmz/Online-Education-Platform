package platform.course.recommendation;

import java.util.List;

import platform.course.Course;
import platform.course.recommendation.interfaces.RecommendationStrategy;

public class SkillBasedStrategy implements RecommendationStrategy {
	@Override
	public List<Course> recommend(RecommendationContext context) {
		int studentSkill = context.student.getSkill();

		return context.availableCourses.stream()
										.sorted((a, b) -> Math.abs(a.getDifficulty() - studentSkill) - Math.abs(b.getDifficulty() - studentSkill))
										.toList();
	}
}
