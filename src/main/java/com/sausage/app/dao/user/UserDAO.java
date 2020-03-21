package com.sausage.app.dao.user;

import com.sausage.app.domain.housing.housingInfo.HousingInfoRequest;
import com.sausage.app.entity.User;

public interface UserDAO {
    User getUser(int id); // (int userID)
}
