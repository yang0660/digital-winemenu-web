package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SmsCodeTypeEnum {
    REGISTER((byte) 1, "注册"), RESET_PASSWORD((byte) 2, "重置密码");

    public final Byte value;
    public final String desc;

    public static SmsCodeTypeEnum enumOf(Byte value) {
        for(SmsCodeTypeEnum e: SmsCodeTypeEnum.values()) {
            if(e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
