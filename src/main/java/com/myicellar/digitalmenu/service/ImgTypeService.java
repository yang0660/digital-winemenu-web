package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.dao.mapper.ImgTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ImgTypeService extends BaseService<Long, ImgType, ImgTypeMapperExt> {

    /**
     * 列表查询
     *
     * @return
     */
    public List<ImgType> queryListAll() {
        return mapper.selectListAll();
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<ImgType> queryPageList(ImgTypePageReqVO reqVO) {
        PageResponseVO<ImgType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }

    public ImgType queryByName(String imgTypeNameEng) {
        return mapper.selectByName(imgTypeNameEng);
    }
}