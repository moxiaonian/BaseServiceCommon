package com.cxd.baseservice.mapper;

import java.util.List;

/**
 * Created by wenter on 2017/7/11.
 */
public interface BaseMapper<D,E,ID> {
    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(ID id);

    int insert(D record);

    int insertSelective(D record);

    List<D> selectByExample(E example);

    D selectByPrimaryKey(ID id);

    int updateByExampleSelective( D record, E example);

    int updateByExample(D record, E example);

    int updateByPrimaryKeySelective(D record);

    int updateByPrimaryKey(D record);
}
