package com.sausage.app.dao.Person.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.entity.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl extends AbstractHibernateDAO<Person> implements PersonDAO {

//    private static final String GET_PERSON = "FROM Person WHERE firstName = :firstName AND lastName = :lastName AND email = :email";

    public PersonDAOImpl() {
        setClazz(Person.class);
    }

    @Override
    public Person getPersonById(int id) {
        return findById(id);
    }

    @Override
    public void updatePerson(Person person) {
        Session session = getCurrentSession();
        session.merge(person);
    }

//    @Override
//    public Person updatePersonNoId(Person person) {
//        Session session = getCurrentSession();
//        Query query = session.createQuery(GET_PERSON);
//        query.setParameter("firstName", person.getFirstName());
//        query.setParameter("lastName", person.getLastName());
//        query.setParameter("email", person.getEmail());
//
//        @SuppressWarnings("unchecked")
//        List<Person> persons = (List<Person>) query.getResultList();
//        if (persons.size() == 0) {
//            return (Person) session.merge(person);
//        } else {
//            return persons.get(0);
//        }
//    }

}
