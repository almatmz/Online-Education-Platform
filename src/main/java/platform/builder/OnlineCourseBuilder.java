package platform.builder;

import platform.course.BaseCourse;
import platform.course.Course;
import platform.course.Module;
import platform.course.Quiz;

import java.util.ArrayList;
import java.util.List;

public class OnlineCourseBuilder implements CourseBuilder {
    private String id;
    private String title;
    private String description;
    private float basePrice;
    private final List<Module> modules = new ArrayList<>();
    private final List<Quiz> quizzes = new ArrayList<>();

    @Override
    public CourseBuilder reset() {
        id = null;
        title = null;
        description = null;
        basePrice = 0.0f;
        modules.clear();
        quizzes.clear();
        return this;
    }

    @Override public CourseBuilder setId(String id) { this.id = id; return this; }
    @Override public CourseBuilder setTitle(String title) { this.title = title; return this; }
    @Override public CourseBuilder setDescription(String description) { this.description = description; return this; }
    @Override public CourseBuilder setBasePrice(float price) { this.basePrice = price; return this; }
    @Override public CourseBuilder addModule(Module module) { this.modules.add(module); return this; }
    @Override public CourseBuilder addQuiz(Quiz quiz) { this.quizzes.add(quiz); return this; }

    @Override
    public Course build() {
        return new BaseCourse(id, title, description, modules, quizzes, basePrice);
    }
}