package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.dao.mapper.ImgTypeMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgTypeReqVO;
import com.myicellar.digitalmenu.vo.response.ImgTypeRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ImgTypeService extends BaseService<Long, ImgType, ImgTypeMapperExt> {

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    public void checkNewParam(ImgTypeReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getImgTypeNameEng())) {
            throw new BizException("imgTypeNameEng cannot be empty!");
        }
        ImgType imgType = queryByName(reqVO.getImgTypeNameEng());
        if (imgType != null) {
            throw new BizException("imgTypeNameEng is already exists!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    public void checkUpdateParam(ImgTypeReqVO reqVO) {
        if (reqVO.getImgTypeId() == null || reqVO.getImgTypeId() == 0L) {
            throw new BizException("imgTypeId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getImgTypeNameEng())) {
            throw new BizException("imgTypeNameEng cannot be empty!");
        }
        ImgType imgType = queryByName(reqVO.getImgTypeNameEng());
        if (imgType != null && !imgType.getImgTypeId().equals(reqVO.getImgTypeId())) {
            throw new BizException("imgTypeNameEng is already exists!");
        }
    }

    public synchronized ResultVO<ImgTypeRespVO> addNew(ImgTypeReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        ImgType imgType = ConvertUtils.convert(reqVO, ImgType.class);
        imgType.setImgTypeId(snowflakeIdWorker.nextId());
        imgType.setCreatedUser(0L);
        imgType.setUpdatedUser(0L);
        Date now = new Date();
        imgType.setCreatedAt(now);
        imgType.setUpdatedAt(now);
        int i = mapper.insertSelective(imgType);
        if (i == 0) {
            return ResultVO.validError("save is failed!");
        }

        ImgTypeRespVO respVO = ConvertUtils.convert(imgType, ImgTypeRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    public ResultVO<ImgTypeRespVO> update(ImgTypeReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        ImgType imgType = ConvertUtils.convert(reqVO, ImgType.class);
        imgType.setUpdatedUser(0L);
        Date now = new Date();
        imgType.setUpdatedAt(now);
        int i = mapper.updateByPrimaryKeySelective(imgType);
        if (i == 0) {
            return ResultVO.validError("update is failed!");
        }

        ImgTypeRespVO respVO = ConvertUtils.convert(imgType, ImgTypeRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 列表查询
     *
     * @return
     */
    public List<ImgType> queryListAll() {
        return mapper.selectListAll();
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<ImgType> queryPageList(ImgTypePageReqVO reqVO) {
        PageResponseVO<ImgType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }

    public ImgType queryByName(String imgTypeNameEng) {
        return mapper.selectByName(imgTypeNameEng);
    }
}