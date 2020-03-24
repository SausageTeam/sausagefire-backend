package com.sausage.app.dao.FacilityReport.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Facility.FacilityDAO;
import com.sausage.app.entity.FacilityReport;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDAOImpl extends AbstractHibernateDAO<FacilityReport> implements FacilityDAO {
    public FacilityReportDAOImpl() { setClazz(FacilityReport.class);}
}
