package com.sausage.app.dao.digitalDocument.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.digitalDocument.DigitalDocumentDAO;
import com.sausage.app.entity.DigitalDocument;
import org.springframework.stereotype.Repository;

@Repository
public class DigitalDocumentDAOImpl extends AbstractHibernateDAO<DigitalDocument> implements DigitalDocumentDAO {
    public DigitalDocumentDAOImpl() { setClazz(DigitalDocument.class);}
}
