package platform.course;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import platform.course.module.Module;
import platform.course.quiz.Quiz;
import platform.users.Student;

public interface Course {
    UUID getId();
    String getTitle();
    List<Module> getModules();
    List<Quiz> getQuizzes();
    float getPrice();
	int getDifficulty();
    List<String> getFeatures();
	Set<Student> getEnrolledStudents();
	void addEnrolledStudent(Student student);
	void removeEnrolledStudent(Student student);
}