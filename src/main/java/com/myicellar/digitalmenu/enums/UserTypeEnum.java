package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

/**
 * 用户类型枚举
 */
@AllArgsConstructor
public enum UserTypeEnum {
    EMPLOYEE((byte) 1, "员工");

    public final Byte value;
    public final String desc;

    public static UserTypeEnum enumOf(Byte value) {
        for (UserTypeEnum e : UserTypeEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
