package com.sausage.app.service.hr.hire.applicationReview.impl;

import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.domain.common.AddressDomain;
import com.sausage.app.domain.hr.hire.applicationReview.Application;
import com.sausage.app.domain.hr.hire.applicationReview.ApplicationResult;
import com.sausage.app.domain.hr.hire.applicationReview.HireApplicationReview;
import com.sausage.app.entity.*;
import com.sausage.app.fileIO.FileOutput;
import com.sausage.app.fileIO.URIHandler;
import com.sausage.app.service.hr.hire.applicationReview.HRHireApplicationReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.sausage.app.constant.Constant.DEFAULT_FILE_PATH;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOnboardingStatusEnums.APPROVE;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOnboardingStatusEnums.PROCESSING;

@Service
public class HRHireApplicationReviewServiceImpl implements HRHireApplicationReviewService {

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    private ContactDAO contactDAO;

    private AddressDAO addressDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

    private URIHandler uriHandler;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Autowired
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Autowired
    public void setUriHandler(URIHandler uriHandler) {
        this.uriHandler = uriHandler;
    }

    @Override
    @Transactional
    public HireApplicationReview getHireApplicationReview() {
        List<Application> applicationList = new ArrayList<>();
        List<ApplicationWorkFlow> applicationWorkFlowList = applicationWorkFlowDAO.getAllOnboardingWaitingApplicationWorkFlow();
        for (ApplicationWorkFlow applicationWorkFlow : applicationWorkFlowList) {
            Employee employee = applicationWorkFlow.getEmployee();
            int visaStatusId = employee.getVisaStatusId();
            VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(visaStatusId);
            Person person = employee.getPerson();

            String avatarPath = String.format(DEFAULT_FILE_PATH, employee.getId(), "avatar.jpg");
            FileOutput.getAvatar(avatarPath);

            Contact referenceContact = contactDAO.getContactById(employee.getReferenceId());
            Person referencePerson = referenceContact.getPerson();
            Address referenceAddress = addressDAO.getAddressByPerson(referencePerson);

            Contact emergencyContact = contactDAO.getContactById(employee.getEmergencyId());
            Person emergencyPerson = emergencyContact.getPerson();
            Address emergencyAddress = addressDAO.getAddressByPerson(emergencyPerson);

            int employeeId = employee.getId();

            /* Person */
            String firstName = person.getFirstName();

            String lastName = person.getLastName();

            String middleName = person.getMiddleName();

            String preferredName = person.getPreferredName();

            String email = person.getEmail();

            String cellPhone = person.getCellphone();

            String alternatePhone = person.getAlternatePhone();

            String gender = person.getGender();

            String ssn = person.getSSN();

            String dob = person.getDOB();

            /* Avatar */
            String avatarUri = uriHandler.getUri(String.valueOf(employee.getId()), "avatar.jpg");

            /* Visa */
            String visaType = visaStatus.getVisaType();

            String visaStartDate = employee.getVisaStartDate();

            String visaEndDate = employee.getVisaEndDate();

            /* Driving */
            String driverLicense = employee.getDriverLicense();

            String driverLicenseExpirationDate = employee.getDriverLicenseExpirationDate();

            String car = employee.getCar();
            String maker = null;
            String model = null;
            String color = null;

            if (car != null) {
                String[] arr_car = car.split("_");
                if (arr_car.length >= 3) {
                    maker = arr_car[0];
                    model = arr_car[1];
                    color = arr_car[2];
                }
            }

            /* Reference */
            String referenceFirstName = referencePerson.getFirstName();

            String referenceLastName = referencePerson.getLastName();

            String referenceMiddleName = referencePerson.getMiddleName();

            String referenceEmail = referencePerson.getEmail();

            String referenceCellPhone = referencePerson.getCellphone();

            AddressDomain referenceAddressDomain = AddressDomain.builder()
                    .addressLineOne(referenceAddress.getAddressLineOne())
                    .addressLineTwo(referenceAddress.getAddressLineTwo())
                    .city(referenceAddress.getCity())
                    .zipCode(referenceAddress.getZipCode())
                    .stateName(referenceAddress.getStateName())
                    .stateAbbr(referenceAddress.getStateAbbr())
                    .build();

            String referenceRelationship = referenceContact.getRelationship();

            String referenceTitle = referenceContact.getTitle();

            /* Emergency */
            String emergencyFirstName = emergencyPerson.getFirstName();

            String emergencyLastName = emergencyPerson.getLastName();

            String emergencyMiddleName = emergencyPerson.getMiddleName();

            String emergencyEmail = emergencyPerson.getEmail();

            String emergencyCellPhone = emergencyPerson.getCellphone();

            AddressDomain emergencyAddressDomain = AddressDomain.builder()
                    .addressLineOne(emergencyAddress.getAddressLineOne())
                    .addressLineTwo(emergencyAddress.getAddressLineTwo())
                    .city(emergencyAddress.getCity())
                    .zipCode(emergencyAddress.getZipCode())
                    .stateName(emergencyAddress.getStateName())
                    .stateAbbr(emergencyAddress.getStateAbbr())
                    .build();

            String emergencyRelationship = emergencyContact.getRelationship();

            String emergencyTitle = emergencyContact.getTitle();

            Application application = Application.builder()
                    .employeeId(employeeId)
                    .firstName(firstName)
                    .lastName(lastName)
                    .middleName(middleName)
                    .preferredName(preferredName)
                    .email(email)
                    .cellPhone(cellPhone)
                    .alternatePhone(alternatePhone)
                    .gender(gender)
                    .ssn(ssn)
                    .dob(dob)
                    .avatarUri(avatarUri)
                    .visaType(visaType)
                    .visaStartDate(visaStartDate)
                    .visaEndDate(visaEndDate)
                    .driverLicense(driverLicense)
                    .driverLicenseExpirationDate(driverLicenseExpirationDate)
                    .maker(maker)
                    .model(model)
                    .color(color)
                    .referenceFirstName(referenceFirstName)
                    .referenceLastName(referenceLastName)
                    .referenceMiddleName(referenceMiddleName)
                    .referenceEmail(referenceEmail)
                    .referenceCellPhone(referenceCellPhone)
                    .referenceAddressDomain(referenceAddressDomain)
                    .referenceRelationship(referenceRelationship)
                    .referenceTitle(referenceTitle)
                    .emergencyFirstName(emergencyFirstName)
                    .emergencyLastName(emergencyLastName)
                    .emergencyMiddleName(emergencyMiddleName)
                    .emergencyEmail(emergencyEmail)
                    .emergencyCellPhone(emergencyCellPhone)
                    .emergencyAddressDomain(emergencyAddressDomain)
                    .emergencyRelationship(emergencyRelationship)
                    .emergencyTitle(emergencyTitle).build();
            applicationList.add(application);
        }
        return HireApplicationReview.builder()
                .applicationList(applicationList)
                .build();
    }

    @Override
    @Transactional
    public void setHireApplicationReview(ApplicationResult applicationResult) {
        int employeeId = applicationResult.getEmployeeId();
        boolean accept = applicationResult.isAccept();
        String comments = applicationResult.getComments();
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
        applicationWorkFlow.setComments(comments);
        if (accept) {
            applicationWorkFlow.setStatus(APPROVE.getValue());
        } else {
            applicationWorkFlow.setStatus(PROCESSING.getValue());
        }
        applicationWorkFlowDAO.setApplicationWorkFlow(applicationWorkFlow);
    }
}
