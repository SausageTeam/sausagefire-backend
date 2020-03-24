package com.sausage.app.dao.PersonalDocument.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.PersonalDocument.PersonalDocumentDAO;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.PersonalDocument;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class PersonalDocumentDAOImpl extends AbstractHibernateDAO<PersonalDocument> implements PersonalDocumentDAO {

    private static final String GET_LATEST_DOCUMENT = "FROM PersonalDocument WHERE employee = :employee ORDER BY id DESC";

    private static final String GET_PERSONAL_DOCUMENT = "FROM PersonalDocument WHERE employee = :employee";

    public PersonalDocumentDAOImpl() { setClazz(PersonalDocument.class);}


    @Override
    public PersonalDocument getPersonalDocumentByEmployee(Employee employee) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_PERSONAL_DOCUMENT);
        query.setParameter("employee", employee);

        @SuppressWarnings("unchecked")
        List<PersonalDocument> personalDocuments = (List<PersonalDocument>) query.getResultList();
        return (personalDocuments.size() > 0) ? personalDocuments.get(0) : null;
    }

    @Override
    public void setPersonalDocument(PersonalDocument personalDocument) {
        Session session = getCurrentSession();
        session.merge(personalDocument);
    }

    @Override
    public PersonalDocument getLatestDocumentByEmployee(Employee employee) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_LATEST_DOCUMENT);
        query.setParameter("employee", employee);

        @SuppressWarnings("unchecked")
        List<PersonalDocument> personalDocuments = (List<PersonalDocument>) query.getResultList();
        return (personalDocuments.size() > 0) ? personalDocuments.get(0) : null;
    }
}
