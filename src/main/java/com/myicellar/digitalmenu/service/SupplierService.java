package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.dao.mapper.SupplierMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierStatusReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private FoodTypeService foodTypeService;

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(SupplierReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getSupplierNameEng())) {
            throw new BizException("SupplierNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0L) {
            throw new BizException("Logo cannot be empty!");
        }
        if (reqVO.getType() == null) {
            throw new BizException("Type cannot be empty!");
        }
        if (queryBySupplierName(reqVO.getSupplierNameEng()) != null) {
            throw new BizException("Supplier already exist!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(SupplierReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("SupplierId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getSupplierNameEng())) {
            throw new BizException("SupplierNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0L) {
            throw new BizException("Logo cannot be empty!");
        }
        if (reqVO.getType() == null) {
            throw new BizException("Type cannot be empty!");
        }
        Supplier nameSupplier = queryBySupplierName(reqVO.getSupplierNameEng());
        if (nameSupplier!=null && !nameSupplier.getSupplierId().equals(reqVO.getSupplierId())) {
            throw new BizException("Supplier already exist!");
        }
    }

    /**
     * 校验删除参数
     *
     * @param reqVO
     */
    private void checkDeleteParam(SupplierDeleteReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId cannot be empty!");
        }
        List<Product> products = productService.queryListBySupplierId(reqVO.getSupplierId());
        List<FoodType> foodTypes = foodTypeService.queryListBysupplierId(reqVO.getSupplierId());
        if (!products.isEmpty() || !foodTypes.isEmpty()) {
            throw new BizException("Supplier is in use, can not be deleted!");
        }
    }

    @Transactional
    public synchronized ResultVO<Integer> addNew(SupplierReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO, Supplier.class);
        supplier.setSupplierId(snowflakeIdWorker.nextId());
        supplier.setCreatedBy(0L);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setCreatedAt(now);
        supplier.setUpdatedAt(now);
        Integer result = mapper.insertSelective(supplier);
        if (result == 0) {
            return ResultVO.validError("save is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO<Integer> update(SupplierReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO, Supplier.class);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setUpdatedAt(now);
        Integer result = mapper.updateByPrimaryKeySelective(supplier);
        if (result == 0) {
            return ResultVO.validError("update is failed!");
        }
        return ResultVO.success(result);

    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO delete(SupplierDeleteReqVO reqVO) {
        checkDeleteParam(reqVO);
        Integer result = mapper.deleteByPrimaryKey(reqVO.getSupplierId());
        if (result == 0) {
            return ResultVO.validError("delete is failed!");
        }
        return ResultVO.success("delete is success!");
    }

    /**
     * 状态切换
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO updateStatus(SupplierStatusReqVO reqVO) {
        if (reqVO.getSupplierId()==null) {
            return ResultVO.validError("SupplierId cannot be empty!");
        }
        Supplier supplier = new Supplier();
        supplier.setSupplierId(reqVO.getSupplierId());
        supplier.setIsEnabled(reqVO.getIsEnabled());
        supplier.setUpdatedBy(1L);
        supplier.setUpdatedAt(new Date());
        Integer i = mapper.updateByPrimaryKeySelective(supplier);
        if (i == 0) {
            return ResultVO.validError("update is success!");
        }
        return ResultVO.success("update is failed!");
    }

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
}