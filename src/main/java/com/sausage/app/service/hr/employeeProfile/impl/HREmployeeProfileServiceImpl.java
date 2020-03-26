package com.sausage.app.service.hr.employeeProfile.impl;

import com.sausage.app.dao.visaStatus.VisaStatusDAO;
import com.sausage.app.domain.hr.employeeProfile.EmployeeProfile;
import com.sausage.app.domain.hr.employeeProfile.EmployeeRecord;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.VisaStatus;
import com.sausage.app.service.hr.employeeProfile.HREmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HREmployeeProfileServiceImpl implements HREmployeeProfileService {

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Override
    @Transactional
    public EmployeeProfile getEmployeeProfile() {
        List<EmployeeRecord> employeeRecordList = new ArrayList<>();
        List<Employee> employeeList = employeeDAO.getAllEmployee();
        for (Employee employee : employeeList) {
            Person person = employee.getPerson();
            VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(employee.getVisaStatusId());
            EmployeeRecord employeeRecord = EmployeeRecord.builder()
                    .firstName(person.getFirstName())
                    .middleName(person.getMiddleName())
                    .lastName(person.getLastName())
                    .ssn(person.getSSN())
                    .startDate(employee.getStartDate())
                    .visaStatus(visaStatus.getVisaType())
                    .build();
            employeeRecordList.add(employeeRecord);
        }

        return EmployeeProfile.builder()
                .employeeRecordList(employeeRecordList)
                .build();
    }

    @Override
    public void setEmployeeProfile() {

    }

}
