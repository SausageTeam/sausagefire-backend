package com.sausage.app.dao.VisaStatus.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.entity.VisaStatus;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class VisaStatusImpl extends AbstractHibernateDAO<VisaStatus> implements VisaStatusDAO {

    public VisaStatusImpl() { setClazz(VisaStatus.class);}

    @Override
    public VisaStatus getVisaStatusById(int id) {
        return findById(id);
    }

    @Override
    public VisaStatus setVisaStatus(VisaStatus visaStatus) {
        Session session = getCurrentSession();
        return (VisaStatus) session.merge(visaStatus);
    }

}
