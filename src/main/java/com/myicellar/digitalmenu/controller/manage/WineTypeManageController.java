package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.WineType;
import com.myicellar.digitalmenu.service.WineTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineTypeDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WineTypeReqVO;
import com.myicellar.digitalmenu.vo.request.WineTypeUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineTypeRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/wineType")
@Api(tags = "酒品类型", description = "/manage/wineType")
public class WineTypeManageController {

    @Autowired
    private WineTypeService wineTypeService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<List<WineTypeRespVO>> queryList() {
        List<WineType> page = wineTypeService.queryList();

        PageResponseVO<WineTypeRespVO> resultPage = new PageResponseVO<WineTypeRespVO>();
        if(page!=null && page.isEmpty()){
            ResultVO.success(resultPage);
        }

        return ResultVO.validError("query is failed!");
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
    public ResultVO<WineTypeRespVO> add(@RequestBody WineTypeReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        WineType wineType = ConvertUtils.convert(reqVO,WineType.class);
        wineType.setWineTypeId(snowflakeIdWorker.nextId());

        Date now = new Date();

        wineType.setUpdatedAt(now);
        int i = wineTypeService.insertSelective(wineType);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        WineTypeRespVO respVO = ConvertUtils.convert(wineType,WineTypeRespVO.class);
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
    public ResultVO update(@RequestBody WineTypeUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        WineType wineType = ConvertUtils.convert(reqVO,WineType.class);
        Date now = new Date();
        wineType.setUpdatedAt(now);
        int i = wineTypeService.updateByPrimaryKeySelective(wineType);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        WineTypeRespVO respVO = ConvertUtils.convert(wineType,WineTypeRespVO.class);
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
    public ResultVO update(@RequestBody WineTypeDeleteReqVO reqVO) {
        if(reqVO.getWineTypeId()==null || reqVO.getWineTypeId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = wineTypeService.deleteByPrimaryKey(reqVO.getWineTypeId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(WineTypeReqVO reqVO){
        if(reqVO.getWineTypeId()==null || reqVO.getWineTypeId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getWineTypeNameEng())){
            throw new BizException("wineTypeNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(WineTypeUpdateReqVO reqVO){
        if(reqVO.getWineTypeId()==null || reqVO.getWineTypeId()==0L){
            throw new BizException("wineTypeId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getWineTypeNameEng())){
            throw new BizException("wineTypeNameEng cannot be empty!");
        }
    }

}
