package platform.course.recommendation;

import platform.course.Course;

public class RecommendationCourse {
	public final Course course;
	public final int users;
	public final int difficulty;

	public RecommendationCourse(Course course, int users, int difficulty) {
		this.course = course;
		this.users = users;
		this.difficulty = difficulty;
	}
}
