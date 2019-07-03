package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Product;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);
}