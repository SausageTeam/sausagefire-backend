package com.sausage.app.dao.personalDocument.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.personalDocument.PersonalDocumentDAO;
import com.sausage.app.entity.PersonalDocument;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalDocumentDAOImpl extends AbstractHibernateDAO<PersonalDocument> implements PersonalDocumentDAO {
    public PersonalDocumentDAOImpl() { setClazz(PersonalDocument.class);}
}
