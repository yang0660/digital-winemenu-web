package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Attr;
import com.myicellar.digitalmenu.service.AttrService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.AttrDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.AttrReqVO;
import com.myicellar.digitalmenu.vo.request.AttrUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.AttrRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/manage/attr")
@Api(tags = "配料", description = "/manage/attr")
public class AttrManageController {

    @Autowired
    private AttrService attrService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
/*    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<AttrRespVO>> queryListPage(@RequestBody AttrPageReqVO reqVO) {
        PageResponseVO<Attr> page = attrService.queryPageList(reqVO);

        PageResponseVO<AttrRespVO> resultPage = new PageResponseVO<AttrRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,AttrRespVO.class);
        }

        return ResultVO.success(resultPage);
    }*/




    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<AttrRespVO> add(@RequestBody AttrReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Attr attr = ConvertUtils.convert(reqVO,Attr.class);
        attr.setAttrId(snowflakeIdWorker.nextId());

        Date now = new Date();

        attr.setUpdatedAt(now);
        int i = attrService.insertSelective(attr);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        AttrRespVO respVO = ConvertUtils.convert(attr,AttrRespVO.class);
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
    public ResultVO update(@RequestBody AttrUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Attr attr = ConvertUtils.convert(reqVO,Attr.class);
        Date now = new Date();
        attr.setUpdatedAt(now);
        int i = attrService.updateByPrimaryKeySelective(attr);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        AttrRespVO respVO = ConvertUtils.convert(attr,AttrRespVO.class);
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
    public ResultVO update(@RequestBody AttrDeleteReqVO reqVO) {
        if(reqVO.getAttrId()==null || reqVO.getAttrId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = attrService.deleteByPrimaryKey(reqVO.getAttrId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(AttrReqVO reqVO){
        if(reqVO.getAttrId()==null || reqVO.getAttrId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(AttrUpdateReqVO reqVO){
        if(reqVO.getAttrId()==null || reqVO.getAttrId()==0L){
            throw new BizException("attrId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getAttrNameEng())){
            throw new BizException("attrNameEng cannot be empty!");
        }
    }

}
