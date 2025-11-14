package platform.course.builder;

import platform.course.Course;
import platform.decorator.CertificateCourseDecorator;
import platform.decorator.ExtraMaterialCourseDecorator;

public class PremiumBuilder extends BasicBuilder<PremiumBuilder> {
	private float certificateFee;
	private float extraMaterialFee;

	public PremiumBuilder setCertificateFee(float certificateFee) {
		this.certificateFee = certificateFee;
		return this;
	}

	public PremiumBuilder setExtraMaterialFee(float extraMaterialFee) {
		this.extraMaterialFee = extraMaterialFee;
		return this;
	}

	public Course build() {
		Course course = super.build();

		return new ExtraMaterialCourseDecorator(new CertificateCourseDecorator(course, this.certificateFee), this.extraMaterialFee);
	}
}
