package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

/**
 * 用户类型枚举
 */
@AllArgsConstructor
public enum AttrCatgEnum {
    STYLE((byte) 1, (long)1001,"风格"),
    DESCRIPTOR((byte) 2, (long)1002,"口味"),
    GRAP((byte) 3, (long)101,"葡萄");

    public final Byte attrCatgNo;
    public final Long attrCatgId;
    public final String desc;

    public static AttrCatgEnum enumOf(Byte attrCatgNo) {
        for(AttrCatgEnum e: AttrCatgEnum.values()) {
            if(e.attrCatgNo.equals(attrCatgNo)) {
                return e;
            }
        }
        return null;
    }
}
