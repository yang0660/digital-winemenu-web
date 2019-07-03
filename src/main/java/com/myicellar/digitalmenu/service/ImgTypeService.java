package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.dao.mapper.ImgTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImgTypeService extends BaseService<Long, ImgType, ImgTypeMapperExt> {

    /**
     * 查询用户列表(分页，支持模糊匹配)
     * @return
     */
    public PageResponseVO<ImgType> queryPageList(ImgTypePageReqVO reqVO){
        PageResponseVO<ImgType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }
}