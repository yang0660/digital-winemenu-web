package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Origin;
import com.myicellar.digitalmenu.dao.mapper.OriginMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OriginService extends BaseService<Long, Origin, OriginMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<Origin> queryList(){
        List<Origin> list=mapper.selectList();
        return list;
    }


    /**
     * 查询产地列表（根据供应商ID）
     * @return
     */
    public List<Origin> queryListBySupplierId(Long supplierId){
        List<Origin> list = mapper.selectListBySupplierId(supplierId);
        return list;
    }

    /**
     * 查询产地列表（根据供应商及国家ID）
     * @return
     */
    public List<Origin> queryListBySupplierIdAndCountryId(Long supplierId,Long countryId){
        List<Origin> list = mapper.selectListBySupplierIdAndCountryId(supplierId,countryId);
        return list;
    }


}