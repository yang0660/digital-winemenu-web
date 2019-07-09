package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.ProductMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService extends BaseService<Long, Product, ProductMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<Product> queryList(){
        List<Product> list=mapper.selectList();
        return list;
    }

}