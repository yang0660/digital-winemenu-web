package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Menu;
import com.myicellar.digitalmenu.dao.mapper.MenuMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.response.MenuTreeRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MenuService extends BaseService<Long, Menu, MenuMapperExt> {

    /**
     * 查询全量菜单树状列表
     *
     * @return
     */
    public List<MenuTreeRespVO> queryTree() {
        List<MenuTreeRespVO> resultList = new ArrayList<MenuTreeRespVO>();
        Long parentId = 0L;
        List<Menu> topList = mapper.selectListByParentId(parentId);
        if (!CollectionUtils.isEmpty(topList)) {
            for (Menu menu : topList) {
                MenuTreeRespVO respVO = ConvertUtils.convert(menu, MenuTreeRespVO.class);
                List<MenuTreeRespVO> children = getChildrenList(menu.getMenuId());
                respVO.setChildren(children);
                resultList.add(respVO);
            }
        }

        return resultList;
    }

    /**
     * 下级部门树状列表查询
     *
     * @param parentId
     * @return
     */
    public List<MenuTreeRespVO> getChildrenList(Long parentId) {
        List<MenuTreeRespVO> resultList = new ArrayList<MenuTreeRespVO>();
        //子菜单列表，树结构
        List<Menu> childrenList = mapper.selectListByParentId(parentId);
        if (!CollectionUtils.isEmpty(childrenList)) {
            for (Menu menu : childrenList) {
                MenuTreeRespVO respVO = ConvertUtils.convert(menu, MenuTreeRespVO.class);
                //递归
                List<MenuTreeRespVO> children = getChildrenList(menu.getMenuId());
                respVO.setChildren(children);
                resultList.add(respVO);
            }
        }

        return resultList;
    }
}