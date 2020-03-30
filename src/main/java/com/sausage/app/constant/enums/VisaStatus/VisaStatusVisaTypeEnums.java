package com.sausage.app.constant.enums.VisaStatus;

public enum VisaStatusVisaTypeEnums {
    CITIZEN(1),
    GREEN_CARD(2),
    H1_B(3),
    L2(4),
    F1(5),
    H4(6);

    private final int value;
    private VisaStatusVisaTypeEnums(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
