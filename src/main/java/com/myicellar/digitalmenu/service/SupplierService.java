package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierStatusReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
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
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, SupplierRespVO.class);
            List<SupplierRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for (SupplierRespVO respVO : list) {
                if (respVO.getLogoImgId() != null && respVO.getLogoImgId() != 0L) {
                    imgIds.add(respVO.getLogoImgId());
                }
            }
            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if (!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(info -> {
                    if (info.getLogoImgId() != null && info.getLogoImgId() != 0L) {
                        Img logoImg = imgMap.get(info.getLogoImgId());
                        if (logoImg != null) {
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
    public List<Supplier> queryListAll() {
        return mapper.selectListAll();
    }

    public Supplier queryBySupplierName(String supplierName) {
        return mapper.selectBySupplierName(supplierName);
    }

    /**
     * 详情查询
     *
     * @return
     */
    public SupplierRespVO queryBySupplierId(Long supplierId) {
        SupplierRespVO respVO = null;
        Supplier supplier = mapper.selectByPrimaryKey(supplierId);
        if(supplier!=null) {
            respVO = ConvertUtils.convert(supplier, SupplierRespVO.class);
            Long logoImgId = supplier.getLogoImgId();
            if (logoImgId != null && logoImgId != 0L) {
                Img img = imgService.selectByPrimaryKey(logoImgId);
                if (img!=null){
                    respVO.setLogoImgUrl(img.getImgUrl());
                }
            }
        }

        return respVO;
    }

    @Transactional
    public Integer updateStatus(SupplierStatusReqVO reqVO) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(reqVO.getSupplierId());
        supplier.setIsEnabled(reqVO.getIsEnabled());
        supplier.setUpdatedBy(1L);
        supplier.setUpdatedAt(new Date());
        return mapper.updateByPrimaryKeySelective(supplier);
    }
}