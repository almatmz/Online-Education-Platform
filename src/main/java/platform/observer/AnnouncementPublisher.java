package platform.observer;

public interface AnnouncementPublisher {
	void subscribeAnnouncements(AnnouncementSubscriber subscriber);
	void unsubscribeAnnouncements(AnnouncementSubscriber subscriber);
	void announce(String text);
}
