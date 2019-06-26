package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSexEnum {
    MALE((byte) 1, "男"),
    FEMALE((byte) 2, "女");

    private final Byte value;
    private final String desc;

    public static UserSexEnum enumOf(byte value) {
        for (UserSexEnum userSexEnum : values()) {
            if (userSexEnum.value == value) {
                return userSexEnum;
            }
        }
        return null;
    }
}
