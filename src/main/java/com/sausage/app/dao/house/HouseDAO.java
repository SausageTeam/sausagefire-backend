package com.sausage.app.dao.house;

import com.sausage.app.entity.House;

import java.util.List;

public interface HouseDAO {
    House getHouse(int id); // (int userID)
    List<House> getAllHouses();
}
