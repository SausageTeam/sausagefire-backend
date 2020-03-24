package com.sausage.app.dao.PersonalDocument;

import com.sausage.app.entity.Employee;
import com.sausage.app.entity.PersonalDocument;

public interface PersonalDocumentDAO {

    PersonalDocument getPersonalDocumentByEmployee(Employee employee);

    void setPersonalDocument(PersonalDocument personalDocument);

    PersonalDocument getLatestDocumentByEmployee(Employee employee);

}
