package com.sausage.app.dao.facilityReportDetail.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.facilityReportDetail.FacilityReportDetailDAO;
import com.sausage.app.entity.FacilityReport;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDetailDetailDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityReportDetailDAO {
    public FacilityReportDetailDetailDAOImpl() { setClazz(FacilityReport.class);}
}
