package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.dao.mapper.WineryMapperExt;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WineryService extends BaseService<Long, Winery, WineryMapperExt> {

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<Winery> queryPageList(WineryPageReqVO reqVO) {
        PageResponseVO<Winery> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }
}