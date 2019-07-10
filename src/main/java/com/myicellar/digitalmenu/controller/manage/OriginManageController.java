package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Origin;
import com.myicellar.digitalmenu.service.OriginService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.response.OriginRespVO;
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
@RequestMapping("/manage/origin")
@Api(tags = "产地", description = "/manage/origin")
public class OriginManageController {

    @Autowired
    private OriginService originService;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<List<OriginRespVO>> queryList() {
        List<Origin> page = originService.queryList();

        PageResponseVO<OriginRespVO> resultPage = new PageResponseVO<OriginRespVO>();
        if(page!=null && page.isEmpty()){
            ResultVO.success(resultPage);
        }

        return ResultVO.validError("query is failed!");
    }

}
