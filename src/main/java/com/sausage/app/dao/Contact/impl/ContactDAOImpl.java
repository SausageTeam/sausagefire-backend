package com.sausage.app.dao.Contact.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.entity.Contact;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl extends AbstractHibernateDAO<Contact> implements ContactDAO {

    public ContactDAOImpl() { setClazz(Contact.class);}

    @Override
    public Contact getContactById(int id) {
        return findById(id);
    }

    @Override
    public Contact setContact(Contact contact) {
        Session session = getCurrentSession();
        return (Contact) session.merge(contact);
    }

}
