package com.sausage.app.dao.ApplicationWorkFlow.enums;

public enum ApplicationWorkFlowEnums {
    OPT_RECEIPT(1, "OPT_RECEIPT"),
    OPT_EAD(2, "OPT_EAD"),
    I983(3, "I983"),
    I20(4, "I20"),
    OPT_STEM_RECEIPT(5, "OPT_STEM_RECEIPT"),
    OPT_STEM_EAD(6, "OPT_STEM_EAD");

    private final int value;
    private final String str;

    ApplicationWorkFlowEnums(int value, String str) {
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
