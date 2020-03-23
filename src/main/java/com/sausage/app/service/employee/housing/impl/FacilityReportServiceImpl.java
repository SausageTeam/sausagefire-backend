package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.dao.facilityReport.FacilityReportDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistory;
import com.sausage.app.entity.*;
import com.sausage.app.service.employee.housing.FacilityReportService;
import com.sausage.app.service.employee.housing.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityReportServiceImpl implements FacilityReportService {
    private UserDAO userDAO;
    private EmployeeDAO employeeDAO;
    private FacilityReportDAO facilityReportDAO;
    private UserService userService;
    private PersonDAO personDAO;

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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public List<MaintenanceHistory> getReportListFromEmployee(int userID) {
        System.out.println(userID);
        User user = userDAO.getUser(userID);

        int personID = userService.getPersonID(user);
        Employee employee = employeeDAO.getEmployee(personID);
        int employeeID = employee.getId();
        List<MaintenanceHistory> maintenanceHistoryList = new ArrayList<>();
        List<FacilityReport> facilityReportList = facilityReportDAO.getFacilityReportListFromEmployee(employeeID);
        for(FacilityReport facilityReport: facilityReportList) {
            MaintenanceHistory maintenanceHistory = new MaintenanceHistory();
            maintenanceHistory.setId(employeeID);
            maintenanceHistory.setTitle(facilityReport.getTitle());
            maintenanceHistory.setDescription(facilityReport.getDescription());
            maintenanceHistory.setStatus(facilityReport.getStatus());
            maintenanceHistory.setReportDate(facilityReport.getReportDate());
            maintenanceHistoryList.add(maintenanceHistory);
        }
        return maintenanceHistoryList;
    }

    @Override
    @Transactional
    public FacilityReport updateFacilityReport(String title, int employeeID, String reportDate, String description, String status) {
        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setTitle(title);
        facilityReport.setEmployeeID(employeeID);
        facilityReport.setReportDate(reportDate);
        facilityReport.setDescription(description);
        facilityReport.setStatus(status);
        return facilityReportDAO.updateFacilityReport(facilityReport);
    }

    @Override
    @Transactional
    public int getEmployeeID(int userID) {
        User user = userDAO.getUser(userID);
        int personID = user.getPersonId();
        Person p = personDAO.getPerson(personID);
        Employee e = employeeDAO.getEmployee(p.getId());
        return e.getId();
    }
}
