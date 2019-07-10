package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.AttrService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/manage/attr")
@Api(tags = "配料", description = "/manage/attr")
public class AttrManageController {

    @Autowired
    private AttrService attrService;

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

}
