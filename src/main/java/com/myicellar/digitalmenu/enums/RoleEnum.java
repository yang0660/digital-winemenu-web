package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleEnum {
    ADMINISTRATOR(8L, "管理员")
    ;

    public final Long roleId;
    public final String desc;

    public static RoleEnum enumOf(Long roleId) {
        for(RoleEnum e: RoleEnum.values()) {
            if(e.roleId.equals(roleId)) {
                return e;
            }
        }
        return null;
    }
}
