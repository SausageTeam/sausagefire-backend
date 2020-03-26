package com.sausage.app.dao.applicationWorkFlow.enums;

public enum ApplicationWorkFlowNotifyEnums {
    NOTIFIED(1, "NOTIFIED"),
    NOT_NOTIFIED(0, "NOT_NOTIFIED");

    private final int value;
    private final String str;

    ApplicationWorkFlowNotifyEnums(int value, String str) {
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
