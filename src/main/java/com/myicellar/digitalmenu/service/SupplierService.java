package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SupplierService extends BaseService<Long, Supplier, SupplierMapperExt> {

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<Supplier> queryPageList(SupplierPageReqVO reqVO) {
        PageResponseVO<Supplier> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }
}