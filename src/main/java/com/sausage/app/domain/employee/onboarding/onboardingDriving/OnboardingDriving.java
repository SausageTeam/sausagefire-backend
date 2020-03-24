package com.sausage.app.domain.employee.onboarding.onboardingDriving;

import lombok.*;

import java.io.File;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingDriving {

    String driverLicense;

    String driverLicenseExpirationDate;

    File driverLicenseDoc;

    String maker;

    String model;

    String color;

}
