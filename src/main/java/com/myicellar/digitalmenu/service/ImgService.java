package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.configuration.properties.FileUploadProperties;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.file.FileUploadHandler;
import com.myicellar.digitalmenu.vo.request.ImgBatchDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgReqVO;
import com.myicellar.digitalmenu.vo.response.ImgRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ImgService extends BaseService<Long, Img, ImgMapperExt> {

    @Autowired
    private FileUploadHandler fileUploadHandler;

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
        Img img = queryByTypeIdAndImgName(reqVO.getImgTypeId(), reqVO.getImgNameEng());
        if (img != null) {
            throw new BizException("imgNameEng is already exists!");
        }
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public synchronized ResultVO<ImgRespVO> addNew(ImgReqVO reqVO) {
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
        int i = mapper.insertSelective(img);
        if (i == 0) {
            return ResultVO.validError("save is failed!");
        }

        return ResultVO.success(ConvertUtils.convert(img, ImgRespVO.class), "save is success!");
    }

    /**
     * 批量删除
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO batchDelete(ImgBatchDeleteReqVO reqVO) {
        if (CollectionUtils.isEmpty(reqVO.getImgIds())) {
            return ResultVO.validError("parameter is invalid！");
        }
        int i = mapper.deleteByIds(reqVO.getImgIds());
        if (i == 0) {
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
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

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<Img> queryPageList(ImgPageReqVO reqVO) {
        PageResponseVO<Img> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }

    /**
     * 图片数量查询
     *
     * @return
     */
    public Long queryCount(ImgPageReqVO reqVO) {
        return mapper.selectCount(reqVO);
    }

    public Img queryByTypeIdAndImgName(Long imgTypeId, String imgNameEng) {
        return mapper.selectByTypeIdAndImgName(imgTypeId, imgNameEng);
    }

    public Map<Long, Img> queryImgMapByIds(List<Long> imgIds) {
        Map<Long, Img> resultMap = new HashMap<Long, Img>();
        if (!CollectionUtils.isEmpty(imgIds)) {
            resultMap = mapper.selectImgMapByIds(imgIds);
        }

        return resultMap;
    }

    public Integer deleteByIds(List<Long> imgIds) {
        return mapper.deleteByIds(imgIds);
    }
}