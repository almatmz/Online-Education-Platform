package platform.app;

import platform.course.Course;
import platform.facade.EducationSystem;

public class Main {
	public static EducationSystem educationSystem;

    public static void main(String[] args) {
		educationSystem = new EducationSystem();

		Course courseBase = educationSystem.addCourse(
			"SE-101",
            "Intro to Software Design",
            "Learn core design patterns with practical examples.",
            49.99f
		);

		educationSystem.setCourseCertificateFee(courseBase.getId(), 20.0f);
		educationSystem.setCourseExtraMaterialFee(courseBase.getId(), 10.0f);

		Course courseWithCertificate = educationSystem.getCourseWithCertificate(courseBase.getId());
		Course coursePremium = educationSystem.getPremiumCourse(courseBase.getId());

        System.out.println("Base:        " + courseBase.summary());
        System.out.println("Certificate: " + courseWithCertificate.summary()
                + " | price=" + courseWithCertificate.getPrice()
                + " | features=" + courseWithCertificate.getFeatures());
        System.out.println("Premium:     " + coursePremium.summary()
                + " | price=" + coursePremium.getPrice()
                + " | features=" + coursePremium.getFeatures());

		System.out.println("Recommended courses: " + educationSystem.getRecommendedCourses());
    }
}