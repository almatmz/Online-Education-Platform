package platform.decorator;

import platform.course.Course;
import platform.course.Module;
import platform.course.Quiz;

import java.util.List;

public abstract class CourseDecorator implements Course {
    protected final Course inner;

    protected CourseDecorator(Course inner) {
        this.inner = inner;
    }

    @Override public String getId() { return inner.getId(); }
    @Override public String getTitle() { return inner.getTitle(); }
    @Override public String getDescription() { return inner.getDescription(); }
    @Override public List<Module> getModules() { return inner.getModules(); }
    @Override public List<Quiz> getQuizzes() { return inner.getQuizzes(); }
    @Override public List<String> getFeatures() { return inner.getFeatures(); }
    @Override public String summary() { return inner.summary(); }
}