package com.sausage.app.constant.enums.ApplicationWorkFlow;

public enum ApplicationWorkFlowTypeEnums {
    ONBOARDING(1, "ONBOARDING"),
    OPT(2, "OPT");

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
