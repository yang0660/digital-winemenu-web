package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import com.myicellar.digitalmenu.dao.mapper.WineVintageMapperExt;
import com.myicellar.digitalmenu.vo.request.WineDetailReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.WineAttrInfoRespVO;
import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
import com.myicellar.digitalmenu.vo.response.WineVintageListRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineVintageService extends BaseService<Long, WineVintage, WineVintageMapperExt> {

    @Autowired
    public WineVintageAttrService wineVintageAttrService;

    public PageResponseVO<WineVintageListRespVO> queryPageList(WineDetailReqVO reqVO){
        PageResponseVO<WineVintageListRespVO> page = selectPage(reqVO,mapper:: selectCount, mapper:: selectList);

        List<String> wineVintageIds = new ArrayList<String>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            List<WineVintageListRespVO> list = page.getItems();
            for(WineVintageListRespVO respVO : list) {
                wineVintageIds.add(respVO.getWineId() + "|" + respVO.getVintageTag());
            }
            //风格
            Map<String,WineAttrMapRespVO> styleAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1001L,wineVintageIds);
            //口味
            Map<String,WineAttrMapRespVO> descriptorAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1002L,wineVintageIds);
            //葡萄
            Map<String,WineAttrMapRespVO> grapAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(101L,wineVintageIds);
            list.forEach(respVO ->{
                //酒精度%
                if(respVO.getAlcoholBps()!=null){
                    respVO.setAcohol(new BigDecimal(respVO.getAlcoholBps()).divide(new BigDecimal(100)).setScale(2));
                }
                //风格
                if(!CollectionUtils.isEmpty(styleAttrMap)){
                    WineAttrMapRespVO attrRespVO = styleAttrMap.get(respVO.getWineId()+"|"+respVO.getVintageTag());
                    if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setStyle(wineVintageAttrService.listToEngStr(attrlist));
                    }
                }
                //口味
                if(!CollectionUtils.isEmpty(descriptorAttrMap)){
                    WineAttrMapRespVO attrRespVO = descriptorAttrMap.get(respVO.getWineId()+"|"+respVO.getVintageTag());
                    if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setDescriptor(wineVintageAttrService.listToEngStr(attrlist));
                    }
                }
                //葡萄
                if(!CollectionUtils.isEmpty(grapAttrMap)){
                    WineAttrMapRespVO attrRespVO = grapAttrMap.get(respVO.getWineId()+"|"+respVO.getVintageTag());
                    if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setGrap(wineVintageAttrService.listToEngStr(attrlist));
                    }
                }
            });
        }
        return page;
    }


}