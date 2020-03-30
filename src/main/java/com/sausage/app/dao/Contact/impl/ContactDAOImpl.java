package com.sausage.app.dao.Contact.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.entity.Contact;
import com.sausage.app.entity.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContactDAOImpl extends AbstractHibernateDAO<Contact> implements ContactDAO {

    private static final String GET_CONTACT_BY_PERSON = "FROM Contact WHERE person = :person";

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

    @Override
    public Contact getContactByPerson(Person person) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_CONTACT_BY_PERSON);
        query.setParameter("person", person);

        @SuppressWarnings("unchecked")
        List<Contact> contactList = query.getResultList();
        return (contactList.size() > 0) ? contactList.get(0) : null;
    }

}
