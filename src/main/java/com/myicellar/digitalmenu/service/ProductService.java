package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.ProductMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService extends BaseService<Long, Product, ProductMapperExt> {

    public Product selectByWineIdAndVintage(Long wineId, Long vintageTag){
        return mapper.selectByWineIdAndVintage(wineId, vintageTag);
    }
}