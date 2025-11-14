package platform.observer;

public interface AnnouncementPublisher {
	void subscribe(AnnouncementSubscriber subscriber);
	void unsubscribe(AnnouncementSubscriber subscriber);
	void announce(String text);
}
