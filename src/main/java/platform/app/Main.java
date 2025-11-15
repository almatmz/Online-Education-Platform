package platform.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import platform.course.Course;
import platform.course.builder.BasicBuilder;
import platform.course.builder.Builder;
import platform.course.builder.CertificatedBuilder;
import platform.course.builder.PremiumBuilder;
import platform.course.module.Module;
import platform.course.quiz.Quiz;
import platform.facade.EducationSystem;
import platform.factory.StudentFactory;
import platform.users.Student;

public class Main {
	@SuppressWarnings("rawtypes")
	private final static BasicBuilder courseBuilder = new BasicBuilder();
	private final static CertificatedBuilder certificatedCourseBuilder = new CertificatedBuilder();
	private final static PremiumBuilder premiumCourseBuilder = new PremiumBuilder();

	private final static StudentFactory studentFactory = new StudentFactory();

	private final static EducationSystem educationSystem = new EducationSystem();

	private final static Scanner scanner = new Scanner(System.in);
	private final static Random rand = new Random();

	private static void printMenu() {
		System.out.println("=== Online Education Platform ===");
		System.out.println(String.format("Courses: %s", new ArrayList<>(educationSystem.getCourses())));
		System.out.println(String.format("Students: %s", new ArrayList<>(educationSystem.getStudents())));
		System.out.println("---------------------------------");
		System.out.println("Select an option:");
		System.out.println("1. Create Course");
		System.out.println("2. Register Student");
		System.out.println("3. Enroll/Unenroll Student to/from Course");
		System.out.println("4. Subscribe/Unsubscribe Student to/from Announcements");
		System.out.println("5. Announce Message");
		System.out.println("6. Recommend Popular Courses");
		System.out.println("7. Recommend Courses for Student");
		System.out.println("0. Exit");
	}

	private static void createCourse() {
		@SuppressWarnings("rawtypes")
		Builder builder = courseBuilder;

		System.out.print("Title: ");
		String title = scanner.nextLine();

		System.out.print("Price: ");
		float price = scanner.nextFloat();
		scanner.nextLine();

		System.out.print("Difficulty: ");
		int difficulty = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Modules (separated by comma): ");
		List<Module> modules = Arrays.asList(scanner.nextLine().trim().split(", ")).stream().map((module) -> new Module(title, title)).toList();

		System.out.print("Quizzes (separated by comma): ");
		List<Quiz> quizzes = Arrays.asList(scanner.nextLine().trim().split(", ")).stream().map((module) -> new Quiz(title, rand.nextInt(1, 10))).toList();

		System.out.print("Features (separated by comma): ");
		List<String> features = Arrays.asList(scanner.nextLine().trim().split(", "));

		System.out.print("Certificate fee (0 to skip): ");
		float certificateFee = scanner.nextFloat();
		scanner.nextLine();

		if (certificateFee > 0.0f) {
			System.out.print("Extra material fee (0 to skip): ");
			float extraMaterialFee = scanner.nextFloat();
			scanner.nextLine();

			if (extraMaterialFee > 0.0f) {
				builder = premiumCourseBuilder.setCertificateFee(certificateFee).setExtraMaterialFee(extraMaterialFee);
			} else {
				builder = certificatedCourseBuilder.setCertificateFee(certificateFee);
			}
		}

		@SuppressWarnings("unchecked")
		Course course = builder.setTitle(title)
								.setPrice(price)
								.setDifficulty(difficulty)
								.setModules(modules)
								.setQuizzes(quizzes)
								.setFeatures(features)
								.build();

		educationSystem.addCourse(course);
	}

	private static void registerStudent() {
		System.out.print("Skill: ");
		int skill = scanner.nextInt();
		scanner.nextLine();

		Student student = studentFactory.createUser();
		student.setSkill(skill);
		educationSystem.registerStudent(student);
	}

	private static void enrollUnenrollStudent() {
		Set<Student> students = educationSystem.getStudents();

		System.out.print("Student ID: ");
		String studentId = scanner.nextLine();

		Student student = null;

		for (Student st : students) {
			if (st.getId().toString().equals(studentId)) {
				student = st;
				break;
			}
		}

		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		Set<Course> courses = educationSystem.getCourses();

		System.out.print("Course ID: ");
		String courseId = scanner.nextLine();

		Course course = null;

		for (Course c : courses) {
			if (c.getId().toString().equals(courseId)) {
				course = c;
				break;
			}
		}

		if (course == null) {
			System.out.println("Course not found.");
			return;
		}

		if (!educationSystem.isEnrolled(student, course)) {
			educationSystem.enrollStudent(educationSystem.getStudents().iterator().next(), course);
			System.out.println("Enrolled");
		} else {
			educationSystem.unenrollStudent(educationSystem.getStudents().iterator().next(), course);
			System.out.println("Unenrolled");
		}
	}

	private static void subscribeUnsubscribeStudent() {
		Set<Student> students = educationSystem.getStudents();

		System.out.print("Student ID: ");
		String studentId = scanner.nextLine();

		Student student = null;

		for (Student st : students) {
			if (st.getId().toString().equals(studentId)) {
				student = st;
				break;
			}
		}

		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		if (!educationSystem.isSubscribed(student)) {
			educationSystem.subscribeAnnouncements(student);
			System.out.println("Subscribed");
		} else {
			educationSystem.unsubscribeAnnouncements(student);
			System.out.println("Unsubscribed");
		}
	}

	private static void announceMessage() {
		System.out.print("Message: ");
		String message = scanner.nextLine();

		educationSystem.announce(message);
	}

	private static void recommendPopularCourses() {
		System.out.println(educationSystem.recommendCourses());
	}

	private static void recommendCoursesForStudent() {
		Set<Student> students = educationSystem.getStudents();

		System.out.print("Student ID: ");
		String studentId = scanner.nextLine();

		Student student = null;

		for (Student st : students) {
			if (st.getId().toString().equals(studentId)) {
				student = st;
				break;
			}
		}

		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		System.out.println(educationSystem.recommendCoursesForStudent(student));
	}

    public static void main(String[] args) {
		boolean running = true;
		while (running) {
			printMenu();

			System.out.print(">>> ");
			int option = scanner.nextInt();
			scanner.nextLine();

			try {
				switch (option) {
					case 1:
						createCourse();
						break;

					case 2:
						registerStudent();
						break;

					case 3:
						enrollUnenrollStudent();
						break;

					case 4:
						subscribeUnsubscribeStudent();
						break;

					case 5:
						announceMessage();
						break;

					case 6:
						recommendPopularCourses();
						break;

					case 7:
						recommendCoursesForStudent();
						break;

					case 0:
						running = false;
						break;

					default:
						System.out.println("Unknown option");
						break;
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

		scanner.close();
    }
}