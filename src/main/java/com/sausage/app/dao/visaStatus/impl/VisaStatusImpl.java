package com.sausage.app.dao.visaStatus.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.visaStatus.VisaStatusDAO;
import com.sausage.app.entity.VisaStatus;
import org.springframework.stereotype.Repository;

@Repository
public class VisaStatusImpl extends AbstractHibernateDAO<VisaStatus> implements VisaStatusDAO {
    public VisaStatusImpl() { setClazz(VisaStatus.class);}
}
