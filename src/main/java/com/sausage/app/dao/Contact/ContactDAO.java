package com.sausage.app.dao.Contact;

import com.sausage.app.entity.Contact;

public interface ContactDAO {

    Contact getContactById(int id);

    Contact setContact(Contact contact);

}
