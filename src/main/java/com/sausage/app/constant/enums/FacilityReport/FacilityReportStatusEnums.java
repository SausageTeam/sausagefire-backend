package com.sausage.app.constant.enums.FacilityReport;

public enum FacilityReportStatusEnums {
    OPEN(2, "OPEN"),
    IN_PROGRESS(1, "IN_PROGRESS"),
    CLOSED(0, "CLOSED");

    private final int value;
    private final String str;

    FacilityReportStatusEnums(int value, String str) {
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
