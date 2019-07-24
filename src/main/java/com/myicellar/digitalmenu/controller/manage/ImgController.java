package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.configuration.properties.FileUploadProperties;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.service.ImgService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.utils.file.FileUploadHandler;
import com.myicellar.digitalmenu.vo.request.ImgBatchDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ImgDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgReqVO;
import com.myicellar.digitalmenu.vo.response.ImgRespVO;
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
@RequestMapping("/manage/img")
@Api(tags = "图库管理-图片管理", description = "/manage/img")
public class ImgController {

    @Autowired
    private ImgService imgService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private FileUploadHandler fileUploadHandler;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<ImgRespVO>> queryListPage(@RequestBody ImgPageReqVO reqVO) {
        PageResponseVO<Img> page = imgService.queryPageList(reqVO);

        PageResponseVO<ImgRespVO> resultPage = new PageResponseVO<ImgRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, ImgRespVO.class);
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
    public ResultVO<ImgRespVO> add(@RequestBody ImgReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);

        FileUploadProperties.FileUploadResult fileUploadResult = fileUpload(reqVO);
        if (fileUploadResult == null) {
            return ResultVO.validError("uploading picture is failed!");
        }

        Img img = ConvertUtils.convert(reqVO, Img.class);
        img.setImgId(snowflakeIdWorker.nextId());
        img.setImgUrl(fileUploadResult.getImageUrl());
        img.setSmallImgUrl(fileUploadResult.getSmallImageUrl());
        img.setCreatedBy(0L);
        img.setUpdatedBy(0L);
        Date now = new Date();
        img.setCreatedAt(now);
        img.setUpdatedAt(now);
        int i = imgService.insertSelective(img);
        if (i == 0) {
            return ResultVO.validError("save is failed!");
        }

        ImgRespVO respVO = ConvertUtils.convert(img, ImgRespVO.class);
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
    public ResultVO<ImgRespVO> update(@RequestBody ImgReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        FileUploadProperties.FileUploadResult fileUploadResult = fileUpload(reqVO);
        if (fileUploadResult == null) {
            return ResultVO.validError("uploading picture is failed!");
        }

        Img img = ConvertUtils.convert(reqVO, Img.class);
        img.setImgUrl(fileUploadResult.getImageUrl());
        img.setSmallImgUrl(fileUploadResult.getSmallImageUrl());
        img.setUpdatedBy(0L);
        Date now = new Date();
        img.setUpdatedAt(now);
        int i = imgService.updateByPrimaryKeySelective(img);
        if (i == 0) {
            return ResultVO.validError("update is failed!");
        }

        ImgRespVO respVO = ConvertUtils.convert(img, ImgRespVO.class);
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
    public ResultVO update(@RequestBody ImgDeleteReqVO reqVO) {
        if (reqVO.getImgId() == null || reqVO.getImgId() == 0L) {
            return ResultVO.validError("parameter is invalid！");
        }
        int i = imgService.deleteByPrimaryKey(reqVO.getImgId());
        if (i == 0) {
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/batchDelete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody ImgBatchDeleteReqVO reqVO) {
        if (!CollectionUtils.isEmpty(reqVO.getImgIds())) {
            return ResultVO.validError("parameter is invalid！");
        }
        int i = imgService.deleteByIds(reqVO.getImgIds());
        if (i == 0) {
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    public void checkNewParam(ImgReqVO reqVO) {
        if (reqVO.getImgTypeId() == null || reqVO.getImgTypeId() == 0L) {
            throw new BizException("imgTypeId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getImgNameEng())) {
            throw new BizException("imgNameEng cannot be empty!");
        }
        Img img = imgService.queryByTypeIdAndImgName(reqVO.getImgTypeId(), reqVO.getImgNameEng());
        if (img != null) {
            throw new BizException("imgNameEng is already exists!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    public void checkUpdateParam(ImgReqVO reqVO) {
        if (reqVO.getImgId() == null || reqVO.getImgId() == 0L) {
            throw new BizException("imgId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getImgNameEng())) {
            throw new BizException("imgNameEng cannot be empty!");
        }
        Img img = imgService.queryByTypeIdAndImgName(reqVO.getImgTypeId(), reqVO.getImgNameEng());
        if (img != null && !img.getImgId().equals(reqVO.getImgId())) {
            throw new BizException("imgNameEng is already exists!");
        }
    }

    /**
     * 图片上传
     *
     * @param reqVO
     * @return
     */
    public FileUploadProperties.FileUploadResult fileUpload(ImgReqVO reqVO) {
        FileUploadProperties.FileUploadResult fileUploadResult = null;
        try {
            String base64Str = reqVO.getBase64Str();
            int indexOf = base64Str.indexOf(";base64,");
            if (indexOf != -1) {
                indexOf += 8;
                base64Str = base64Str.substring(indexOf);
            }
            if (!StringUtils.isEmpty(reqVO.getBase64Str())) {
                fileUploadResult = fileUploadHandler.upload(base64Str, true, "jpg");
            }
        } catch (Exception e) {
            log.error("图片上传失败.", e);
        }

        return fileUploadResult;
    }

}
