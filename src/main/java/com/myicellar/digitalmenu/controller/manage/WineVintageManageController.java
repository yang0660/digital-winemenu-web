package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.WineVintageService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineDetailReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineVintageListRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/manage/winevtg")
@Api(tags = "酒品管理-年份管理", description = "/manage/winevtg")
public class WineVintageManageController {

    @Autowired
    private WineVintageService wineVintageService;
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
    public ResultVO<PageResponseVO<WineVintageListRespVO>> queryListPage(@RequestBody WineDetailReqVO reqVO) {
        PageResponseVO<WineVintageListRespVO> page = wineVintageService.queryPageList(reqVO);

        return ResultVO.success(page);
    }

}
