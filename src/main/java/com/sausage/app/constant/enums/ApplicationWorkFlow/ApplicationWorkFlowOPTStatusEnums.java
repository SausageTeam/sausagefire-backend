package com.sausage.app.constant.enums.ApplicationWorkFlow;

public enum ApplicationWorkFlowOPTStatusEnums {
    OPT_RECEIPT(1, "OPT RECEIPT"),
    OPT_EAD(2, "OPT EAD"),
    I983(3, "I983"),
    I20(4, "I20"),
    OPT_STEM_RECEIPT(5, "OPT STEM RECEIPT"),
    OPT_STEM_EAD(6, "OPT STEM EAD");

    private final int value;
    private final String str;

    ApplicationWorkFlowOPTStatusEnums(int value, String str) {
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
