package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.dao.mapper.WineMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.WineDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.request.WineReqVO;
import com.myicellar.digitalmenu.vo.request.WineUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineService extends BaseService<Long, Wine, WineMapperExt> {

    @Autowired
    private WineTypeService wineTypeService;
    @Autowired
    private WineryService wineryService;
    @Autowired
    private WineService wineService;
    @Autowired
    private ImgService imgService;
    @Autowired
    private OriginService originService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private WineVintageService wineVintageService;
    @Autowired
    private WineVintageAttrService wineVintageAttrService;
    @Autowired
    private WineVintageScoreService wineVintageScoreService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductManageService productManageService;

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(WineReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getWineNameEng())) {
            throw new BizException("wineNameEng can not be empty!");
        }
        if (reqVO.getWineTypeId() == null || reqVO.getWineTypeId() == 0L) {
            throw new BizException("wineTypeId can not be empty!");
        }
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("wineryId can not be empty!");
        }
        if (reqVO.getWineOriginId() == null || reqVO.getWineOriginId() == 0L) {
            throw new BizException("originId can not be empty!");
        }
        Wine wine = queryByName(reqVO.getWineNameEng());
        if (wine != null) {
            throw new BizException("wineNameEng is already exists!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(WineUpdateReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            throw new BizException("wineId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getWineNameEng())) {
            throw new BizException("wineNameEng can not be empty!");
        }
        if (reqVO.getWineTypeId() == null || reqVO.getWineTypeId() == 0L) {
            throw new BizException("wineTypeId can not be empty!");
        }
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("wineryId can not be empty!");
        }
        if (reqVO.getWineOriginId() == null || reqVO.getWineOriginId() == 0L) {
            throw new BizException("originId can not be empty!");
        }
        Wine wine = queryByName(reqVO.getWineNameEng());
        if (wine != null && !wine.getWineId().equals(reqVO.getWineId())) {
            throw new BizException("wineNameEng is already exists!");
        }
    }

    public synchronized ResultVO<WineRespVO> addNew(WineReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Wine wine = ConvertUtils.convert(reqVO, Wine.class);
        String wineSeoName = wine.getWineNameEng().replaceAll(" ", "-").toLowerCase();
        wine.setWineSeoName(wineSeoName);
        wine.setWineId(snowflakeIdWorker.nextId());
        wine.setUpdatedAt(new Date());
        int i = wineService.insertSelective(wine);
        if (i == 0) {
            return ResultVO.validError("save is failed!");
        }

        WineRespVO respVO = ConvertUtils.convert(wine, WineRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    public ResultVO<WineRespVO> update(WineUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Wine wine = ConvertUtils.convert(reqVO, Wine.class);
        String wineSeoName = wine.getWineNameEng().replaceAll(" ", "-").toLowerCase();
        wine.setWineSeoName(wineSeoName);
        wine.setUpdatedAt(new Date());
        int i = wineService.updateByPrimaryKeySelective(wine);
        if (i == 0) {
            return ResultVO.validError("update is failed!");
        }

        WineRespVO respVO = ConvertUtils.convert(wine, WineRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    public ResultVO delete(WineDeleteReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            return ResultVO.validError("parameter is invalid！");
        }
        Product product = productManageService.queryByWineId(reqVO.getWineId());
        if (product != null) {
            return ResultVO.validError("Wine is in use, can not be deleted");
        }

        int i = mapper.deleteByPrimaryKey(reqVO.getWineId());
        if (i == 0) {
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<WineRespVO> queryPageList(WinePageReqVO reqVO) {
        PageResponseVO<WineRespVO> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            List<WineRespVO> list = page.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for (WineRespVO respVO : list) {
                imgIds.add(respVO.getWineImgId());
            }

            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if (!CollectionUtils.isEmpty(imgMap)) {
                page.getItems().forEach(respVO -> {
                    Img img = imgMap.get(respVO.getWineImgId());
                    if (img != null) {
                        respVO.setWineImgUrl(img.getImgUrl());
                    }
                });
            }
        }

        return page;
    }

    public WineRespVO queryByWineId(Long wineId) {
        WineRespVO respVO = mapper.selectByWineId(wineId);
        if (respVO != null) {
            if (respVO.getWineImgId() != null) {
                Img img = imgService.selectByPrimaryKey(respVO.getWineImgId());
                if (img != null) {
                    respVO.setWineImgUrl(img.getImgUrl());
                }
            }
        }

        return respVO;
    }

    /**
     * 按酒庄ID查询
     *
     * @return
     */
    public Wine queryByWineryId(Long wineryId) {
        return mapper.selectByWineryId(wineryId);
    }

    public Wine queryByName(String wineNameEng) {
        return mapper.selectByName(wineNameEng);
    }
}