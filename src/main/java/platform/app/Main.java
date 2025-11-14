package platform.app;

import java.util.List;

import platform.course.Course;
import platform.course.builder.BasicBuilder;
import platform.course.builder.CertificatedBuilder;
import platform.course.builder.PremiumBuilder;
import platform.facade.EducationSystem;
import platform.factory.StudentFactory;
import platform.users.Student;

public class Main {
	@SuppressWarnings("rawtypes")
	public static BasicBuilder courseBuilder = new BasicBuilder();
	public static CertificatedBuilder certificatedCourseBuilder = new CertificatedBuilder();
	public static PremiumBuilder premiumCourseBuilder = new PremiumBuilder();

	public static StudentFactory studentFactory = new StudentFactory();

	public static EducationSystem educationSystem;

    public static void main(String[] args) {
		educationSystem = new EducationSystem();

		Student studentA = studentFactory.createUser();
		studentA.setSkill(2);
		educationSystem.registerStudent(studentA);
		educationSystem.subscribeAnnouncements(studentA);

		Student studentB = studentFactory.createUser();
		studentB.setSkill(4);
		educationSystem.registerStudent(studentB);
		educationSystem.subscribeAnnouncements(studentB);

		Student studentC = studentFactory.createUser();
		studentC.setSkill(5);
		educationSystem.registerStudent(studentC);

		Student studentD = studentFactory.createUser();
		studentD.setSkill(6);
		educationSystem.registerStudent(studentD);

		Course softwareDesignCourse = courseBuilder.setTitle("Intro to Software Design")
												.setPrice(49.99f)
												.setDifficulty(2)
												.build();
		educationSystem.addCourse(softwareDesignCourse);

		Course premiumSoftwareDesignCourse = premiumCourseBuilder.setTitle("Advanced Software Design")
												.setPrice(49.99f)
												.setCertificateFee(20.0f)
												.setExtraMaterialFee(10.0f)
												.setDifficulty(5)
												.build();
		educationSystem.addCourse(premiumSoftwareDesignCourse);

		educationSystem.enrollStudent(studentA, softwareDesignCourse);
		educationSystem.enrollStudent(studentC, premiumSoftwareDesignCourse);
		educationSystem.enrollStudent(studentD, premiumSoftwareDesignCourse);

		List<Course> recommendedCourses = educationSystem.recommendCourses();
		List<Course> recommendedCoursesForStudentB = educationSystem.recommendCoursesForStudent(studentB);

		System.out.println("Students:");
		System.out.println(studentA);
		System.out.println(studentB);
		System.out.println(studentC);
		System.out.println(studentD);

		System.out.println("\nCourses:");
		System.out.println(softwareDesignCourse);
		System.out.println(premiumSoftwareDesignCourse);

		System.out.println(String.format("\nRecommended courses (by popularity): %s", recommendedCourses));
		System.out.println(String.format("\nRecommended courses (by skill for Student B): %s", recommendedCoursesForStudentB));
    }
}