package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.WineVintageAttr;
import com.myicellar.digitalmenu.dao.mapper.WineVintageAttrMapperExt;
import com.myicellar.digitalmenu.vo.response.WineAttrInfoRespVO;
import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineVintageAttrService extends BaseService<Long, WineVintageAttr, WineVintageAttrMapperExt> {

    public Map<String,WineAttrMapRespVO> queryAttrMapByWineVintageIds(Long attrCtgId,List<String> wineVintageIds){
        Map<String,WineAttrMapRespVO> resultMap = new HashMap<String,WineAttrMapRespVO>();
        if(!CollectionUtils.isEmpty(wineVintageIds)){
            resultMap = mapper.selectAttrMapByWineVintageIds(attrCtgId,wineVintageIds);
        }

        return resultMap;
    }

    public String listToEngStr(List<WineAttrInfoRespVO> list){
        StringBuffer subStr = new StringBuffer("");
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(WineAttrInfoRespVO info : list){
                if(!StringUtils.isEmpty(info.getAttrNameEng())) {
                    if (i != 0) {
                        subStr.append(", ");
                    }
                    subStr.append(info.getAttrNameEng());
                    i++;
                }
            }
        }
        return subStr.toString();
    }

    public String listToChsStr(List<WineAttrInfoRespVO> list){
        StringBuffer subStr = new StringBuffer("");
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(WineAttrInfoRespVO info : list){
                if(!StringUtils.isEmpty(info.getAttrNameChs())) {
                    if (i != 0) {
                        subStr.append(", ");
                    }
                    subStr.append(info.getAttrNameChs());
                    i++;
                }
            }
        }
        return subStr.toString();
    }

    public String listToChtStr(List<WineAttrInfoRespVO> list){
        StringBuffer subStr = new StringBuffer("");
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(WineAttrInfoRespVO info : list){
                if(!StringUtils.isEmpty(info.getAttrNameCht())) {
                    if (i != 0) {
                        subStr.append(", ");
                    }
                    subStr.append(info.getAttrNameCht());
                    i++;
                }
            }
        }
        return subStr.toString();
    }

    public Integer deleteByWineIdAndVintage(Long wineId, Long vintageTag){
        return mapper.deleteByWineIdAndVintage(wineId, vintageTag);
    }
}