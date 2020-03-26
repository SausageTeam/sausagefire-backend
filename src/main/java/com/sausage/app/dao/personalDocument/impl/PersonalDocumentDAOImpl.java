package com.sausage.app.dao.personalDocument.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.personalDocument.PersonalDocumentDAO;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.PersonalDocument;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class PersonalDocumentDAOImpl extends AbstractHibernateDAO<PersonalDocument> implements PersonalDocumentDAO {

    private static final String GET_PERSONAL_DOCUMENT_BY_EMPLOYEE = "FROM PersonalDocument WHERE employee = :employee";

    private static final String GET_LATEST_DOCUMENT = "FROM PersonalDocument WHERE employee = :employee ORDER BY id DESC";

    private static final String GET_PERSONAL_DRIVING_DOCUMENT = "FROM PersonalDocument WHERE employee = :employee AND title = :title";

    public PersonalDocumentDAOImpl() { setClazz(PersonalDocument.class);}


    @Override
    public List<PersonalDocument> getPersonalDocumentByEmployee(Employee employee) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_PERSONAL_DOCUMENT_BY_EMPLOYEE);
        query.setParameter("employee", employee);

        @SuppressWarnings("unchecked")
        List<PersonalDocument> personalDocumentList = (List<PersonalDocument>) query.getResultList();
        return personalDocumentList;
    }

    @Override
    public PersonalDocument getPersonalDrivingDocumentByEmployee(Employee employee) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_PERSONAL_DRIVING_DOCUMENT);
        query.setParameter("employee", employee);
        query.setParameter("title", Constant.EMPLOYEE_DRIVER_LICENSE_TITLE);

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
