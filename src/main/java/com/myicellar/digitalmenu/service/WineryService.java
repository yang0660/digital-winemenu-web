package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.dao.mapper.WineryMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.WineryRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineryService extends BaseService<Long, Winery, WineryMapperExt> {

    @Autowired
    private ImgMapperExt imgMapperExt;

    public List<Winery> queryListAll(){
        return mapper.selectListAll();
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<WineryRespVO> queryPageList(WineryPageReqVO reqVO) {
        PageResponseVO<Winery> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        PageResponseVO<WineryRespVO> resultPage = new PageResponseVO<WineryRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineryRespVO.class);
            List<WineryRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(WineryRespVO respVO : list){
                if(respVO.getLogoImgId()!=null && respVO.getLogoImgId()!=0L) {
                    imgIds.add(respVO.getLogoImgId());
                }
            }
            Map<Long, Img> imgMap = imgMapperExt.selectImgMapByIds(imgIds);
            list.forEach(info -> {
                if(info.getLogoImgId()!=null && info.getLogoImgId()!=0L){
                    Img logoImg = imgMap.get(info.getLogoImgId());
                    if(logoImg!=null) {
                        info.setWineryLogoUrl(logoImg.getImgUrl());
                        info.setWineryLogoSmallUrl(logoImg.getSmallImgUrl());
                    }
                }
            });

            resultPage.setItems(list);
        }

        return resultPage;
    }


    /**
     * 酒庄查询-按酒庄名称
     *
     * @return
     */
    public  Winery queryByName(String wineryNameEng) {
        return mapper.selectByName(wineryNameEng);
    }

    public Map<String,Winery> queryNameMap(){
        return mapper.selectNameMap();
    }
}