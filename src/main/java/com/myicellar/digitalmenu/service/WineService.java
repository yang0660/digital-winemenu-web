package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.dao.mapper.WineMapperExt;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.WineRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineService extends BaseService<Long, Wine, WineMapperExt> {

    @Autowired
    private WineTypeService wineTypeService;
    @Autowired
    private WineryService wineryService;
    @Autowired
    private WineService wineService;
    @Autowired
    private ImgService imgService;
    @Autowired
    private OriginService originService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private WineVintageService wineVintageService;
    @Autowired
    private WineVintageAttrService wineVintageAttrService;
    @Autowired
    private WineVintageScoreService wineVintageScoreService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PackageService packageService;

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<WineRespVO> queryPageList(WinePageReqVO reqVO){
        PageResponseVO<WineRespVO> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            List<WineRespVO> list = page.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(WineRespVO respVO : list){
                imgIds.add(respVO.getWineImgId());
            }

            Map<Long,Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)) {
                page.getItems().forEach(respVO -> {
                    Img img = imgMap.get(respVO.getWineImgId());
                    if (img != null) {
                        respVO.setWineImgUrl(img.getImgUrl());
                    }
                });
            }
        }

        return page;
    }

    public WineRespVO queryByWineId(Long wineId){
        WineRespVO respVO = mapper.selectByWineId(wineId);
        if(respVO!=null){
            if(respVO.getWineImgId()!=null) {
                Img img = imgService.selectByPrimaryKey(respVO.getWineImgId());
                if (img != null) {
                    respVO.setWineImgUrl(img.getImgUrl());
                }
            }
        }

        return respVO;
    }

    /**
     * 按酒庄ID查询
     * @return
     */
    public Wine queryByWineryId(Long wineryId){
        return mapper.selectByWineryId(wineryId);
    }

    public Wine queryByName(String wineNameEng){
        return mapper.selectByName(wineNameEng);
    }
}