package com.myicellar.digitalmenu.enums;

import lombok.AllArgsConstructor;

/**
 * 图库分类内置类型枚举
 */
@AllArgsConstructor
public enum ImgTypeEnum {
    FOOD_IMG((long) 1, "美食图片"),
    WINERY_LOGO((long) 2, "酒庄Logo"),
    WINERY_BANNER((long) 3, "酒庄banner"),
    WINERY_IMG((long) 4, "酒庄图片"),
    WINE_IMG((long) 5, "酒品图片"),
    SUPPLIER_LOGO((long) 6, "供应商Logo");

    public final Long value;
    public final String desc;

    public static ImgTypeEnum enumOf(Long value) {
        for (ImgTypeEnum e : ImgTypeEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
