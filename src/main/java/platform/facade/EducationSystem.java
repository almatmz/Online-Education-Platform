package platform.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import platform.course.Course;
import platform.course.recommendation.PopularCourseStrategy;
import platform.course.recommendation.RecommendationContext;
import platform.course.recommendation.SkillBasedStrategy;
import platform.course.recommendation.interfaces.RecommendationStrategy;
import platform.observer.AnnouncementPublisher;
import platform.observer.AnnouncementSubscriber;
import platform.users.Student;

public class EducationSystem implements AnnouncementPublisher {
	private final static RecommendationStrategy popularCourseRecommendation = new PopularCourseStrategy();
	private final static RecommendationStrategy skillBasedCourseRecommendation = new SkillBasedStrategy();

	private Set<Course> courses = new HashSet<>();
	private Set<Student> students = new HashSet<>();
	private Set<AnnouncementSubscriber> announcementSubscribers = new HashSet<>();

	public EducationSystem() {}

	public Set<Course> getCourses() {
		return Collections.unmodifiableSet(this.courses);
	}

	public Set<Student> getStudents() {
		return Collections.unmodifiableSet(this.students);
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}

	public void registerStudent(Student student) {
		this.students.add(student);
	}

	public void unregisterStudent(Student student) {
		this.students.remove(student);
	}

	public boolean isEnrolled(Student student, Course course) {
		return student.getEnrolledCourses().contains(course);
	}

	public void enrollStudent(Student student, Course course) {
		student.addEnrolledCourse(course);
		course.addEnrolledStudent(student);
	}

	public void unenrollStudent(Student student, Course course) {
		student.removeEnrolledCourse(course);
		course.removeEnrolledStudent(student);
	}

	public boolean isSubscribed(AnnouncementSubscriber subscriber) {
		return this.announcementSubscribers.contains(subscriber);
	}

	@Override
	public void subscribeAnnouncements(AnnouncementSubscriber subscriber) {
		this.announcementSubscribers.add(subscriber);
	}

	@Override
	public void unsubscribeAnnouncements(AnnouncementSubscriber subscriber) {
		this.announcementSubscribers.remove(subscriber);
	}

	@Override
	public void announce(String text) {
		for (AnnouncementSubscriber subscriber : this.announcementSubscribers) {
			subscriber.announcement(text);
		}
	}

	public List<Course> recommendCourses() {
		return popularCourseRecommendation.recommend(new RecommendationContext(new ArrayList<>(this.courses)));
	}

	public List<Course> recommendCoursesForStudent(Student student) {
		return skillBasedCourseRecommendation.recommend(new RecommendationContext(new ArrayList<>(this.courses), student));
	}
}
