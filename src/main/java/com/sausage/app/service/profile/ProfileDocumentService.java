package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileDocument.ProfileDocument;

public interface ProfileDocumentService {

    ProfileDocument getProfileDocument(int userId);

    void setProfileDocument(int userId, ProfileDocument profileDocument);

}
