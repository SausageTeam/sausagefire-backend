package com.sausage.app.dao.DigitalDocument.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.DigitalDocument.DigitalDocumentDAO;
import com.sausage.app.entity.DigitalDocument;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class DigitalDocumentDAOImpl extends AbstractHibernateDAO<DigitalDocument> implements DigitalDocumentDAO {

    private static final String GET_DIGITAL_DOCUMENT = "FROM DigitalDocument WHERE type = :type";

    public DigitalDocumentDAOImpl() { setClazz(DigitalDocument.class);}

    @Override
    public DigitalDocument getDigitalDocumentByType(String type) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_DIGITAL_DOCUMENT);
        query.setParameter("type", type);

        @SuppressWarnings("unchecked")
        List<DigitalDocument> digitalDocumentList = query.getResultList();

        return (digitalDocumentList.size() > 0) ? digitalDocumentList.get(0) : null;
    }

}
