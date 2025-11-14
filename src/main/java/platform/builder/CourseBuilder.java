package platform.builder;

import platform.course.Module;
import platform.course.Quiz;
import platform.course.Course;

public interface CourseBuilder {
    CourseBuilder reset();
    CourseBuilder setId(String id);
    CourseBuilder setTitle(String title);
    CourseBuilder setDescription(String description);
    CourseBuilder setBasePrice(float price);
    CourseBuilder addModule(Module module);
    CourseBuilder addQuiz(Quiz quiz);
    Course build();
}