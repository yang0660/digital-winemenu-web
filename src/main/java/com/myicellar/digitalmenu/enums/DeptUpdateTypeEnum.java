package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeptUpdateTypeEnum {
    DEPT_NAME((byte) 1, "修改部门名称"),
    DEPT_CODE((byte) 2, "修改部门编号");

    public final Byte value;
    public final String desc;

    public static DeptUpdateTypeEnum enumOf(Byte value) {
        for(DeptUpdateTypeEnum e: DeptUpdateTypeEnum.values()) {
            if(e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
