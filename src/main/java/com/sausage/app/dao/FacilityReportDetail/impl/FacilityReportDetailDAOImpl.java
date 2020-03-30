package com.sausage.app.dao.FacilityReportDetail.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.FacilityReportDetail.FacilityReportDetailDAO;
import com.sausage.app.entity.FacilityReport;
import com.sausage.app.entity.FacilityReportDetail;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class FacilityReportDetailDAOImpl extends AbstractHibernateDAO<FacilityReportDetail> implements FacilityReportDetailDAO {

    private static final String GET_FACILITY_REPORT_DETAILS_BY_REPORT = "FROM FacilityReportDetail WHERE facilityReport = :facilityReport";

    public FacilityReportDetailDAOImpl() {
        setClazz(FacilityReportDetail.class);
    }

    @Override
    public FacilityReportDetail getFacilityReportDetailById(int id) {
        return findById(id);
    }

    @Override
    public void setFacilityReportDetail(FacilityReportDetail facilityReportDetail) {
        Session session = getCurrentSession();
        session.merge(facilityReportDetail);
    }

    @Override
    public List<FacilityReportDetail> getFacilityReportDetailsByReport(FacilityReport facilityReport) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_FACILITY_REPORT_DETAILS_BY_REPORT);
        query.setParameter("facilityReport", facilityReport);

        @SuppressWarnings("unchecked")
        List<FacilityReportDetail> facilityReportDetailList = query.getResultList();
        return facilityReportDetailList;
    }
}
