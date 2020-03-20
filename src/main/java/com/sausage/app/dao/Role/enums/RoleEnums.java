package com.sausage.app.dao.Role.enums;

public enum RoleEnums {
    EMPLOYEE(1),
    HR(2);

    private final int value;
    RoleEnums(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
