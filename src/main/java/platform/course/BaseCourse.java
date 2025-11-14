package platform.course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseCourse implements Course {
    private final String id;
    private final String title;
    private final String description;
    private final List<Module> modules;
    private final List<Quiz> quizzes;
    private final float basePrice;
    private final List<String> features;

    public BaseCourse(
            String id,
            String title,
            String description,
            List<Module> modules,
            List<Quiz> quizzes,
            float basePrice
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.modules = new ArrayList<>(modules);
        this.quizzes = new ArrayList<>(quizzes);
        this.basePrice = basePrice;
        this.features = new ArrayList<>();
        this.features.add("base");
    }

    @Override public String getId() { return id; }
    @Override public String getTitle() { return title; }
    @Override public String getDescription() { return description; }
    @Override public List<Module> getModules() { return Collections.unmodifiableList(modules); }
    @Override public List<Quiz> getQuizzes() { return Collections.unmodifiableList(quizzes); }
    @Override public float getPrice() { return basePrice; }
    @Override public List<String> getFeatures() { return Collections.unmodifiableList(features); }

    protected List<String> mutableFeatures() { return features; }

    @Override
    public String summary() {
        return String.format(
                "Course[id=%s, title=%s, price=%.2f, modules=%d, quizzes=%d, features=%s]",
                id, title, getPrice(), modules.size(), quizzes.size(), getFeatures()
        );
    }

	@Override
	public String toString() {
		return this.summary();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
