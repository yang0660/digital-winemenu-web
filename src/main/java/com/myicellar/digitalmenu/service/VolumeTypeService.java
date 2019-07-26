package com.myicellar.digitalmenu.service;


import com.myicellar.digitalmenu.dao.entity.VolumeType;
import com.myicellar.digitalmenu.dao.mapper.VolumeTypeMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VolumeTypeService extends BaseService<Long, VolumeType, VolumeTypeMapperExt> {

    public List<VolumeType> queryVolumeList(){
        return mapper.selectListAll();
    }
}