package platform.users;

import java.util.UUID;

public interface User {
	UUID getId();
	int getSkill();
	void setSkill(int skill);
}
