package platform.course.decorator;

import platform.course.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtraMaterialCourseDecorator extends CourseDecorator {
    private final float extraMaterialFee;
    private final List<String> features;

    public ExtraMaterialCourseDecorator(Course inner, float extraMaterialFee) {
        super(inner);

        this.extraMaterialFee = extraMaterialFee;
        this.features = new ArrayList<>(inner.getFeatures());
        this.features.add("extra material");
    }

    @Override
    public float getPrice() {
        return this.inner.getPrice() + extraMaterialFee;
    }

    @Override
    public List<String> getFeatures() {
        return Collections.unmodifiableList(this.features);
    }
}