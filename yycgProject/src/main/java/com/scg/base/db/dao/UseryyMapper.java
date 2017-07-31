package com.scg.base.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.scg.base.db.model.po.Useryy;
import com.scg.base.db.model.po.UseryyExample;


public interface UseryyMapper {
    int countByExample(UseryyExample example);

    int deleteByExample(UseryyExample example);

    int deleteByPrimaryKey(String id);

    int insert(Useryy record);

    int insertSelective(Useryy record);

    List<Useryy> selectByExample(UseryyExample example);

    Useryy selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Useryy record, @Param("example") UseryyExample example);

    int updateByExample(@Param("record") Useryy record, @Param("example") UseryyExample example);

    int updateByPrimaryKeySelective(Useryy record);

    int updateByPrimaryKey(Useryy record);
}