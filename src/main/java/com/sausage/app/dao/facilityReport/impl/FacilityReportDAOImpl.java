package com.sausage.app.dao.facilityReport.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.dao.facilityReport.FacilityReportDAO;
import com.sausage.app.entity.FacilityReport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacilityReportDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityReportDAO {
    private PersonDAO personDAO;
    private EmployeeDAO employeeDAO;

    private static final String GET_FACILITY_REPORT_FROM_EMPLOYEE = "FROM FacilityReport  WHERE employeeID = :employeeID";
    private static final String INSERT_INTO_FACILITY_REPORT = "INSERT INTO FacilityReport(title, employeeID, reportDate, description, status)";

    public FacilityReportDAOImpl() { setClazz(FacilityReport.class);}

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<FacilityReport> getFacilityReportListFromEmployee(int employeeID) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_FACILITY_REPORT_FROM_EMPLOYEE);
        query.setParameter("employeeID", employeeID);
        List<FacilityReport> facilityReportList = query.getResultList();
        return facilityReportList.size() > 0 ? facilityReportList : null;
    }

    @Override
    public FacilityReport updateFacilityReport(FacilityReport facilityReport) {
        Session session = getCurrentSession();
        return (FacilityReport) session.merge(facilityReport);
    }
}
