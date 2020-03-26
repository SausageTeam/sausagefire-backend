package com.sausage.app.dao.applicationWorkFlow.enums;

public enum ApplicationWorkFlowTypeEnums {
    ONBOARDING_TYPE(1, "ONBOARDING"),
    OPT_TYPE(2, "OPT");

    private final int value;
    private final String str;

    ApplicationWorkFlowTypeEnums(int value, String str) {
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
