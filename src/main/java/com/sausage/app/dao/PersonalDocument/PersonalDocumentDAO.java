package com.sausage.app.dao.PersonalDocument;

import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.PersonalDocument;

import java.util.List;

public interface PersonalDocumentDAO {

    List<PersonalDocument> getPersonalDocumentByEmployee(Employee employee);

    PersonalDocument getPersonalDrivingDocumentByEmployee(Employee employee);

    void setPersonalDocument(PersonalDocument personalDocument);

    PersonalDocument getLatestDocumentByEmployee(Employee employee);

}
