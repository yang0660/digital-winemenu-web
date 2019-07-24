package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Menu;

import java.util.List;

public interface MenuMapperExt extends MenuMapper {

    List<Menu> queryMenuList();

    List<Menu> selectListByParentId(Long parentId);
}