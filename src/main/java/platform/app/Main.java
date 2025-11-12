package platform.app;

import platform.builder.CourseDirector;
import platform.builder.OnlineCourseBuilder;
import platform.course.Course;
import platform.decorator.CertificateCourseDecorator;
import platform.decorator.ExtraMaterialDecorator;

public class Main {
    public static void main(String[] args) {
        var director = new CourseDirector(new OnlineCourseBuilder());

        Course base = director.buildWithModulesAndQuizTemplate(
                "SE-101",
                "Intro to Software Design",
                "Learn core design patterns with practical examples.",
                49.99
        );

        Course withCertificate = new CertificateCourseDecorator(base, 20.00);
        Course premium = new ExtraMaterialDecorator(withCertificate, 10.00);

        System.out.println("Base:        " + base.summary());
        System.out.println("Certificate: " + withCertificate.summary()
                + " | price=" + withCertificate.getPrice()
                + " | features=" + withCertificate.getFeatures());
        System.out.println("Premium:     " + premium.summary()
                + " | price=" + premium.getPrice()
                + " | features=" + premium.getFeatures());
    }
}