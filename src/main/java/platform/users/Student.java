package platform.users;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import platform.course.Course;
import platform.observer.AnnouncementSubscriber;

public class Student implements User, AnnouncementSubscriber {
	private final UUID id = UUID.randomUUID();
	private int skill;

	private Set<Course> enrolledCourses = new HashSet<>();

	public Student() { }

	@Override
	public UUID getId() {
		return this.id;
	}

	public int getSkill() {
		return this.skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public Set<Course> getEnrolledCourses() {
		return Collections.unmodifiableSet(this.enrolledCourses);
	}

	public void addEnrolledCourse(Course course) {
		this.enrolledCourses.add(course);
	}

	public void removeEnrolledCourse(Course course) {
		this.enrolledCourses.remove(course);
	}

	@Override
	public void announcement(String announcement) {
		System.out.println(String.format("[Student | %s] %s", this.getId(), announcement));
	}

	@Override
	public String toString() {
		return String.format("[Student | %s] skill: %s", this.getId(), this.getSkill());
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
