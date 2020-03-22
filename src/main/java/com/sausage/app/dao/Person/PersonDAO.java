package com.sausage.app.dao.Person;

import com.sausage.app.entity.Person;

public interface PersonDAO {

    Person getPersonById(int id);

    void updatePerson(Person person);

    Person updatePersonNoId(Person person);

}
