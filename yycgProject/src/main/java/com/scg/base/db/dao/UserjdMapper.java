package com.scg.base.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.scg.base.db.model.po.Userjd;
import com.scg.base.db.model.po.UserjdExample;

public interface UserjdMapper {
    int countByExample(UserjdExample example);

    int deleteByExample(UserjdExample example);

    int deleteByPrimaryKey(String id);

    int insert(Userjd record);

    int insertSelective(Userjd record);

    List<Userjd> selectByExample(UserjdExample example);

    Userjd selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Userjd record, @Param("example") UserjdExample example);

    int updateByExample(@Param("record") Userjd record, @Param("example") UserjdExample example);

    int updateByPrimaryKeySelective(Userjd record);

    int updateByPrimaryKey(Userjd record);
}