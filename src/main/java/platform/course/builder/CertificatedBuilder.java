package platform.course.builder;

import platform.course.Course;
import platform.decorator.CertificateCourseDecorator;

public class CertificatedBuilder extends BasicBuilder<CertificatedBuilder> {
	private float certificateFee;

	public CertificatedBuilder setCertificateFee(float certificateFee) {
		this.certificateFee = certificateFee;
		return this;
	}

	public Course build() {
		Course course = super.build();

		return new CertificateCourseDecorator(course, this.certificateFee);
	}
}
