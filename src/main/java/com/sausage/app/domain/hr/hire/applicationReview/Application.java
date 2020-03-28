package com.sausage.app.domain.hr.hire.applicationReview;

import com.sausage.app.domain.common.AddressDomain;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    int employeeId;

    /* Person */
    String firstName;

    String lastName;

    String middleName;

    String preferredName;

    String email;

    String cellPhone;

    String alternatePhone;

    String gender;

    String ssn;

    String dob;

    /* Avatar */
    String avatarUri;

    /* Visa */
    String visaType;

    String visaStartDate;

    String visaEndDate;

    /* Driving */
    String driverLicense;

    String driverLicenseExpirationDate;

    String maker;

    String model;

    String color;

    /* Reference */
    String referenceFirstName;

    String referenceLastName;

    String referenceMiddleName;

    String referenceEmail;

    String referenceCellPhone;

    AddressDomain referenceAddressDomain;

    String referenceRelationship;

    String referenceTitle;

    /* Emergency */
    String emergencyFirstName;

    String emergencyLastName;

    String emergencyMiddleName;

    String emergencyEmail;

    String emergencyCellPhone;

    AddressDomain emergencyAddressDomain;

    String emergencyRelationship;

    String emergencyTitle;

}
