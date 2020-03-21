package com.sausage.app.dao.Person;

import com.sausage.app.domain.housing.housingInfo.Resident;
import com.sausage.app.entity.Person;

public interface PersonDAO {
    Person getPerson(int id);
}
