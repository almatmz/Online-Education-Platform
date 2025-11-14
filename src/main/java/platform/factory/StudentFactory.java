package platform.factory;

import platform.users.Student;

public class StudentFactory implements UserFactory {
	@Override
	public Student createUser() {
		return new Student();
	}
}
