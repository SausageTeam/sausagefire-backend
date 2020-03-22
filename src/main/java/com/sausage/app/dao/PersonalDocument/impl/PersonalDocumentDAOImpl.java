package com.sausage.app.dao.PersonalDocument.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.PersonalDocument.PersonalDocumentDAO;
import com.sausage.app.entity.PersonalDocument;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class PersonalDocumentDAOImpl extends AbstractHibernateDAO<PersonalDocument> implements PersonalDocumentDAO {

    private static final String GET_PERSONAL_DOCUMENT = "FROM PersonalDocument WHERE employeeId = : employeeId";

    public PersonalDocumentDAOImpl() { setClazz(PersonalDocument.class);}


    @Override
    public PersonalDocument getPersonalDocumentByEmployeeId(int employeeId) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_PERSONAL_DOCUMENT);
        query.setParameter("employeeId", employeeId);

        @SuppressWarnings("unchecked")
        List<PersonalDocument> personalDocuments = (List<PersonalDocument>) query.getResultList();
        return (personalDocuments.size() > 0) ? personalDocuments.get(0) : null;
    }

    @Override
    public void setPersonalDocument(PersonalDocument personalDocument) {
        Session session = getCurrentSession();
        session.merge(personalDocument);
    }
}
