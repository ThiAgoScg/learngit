package com.scg.base.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.scg.base.db.model.po.Ypxx;
import com.scg.base.db.model.po.YpxxExample;


public interface YpxxMapper {
    int countByExample(YpxxExample example);

    int deleteByExample(YpxxExample example);

    int deleteByPrimaryKey(String id);

    int insert(Ypxx record);

    int insertSelective(Ypxx record);

    List<Ypxx> selectByExampleWithBLOBs(YpxxExample example);

    List<Ypxx> selectByExample(YpxxExample example);

    Ypxx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Ypxx record, @Param("example") YpxxExample example);

    int updateByExampleWithBLOBs(@Param("record") Ypxx record, @Param("example") YpxxExample example);

    int updateByExample(@Param("record") Ypxx record, @Param("example") YpxxExample example);

    int updateByPrimaryKeySelective(Ypxx record);

    int updateByPrimaryKeyWithBLOBs(Ypxx record);

    int updateByPrimaryKey(Ypxx record);
}