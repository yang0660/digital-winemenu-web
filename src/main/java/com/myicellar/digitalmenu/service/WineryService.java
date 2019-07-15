package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.dao.mapper.WineryMapperExt;
import com.myicellar.digitalmenu.vo.request.WineryNameReqVO;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 详情查询-按酒庄名称
     *
     * @return
     */
    public  PageResponseVO<Winery> queryByName(WineryNameReqVO reqVO) {
        //// TODO: 2019/7/15 查询不出结果
        PageResponseVO<Winery> page = selectPage(reqVO, mapper::selectByNameCount, mapper::selectByName);
        return page;
    }
}