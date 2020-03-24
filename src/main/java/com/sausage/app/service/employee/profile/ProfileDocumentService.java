package com.sausage.app.service.profile;

import com.sausage.app.domain.profile.profileDocument.ProfileDocument;

public interface ProfileDocumentService {

    ProfileDocument getProfileDocument(int userId);

    void setProfileDocument(int userId, ProfileDocument profileDocument);

}
