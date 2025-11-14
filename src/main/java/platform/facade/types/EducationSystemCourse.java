package platform.facade.types;

import platform.course.Course;

public class EducationSystemCourse {
	public final Course course;
	public float certificateFee;
	public float extraMaterialFee;

	public int users;

	public EducationSystemCourse(Course course) {
		this.course = course;
	}
}
