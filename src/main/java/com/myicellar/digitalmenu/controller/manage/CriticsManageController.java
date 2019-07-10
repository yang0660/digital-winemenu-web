package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Critics;
import com.myicellar.digitalmenu.service.CriticsService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.response.CriticsRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/critics")
@Api(tags = "评价", description = "/manage/critics")
public class CriticsManageController {

    @Autowired
    private CriticsService criticsService;
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
    public ResultVO<List<CriticsRespVO>> queryList() {
        List<Critics> page = criticsService.queryList();

        PageResponseVO<CriticsRespVO> resultPage = new PageResponseVO<CriticsRespVO>();
        if(page!=null && !page.isEmpty()){
            ResultVO.success(resultPage);
        }

        return ResultVO.validError("query is failed!");
    }

}
