package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Restaurant;
import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.dao.mapper.RestaurantMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserAccountMapperExt;
import com.myicellar.digitalmenu.enums.DeviceTypeEnum;
import com.myicellar.digitalmenu.enums.PassWordStatusEnum;
import com.myicellar.digitalmenu.enums.UserTypeEnum;
import com.myicellar.digitalmenu.shiro.ManageUserNamePasswordToken;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.RestaurantListReqVO;
import com.myicellar.digitalmenu.vo.request.RestaurantReqVO;
import com.myicellar.digitalmenu.vo.response.LoginRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RestaurantService extends BaseService<Long, Restaurant, RestaurantMapperExt> {

    @Transactional
    public Restaurant selectByPrimaryKey(Long restaurantId){
        return mapper.selectByPrimaryKey(restaurantId);
    }

    /**
     * 查询餐厅列表-下拉框选项
     * @return
     */
    @Transactional
    public List<Restaurant> queryList(){
        RestaurantListReqVO reqVO = new RestaurantListReqVO();
        return mapper.queryListByParam(reqVO);
    }

    /**
     * 查询餐厅列表(分页，支持模糊匹配)
     * @return
     */
    @Transactional
    public PageResponseVO<Restaurant> queryRestaurantListPage(RestaurantListReqVO reqVO){
        PageResponseVO<Restaurant> page = selectPage(reqVO, mapper::queryCountByParam, mapper::queryListByParam);
        return page;
    }

    public Restaurant selectByRestaurantNames(RestaurantReqVO reqVO){
        return mapper.selectByRestaurantNames(reqVO);
    }
}