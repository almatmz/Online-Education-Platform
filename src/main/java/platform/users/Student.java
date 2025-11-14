package platform.users;

import java.util.UUID;

import platform.observer.AnnouncementSubscriber;

public class Student implements User, AnnouncementSubscriber {
	private UUID id = UUID.randomUUID();
	private int skill;

	public Student() { }

	@Override
	public UUID getId() {
		return this.id;
	}

	@Override
	public int getSkill() {
		return this.skill;
	}

	@Override
	public void setSkill(int skill) {
		this.skill = skill;
	}

	@Override
	public void announcement(String announcement) {
		System.out.println(announcement);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
