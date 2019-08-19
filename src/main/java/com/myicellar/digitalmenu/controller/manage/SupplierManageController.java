package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.service.SupplierService;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.QrcodeRespVO;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/supplier")
@Api(tags = "供应商管理", description = "/manage/supplier")
public class SupplierManageController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 供应商下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryList")
    @ApiOperation("供应商下拉列表")
    public ResultVO<List<SupplierRespVO>> queryListPage() {
        List<Supplier> list = supplierService.queryListAll();

        List<SupplierRespVO> resultList = new ArrayList<SupplierRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, SupplierRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 列表查询-分页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询-分页")
    public ResultVO<PageResponseVO<SupplierRespVO>> queryListPage(@RequestBody SupplierPageReqVO reqVO) {
        PageResponseVO<SupplierRespVO> page = supplierService.queryPageList(reqVO);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            return ResultVO.success(page);
        }
        return ResultVO.success(new PageResponseVO<SupplierRespVO>());
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryBySupplierId")
    @ApiOperation("详情查询")
    public ResultVO<SupplierRespVO> queryByWineryId(@RequestBody SupplierIdReqVO reqVO) {

        SupplierRespVO respVO = supplierService.queryBySupplierId(reqVO.getSupplierId());
        if (respVO == null) {
            respVO = new SupplierRespVO();
        }
        return ResultVO.success(respVO);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResultVO<Integer> add(@RequestBody SupplierReqVO reqVO) {
        return supplierService.addNew(reqVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO<Integer> update(@RequestBody SupplierReqVO reqVO) {
        return supplierService.update(reqVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO delete(@RequestBody SupplierDeleteReqVO reqVO) {
        return supplierService.delete(reqVO);
    }

    /**
     * 状态切换
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/updateStatus")
    @ApiOperation("状态切换")
    public ResultVO updateStatus(@RequestBody SupplierStatusReqVO reqVO) {
        return supplierService.updateStatus(reqVO);
    }


    /**
     * 查询供应商主页二维码
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryQrCode")
    @ApiOperation("查询供应商主页二维码")
    public ResultVO<QrcodeRespVO> queryQrCode(@RequestBody SupplierIdReqVO reqVO) {
        return supplierService.queryQrCode(reqVO);
    }
}
