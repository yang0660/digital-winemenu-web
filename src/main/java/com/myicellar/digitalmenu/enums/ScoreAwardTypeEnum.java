package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ScoreAwardTypeEnum {
    SCORE((byte) 1, "评分"), AWARD((byte) 2, "获奖");

    public final Byte value;
    public final String desc;

    public static ScoreAwardTypeEnum enumOf(Byte value) {
        for (ScoreAwardTypeEnum e : ScoreAwardTypeEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
