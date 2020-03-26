package com.sausage.app.dao.digitalDocument;

import com.sausage.app.entity.DigitalDocument;

public interface DigitalDocumentDAO {

    DigitalDocument getDigitalDocumentByType(String type);

}
