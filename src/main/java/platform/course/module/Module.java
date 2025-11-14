package platform.course.module;

public class Module {
    private final String title;
    private final String content;

    public Module(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
		return this.title;
	}

    public String getContent() {
		return this.content;
	}
}