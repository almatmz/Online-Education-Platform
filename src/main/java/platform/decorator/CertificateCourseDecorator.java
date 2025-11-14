package platform.decorator;

import platform.course.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CertificateCourseDecorator extends CourseDecorator {
    private final float certificateFee;
    private final List<String> features;

    public CertificateCourseDecorator(Course inner, float certificateFee) {
        super(inner);
        this.certificateFee = certificateFee;
        this.features = new ArrayList<>(inner.getFeatures());
        this.features.add("certificate");
    }

    @Override
    public float getPrice() {
        return inner.getPrice() + certificateFee;
    }

    @Override
    public List<String> getFeatures() {
        return Collections.unmodifiableList(features);
    }
}