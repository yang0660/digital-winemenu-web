package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.service.SupplierService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.FoodTypeRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/manage/supplier")
@Api(tags = "供应商", description = "/manage/supplier")
public class SupplierManageController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<SupplierRespVO>> queryListPage(@RequestBody SupplierPageReqVO reqVO) {
        PageResponseVO<Supplier> page = supplierService.queryPageList(reqVO);

        PageResponseVO<SupplierRespVO> resultPage = new PageResponseVO<SupplierRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,SupplierRespVO.class);
        }

        return ResultVO.success(resultPage);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<SupplierRespVO> add(@RequestBody SupplierReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO,Supplier.class);
        supplier.setSupplierId(snowflakeIdWorker.nextId());
        supplier.setCreatedBy(0L);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setCreatedAt(now);
        supplier.setUpdatedAt(now);
        int i = supplierService.insertSelective(supplier);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        SupplierRespVO respVO = ConvertUtils.convert(supplier,SupplierRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody SupplierReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Supplier supplier = ConvertUtils.convert(reqVO,Supplier.class);
        supplier.setUpdatedBy(0L);
        Date now = new Date();
        supplier.setUpdatedAt(now);
        int i = supplierService.updateByPrimaryKeySelective(supplier);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        SupplierRespVO respVO = ConvertUtils.convert(supplier,SupplierRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody SupplierDeleteReqVO reqVO) {
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = supplierService.deleteByPrimaryKey(reqVO.getSupplierId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(SupplierReqVO reqVO){
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getSupplierNameEng())){
            throw new BizException("SupplierNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(SupplierReqVO reqVO){
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("SupplierId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getSupplierNameEng())){
            throw new BizException("SupplierNameEng cannot be empty!");
        }
    }

}
