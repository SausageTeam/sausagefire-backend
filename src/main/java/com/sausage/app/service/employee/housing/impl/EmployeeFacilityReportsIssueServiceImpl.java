package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.FacilityReport.FacilityReportDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.housing.facilityReports.issue.FacilityReportsIssue;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.FacilityReport;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.housing.EmployeeFacilityReportsIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.sausage.app.constant.enums.FacilityReport.FacilityReportStatusEnums.OPEN;

@Service
public class EmployeeFacilityReportsIssueServiceImpl implements EmployeeFacilityReportsIssueService{

    private UserDAO userDAO;

    private EmployeeDAO employeeDAO;

    private FacilityReportDAO facilityReportDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setFacilityReportDAO(FacilityReportDAO facilityReportDAO) {
        this.facilityReportDAO = facilityReportDAO;
    }

    @Override
    @Transactional
    public void setFacilityReportsIssue(int userId, FacilityReportsIssue facilityReportsIssue) {
            User user = userDAO.getUserById(userId);
            Person person = user.getPerson();
            Employee employee = employeeDAO.getEmployeeByPerson(person);

            String title = facilityReportsIssue.getTitle();
            String description = facilityReportsIssue.getDescription();

            LocalDate now = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatDate = now.format(format);

            FacilityReport facilityReport = FacilityReport.builder()
                    .title(title)
                    .employee(employee)
                    .reportDate(formatDate)
                    .description(description)
                    .status(OPEN.getStr()).build();

            facilityReportDAO.setFacilityReport(facilityReport);
    }

}
