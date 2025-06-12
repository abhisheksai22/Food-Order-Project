package com.infy.user.entity.constant;

import lombok.Getter;

@Getter
public enum AddressType {

    PERMANENT("Permanent"),
    PRESENT("Present"),
    OFFICE("Office"),
    FRIEND("Friend");

    private final String value;

    AddressType(String s) {
        this.value = s;
    }

}
