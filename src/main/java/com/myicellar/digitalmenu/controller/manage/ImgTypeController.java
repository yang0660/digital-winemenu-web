package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.service.ImgTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.ImgTypeDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgTypeReqVO;
import com.myicellar.digitalmenu.vo.response.ImgTypeRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
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
@RequestMapping("/manage/imgype")
@Api(tags = "图库分类", description = "/manage/imgype")
public class ImgTypeController {

    @Autowired
    private ImgTypeService imgTypeService;
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
    public ResultVO<PageResponseVO<ImgTypeRespVO>> queryListPage(@RequestBody ImgTypePageReqVO reqVO) {
        PageResponseVO<ImgType> page = imgTypeService.queryPageList(reqVO);

        PageResponseVO<ImgTypeRespVO> resultPage = new PageResponseVO<ImgTypeRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,ImgTypeRespVO.class);
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
    public ResultVO<ImgTypeRespVO> add(@RequestBody ImgTypeReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        ImgType imgType = ConvertUtils.convert(reqVO,ImgType.class);
        imgType.setImgTypeId(snowflakeIdWorker.nextId());
        imgType.setCreatedUser(0L);
        imgType.setUpdatedUser(0L);
        Date now = new Date();
        imgType.setCreatedTime(now);
        imgType.setUpdatedTime(now);
        int i = imgTypeService.insertSelective(imgType);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        ImgTypeRespVO respVO = ConvertUtils.convert(imgType,ImgTypeRespVO.class);
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
    public ResultVO<ImgTypeRespVO> update(@RequestBody ImgTypeReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        ImgType imgType = ConvertUtils.convert(reqVO,ImgType.class);
        imgType.setUpdatedUser(0L);
        Date now = new Date();
        imgType.setUpdatedTime(now);
        int i = imgTypeService.updateByPrimaryKeySelective(imgType);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        ImgTypeRespVO respVO = ConvertUtils.convert(imgType,ImgTypeRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     * @since
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody ImgTypeDeleteReqVO reqVO) {
        if(reqVO.getImgTypeId()==null || reqVO.getImgTypeId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = imgTypeService.deleteByPrimaryKey(reqVO.getImgTypeId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    public void checkNewParam(ImgTypeReqVO reqVO){
        if(StringUtils.isEmpty(reqVO.getImgTypeNameEng())){
            throw new BizException("imgTypeNameEng cannot is empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    public void checkUpdateParam(ImgTypeReqVO reqVO){
        if(reqVO.getImgTypeId()==null || reqVO.getImgTypeId()==0L){
            throw new BizException("imgTypeId cannot is empty!");
        }
        if(StringUtils.isEmpty(reqVO.getImgTypeNameEng())){
            throw new BizException("imgTypeNameEng cannot is empty!");
        }
    }

}