package com.sausage.app.dao.FacilityReportDetail.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.FacilityReportDetail.FacilityReportDetailDAO;
import com.sausage.app.entity.FacilityReport;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDetailDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityReportDetailDAO {
    public FacilityReportDetailDAOImpl() { setClazz(FacilityReport.class);}
}
