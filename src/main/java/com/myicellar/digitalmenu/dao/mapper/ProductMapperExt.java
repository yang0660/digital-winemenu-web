package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Product;

import java.util.List;

public interface ProductMapperExt extends ProductMapper{
    List<Product> selectList();
}