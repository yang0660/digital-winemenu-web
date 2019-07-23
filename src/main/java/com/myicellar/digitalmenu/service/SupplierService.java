package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SupplierService extends BaseService<Long, Supplier, SupplierMapperExt> {

    @Autowired
    private ImgService imgService;

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
            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)){
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

    public Supplier queryBySupplierName(String supplierName) {
        return mapper.selectBySupplierName(supplierName);
    }
}