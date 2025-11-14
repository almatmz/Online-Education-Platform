package platform.decorator;

import platform.course.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtraMaterialDecorator extends CourseDecorator {
    private final float extraMaterialFee;
    private final List<String> features;

    public ExtraMaterialDecorator(Course inner, float extraMaterialFee) {
        super(inner);
        this.extraMaterialFee = extraMaterialFee;
        this.features = new ArrayList<>(inner.getFeatures());
        this.features.add("extra-materials");
    }

    @Override
    public float getPrice() {
        return inner.getPrice() + extraMaterialFee;
    }

    @Override
    public List<String> getFeatures() {
        return Collections.unmodifiableList(features);
    }
}