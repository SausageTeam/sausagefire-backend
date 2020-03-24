package com.sausage.app.dao.VisaStatus.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.entity.VisaStatus;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class VisaStatusImpl extends AbstractHibernateDAO<VisaStatus> implements VisaStatusDAO {

    private static final String GET_OTHER_VISA_STATUS = "FROM VisaStatus WHERE visaType = :visaType";

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

    @Override
    public VisaStatus setOtherVisaStatus(String visaType) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_OTHER_VISA_STATUS);
        query.setParameter("visaType", visaType);

        @SuppressWarnings("unchecked")
        List<VisaStatus> visaStatusList = query.getResultList();
        return (visaStatusList.size() > 0) ? visaStatusList.get(0) : null;
    }


}
