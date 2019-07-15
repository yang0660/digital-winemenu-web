package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.dao.mapper.WineMapperExt;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.request.WineTypeReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WineService extends BaseService<Long, Wine, WineMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<Wine> queryPageList(WinePageReqVO reqVO){
        PageResponseVO<Wine> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }

    /**
     * 下拉框查询
     * @return
     */
    public List<Wine> queryDropList(WineTypeReqVO reqVO){
        List<Wine> list = mapper.selectDropList(reqVO);

        return list;
    }

    /**
     * 按酒庄ID查询
     * @return
     */
    public Wine queryByWineryId(Long wineryId){
        return mapper.selectByWineryId(wineryId);


    }


}