package com.sausage.app.dao.Contact;

import com.sausage.app.entity.Contact;
import com.sausage.app.entity.Person;

public interface ContactDAO {

    Contact getContactById(int id);

    Contact setContact(Contact contact);

    Contact getContactByPerson(Person person);

}
