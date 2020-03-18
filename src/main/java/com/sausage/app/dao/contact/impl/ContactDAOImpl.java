package com.sausage.app.dao.contact.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.contact.ContactDAO;
import com.sausage.app.entity.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl extends AbstractHibernateDAO<Contact> implements ContactDAO {
    public ContactDAOImpl() { setClazz(Contact.class);}
}
