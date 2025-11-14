package platform.course;

import java.util.List;

public interface Course {
    String getId();
    String getTitle();
    String getDescription();
    List<Module> getModules();
    List<Quiz> getQuizzes();
    float getPrice();
    List<String> getFeatures();
    String summary();
}