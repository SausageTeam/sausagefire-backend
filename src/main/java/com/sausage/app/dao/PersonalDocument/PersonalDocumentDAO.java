package com.sausage.app.dao.PersonalDocument;

import com.sausage.app.entity.PersonalDocument;

public interface PersonalDocumentDAO {

    PersonalDocument getPersonalDocumentByEmployeeId(int employeeId);

    void setPersonalDocument(PersonalDocument personalDocument);

}
