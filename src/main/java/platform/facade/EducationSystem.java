package platform.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.builder.CourseDirector;
import platform.builder.OnlineCourseBuilder;
import platform.course.Course;
import platform.course.recommendation.PopularCourseStrategy;
import platform.course.recommendation.RecommendationContext;
import platform.course.recommendation.RecommendationCourse;
import platform.course.recommendation.SkillBasedStrategy;
import platform.course.recommendation.interfaces.RecommendationStrategy;
import platform.decorator.CertificateCourseDecorator;
import platform.decorator.ExtraMaterialDecorator;
import platform.facade.types.EducationSystemCourse;

public class EducationSystem {
	private static RecommendationStrategy popularCourseRecommendation = new PopularCourseStrategy();
	private static RecommendationStrategy skillBasedCourseRecommendation = new SkillBasedStrategy();

	private CourseDirector onlineCourseDirector = new CourseDirector(new OnlineCourseBuilder());

	private Map<String, EducationSystemCourse> courses = new HashMap<>();

	public EducationSystem() {}

	public void registerUser() {}

	public List<Course> getRecommendedCourses() {
		List<RecommendationCourse> availableCourses = new ArrayList<>();

		for (EducationSystemCourse courseData : this.courses.values()) {
			RecommendationCourse recommendationCourse = new RecommendationCourse(courseData.course, courseData.users, 0);
			availableCourses.add(recommendationCourse);
		}

		return popularCourseRecommendation.recommend(new RecommendationContext(availableCourses));
	}

	public Course getCourse(String id) {
		return this.courses.get(id).course;
	}

	public Course getCourseWithCertificate(String id) {
		Course baseCourse = this.getCourse(id);
		if (baseCourse == null) {
			return baseCourse;
		}

		float certificateFee = this.courses.get(id).certificateFee;
		if (certificateFee == 0.0f) {
			throw new RuntimeException(String.format("Course %s has no certificate! Add certificate with setCourseCertificateFee(id, fee)", id));
		}

		return new CertificateCourseDecorator(baseCourse, certificateFee);
	}

	public Course getCourseWithExtraMaterial(String id) {
		Course baseCourse = this.getCourse(id);
		if (baseCourse == null) {
			return baseCourse;
		}

		float extraMaterialFee = this.courses.get(id).extraMaterialFee;
		if (extraMaterialFee == 0.0f) {
			throw new RuntimeException(String.format("Course %s has no extra material! Add extra material with setCourseExtraMaterialFee(id, fee)", id));
		}

		return new ExtraMaterialDecorator(baseCourse, extraMaterialFee);
	}

	public Course getPremiumCourse(String id) {
		Course course = this.getCourse(id);
		if (course == null) {
			return course;
		}

		EducationSystemCourse courseData = this.courses.get(id);

		if (courseData.certificateFee == 0.0f && courseData.extraMaterialFee == 0.0f) {
			throw new RuntimeException(String.format("Course %s has no certificate and extra material! Add certificate with setCourseCertificateFee(id, fee) or extra material with setCourseExtraMaterialFee(id, fee)", id));
		}

		if (courseData.certificateFee != 0.0f) {
			course = new CertificateCourseDecorator(course, courseData.certificateFee);
		}

		if (courseData.extraMaterialFee != 0.0f) {
			course = new ExtraMaterialDecorator(course, courseData.extraMaterialFee);
		}

		return course;
	}

	public boolean hasCourseCertificate(String id) {
		return this.courses.get(id).certificateFee != 0.0f;
	}

	public Course addCourse(String id, String title, String description, float price) {
		Course course = this.onlineCourseDirector.buildWithModulesAndQuizTemplate(id, title, description, price);

		this.courses.put(course.getId(), new EducationSystemCourse(course));

		return course;
	}

	public void setCourseCertificateFee(String id, float certificateFee) {
		EducationSystemCourse courseData = this.courses.get(id);
		if (courseData == null) {
			throw new RuntimeException(String.format("Course %s does not exist!", id));
		}

		courseData.certificateFee = certificateFee;
	}

	public void setCourseExtraMaterialFee(String id, float extraMaterialFee) {
		EducationSystemCourse courseData = this.courses.get(id);
		if (courseData == null) {
			throw new RuntimeException(String.format("Course %s does not exist!", id));
		}

		courseData.extraMaterialFee = extraMaterialFee;
	}
}
