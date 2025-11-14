package platform.builder;

import platform.course.Course;
import platform.course.Module;
import platform.course.Quiz;

public class CourseDirector {
    private final CourseBuilder builder;

    public CourseDirector(CourseBuilder builder) {
        this.builder = builder;
    }

    public Course buildMinimal(String id, String title, String description, float basePrice) {
        return builder.reset()
                .setId(id)
                .setTitle(title)
                .setDescription(description)
                .setBasePrice(basePrice)
                .build();
    }

    public Course buildWithModulesAndQuizTemplate(String id, String title, String description, float basePrice) {
        return builder.reset()
                .setId(id)
                .setTitle(title)
                .setDescription(description)
                .setBasePrice(basePrice)
                .addModule(new Module("Introduction", "Welcome and overview"))
                .addModule(new Module("Core Concepts", "Key topics explained"))
                .addQuiz(new Quiz("Module 1 Quiz", 10))
                .build();
    }
}