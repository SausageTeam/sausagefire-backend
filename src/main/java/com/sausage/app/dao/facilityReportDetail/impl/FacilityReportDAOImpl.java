package com.sausage.app.dao.facilityReportDetail.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.facilityReportDetail.FacilityReportDAO;
import com.sausage.app.entity.FacilityReport;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityReportDAO {
    public FacilityReportDAOImpl() { setClazz(FacilityReport.class);}
}
