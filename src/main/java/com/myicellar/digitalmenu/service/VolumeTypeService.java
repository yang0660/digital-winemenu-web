package com.myicellar.digitalmenu.service;


import com.myicellar.digitalmenu.dao.entity.VolumeType;
import com.myicellar.digitalmenu.dao.mapper.VolumeTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.VolumeTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VolumeTypeService extends BaseService<Long, VolumeType, VolumeTypeMapperExt> {


    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<VolumeType> queryPageList(VolumeTypePageReqVO reqVO){
        PageResponseVO<VolumeType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }

}