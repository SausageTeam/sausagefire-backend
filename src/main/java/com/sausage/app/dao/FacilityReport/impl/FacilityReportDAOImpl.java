package com.sausage.app.dao.FacilityReport.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.FacilityReport.FacilityReportDAO;
import com.sausage.app.entity.FacilityReport;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class FacilityReportDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityReportDAO {

    private static final String GET_ALL_FACILITY_REPORT = "FROM FacilityReport";

    public FacilityReportDAOImpl() {
        setClazz(FacilityReport.class);
    }

    @Override
    public void setFacilityReport(FacilityReport facilityReport) {
        Session session = getCurrentSession();
        session.merge(facilityReport);
    }

    @Override
    public List<FacilityReport> getAllFacilityReport() {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_ALL_FACILITY_REPORT);

        @SuppressWarnings("unchecked")
        List<FacilityReport> facilityReportList = query.getResultList();
        return facilityReportList;
    }

}
