package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatusEnum {
    OPENED((byte) 1, "开启"), CLOSED((byte) 0, "关闭");

    public final Byte value;
    public final String desc;

    public static UserStatusEnum enumOf(Byte value) {
        for(UserStatusEnum e: UserStatusEnum.values()) {
            if(e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
