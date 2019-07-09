package com.myicellar.digitalmenu.controller.manage;


import com.myicellar.digitalmenu.dao.entity.VolumeType;
import com.myicellar.digitalmenu.service.VolumeTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.VolumeTypeDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.VolumeTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.VolumeTypeReqVO;
import com.myicellar.digitalmenu.vo.request.VolumeTypeUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.VolumeTypeRespVO;
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
@RequestMapping("/manage/volumeType")
@Api(tags = "容量", description = "/manage/volumeType")
public class VolumeTypeManageController {

    @Autowired
    private VolumeTypeService volumeTypeService;
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
    public ResultVO<PageResponseVO<VolumeTypeRespVO>> queryListPage(@RequestBody VolumeTypePageReqVO reqVO) {
        PageResponseVO<VolumeType> page = volumeTypeService.queryPageList(reqVO);

        PageResponseVO<VolumeTypeRespVO> resultPage = new PageResponseVO<VolumeTypeRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,VolumeTypeRespVO.class);
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
    public ResultVO<VolumeTypeRespVO> add(@RequestBody VolumeTypeReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        VolumeType volumeType = ConvertUtils.convert(reqVO,VolumeType.class);
        volumeType.setVolumeTypeId(snowflakeIdWorker.nextId());

        Date now = new Date();

        volumeType.setUpdatedAt(now);
        int i = volumeTypeService.insertSelective(volumeType);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        VolumeTypeRespVO respVO = ConvertUtils.convert(volumeType,VolumeTypeRespVO.class);
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
    public ResultVO update(@RequestBody VolumeTypeUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        VolumeType volumeType = ConvertUtils.convert(reqVO,VolumeType.class);
        Date now = new Date();
        volumeType.setUpdatedAt(now);
        int i = volumeTypeService.updateByPrimaryKeySelective(volumeType);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        VolumeTypeRespVO respVO = ConvertUtils.convert(volumeType,VolumeTypeRespVO.class);
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
    public ResultVO update(@RequestBody VolumeTypeDeleteReqVO reqVO) {
        if(reqVO.getVolumeTypeId()==null || reqVO.getVolumeTypeId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = volumeTypeService.deleteByPrimaryKey(reqVO.getVolumeTypeId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(VolumeTypeReqVO reqVO){
        if(reqVO.getVolumeTypeId()==null || reqVO.getVolumeTypeId()==0L){
            throw new BizException("supplier cannot be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(VolumeTypeUpdateReqVO reqVO){
        if(reqVO.getVolumeTypeId()==null || reqVO.getVolumeTypeId()==0L){
            throw new BizException("volumeTypeId cannot be empty!");
        }

    }

}
