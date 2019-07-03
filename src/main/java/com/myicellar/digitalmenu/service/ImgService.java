package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.dao.mapper.ImgTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImgService extends BaseService<Long, Img, ImgMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<Img> queryPageList(ImgPageReqVO reqVO){
        PageResponseVO<Img> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }
}