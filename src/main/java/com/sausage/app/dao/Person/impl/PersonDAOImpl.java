package com.sausage.app.dao.Person.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl extends AbstractHibernateDAO<Person> implements PersonDAO {
    public PersonDAOImpl() { setClazz(Person.class); }

    @Override
    public Person getPerson(int id) {
        return findById(id);
    }
}
