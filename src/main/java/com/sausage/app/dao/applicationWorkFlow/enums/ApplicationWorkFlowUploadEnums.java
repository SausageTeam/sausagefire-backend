package com.sausage.app.dao.applicationWorkFlow.enums;

public enum ApplicationWorkFlowUploadEnums {
    WAITING(1, "WAITING"),
    REQUIRE(0, "REQUIRE"),
    REJECT(-1, "REJECT");

    private final int value;
    private final String str;

    ApplicationWorkFlowUploadEnums(int value, String str) {
        this.value = value;
        this.str = str;
    }

    public int getValue() {
        return value;
    }

    public String getStr() {
        return str;
    }
}
