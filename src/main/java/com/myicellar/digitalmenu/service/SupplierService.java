package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
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
public class SupplierService extends BaseService<Long, Supplier, SupplierMapperExt> {

    @Autowired
    private ImgMapperExt imgMapperExt;

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<SupplierRespVO> queryPageList(SupplierPageReqVO reqVO) {
        PageResponseVO<Supplier> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        PageResponseVO<SupplierRespVO> resultPage = new PageResponseVO<SupplierRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,SupplierRespVO.class);
            List<SupplierRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(SupplierRespVO respVO : list){
                if(respVO.getLogoImgId()!=null && respVO.getLogoImgId()!=0L) {
                    imgIds.add(respVO.getLogoImgId());
                }
            }
            if (imgIds.size() > 0) {
                Map<Long, Img> imgMap = imgMapperExt.selectImgMapByIds(imgIds);
                list.forEach(info -> {
                    if(info.getLogoImgId()!=null && info.getLogoImgId()!=0L){
                        Img logoImg = imgMap.get(info.getLogoImgId());
                        if(logoImg!=null) {
                            info.setLogoImgUrl(logoImg.getImgUrl());
                        }
                    }
                });
            }


            resultPage.setItems(list);
        }
        return resultPage;
    }

    /**
     * 列表查询-下拉框
     *
     * @return
     */
    public List<Supplier> queryListAll(){
        return mapper.selectListAll();
    }
}