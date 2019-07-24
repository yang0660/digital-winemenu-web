package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 图片数量查询
     * @return
     */
    public Long queryCount(ImgPageReqVO reqVO){
        return mapper.selectCount(reqVO);
    }

    public Img queryByTypeIdAndImgName(Long imgTypeId, String imgNameEng){
        return mapper.selectByTypeIdAndImgName(imgTypeId,imgNameEng);
    }

    public Map<Long,Img> queryImgMapByIds(List<Long> imgIds){
        Map<Long,Img> resultMap = new HashMap<Long,Img>();
        if(!CollectionUtils.isEmpty(imgIds)){
            resultMap = mapper.selectImgMapByIds(imgIds);
        }

        return resultMap;
    }

    public Integer deleteByIds(List<Long> imgIds){
        return mapper.deleteByIds(imgIds);
    }
}