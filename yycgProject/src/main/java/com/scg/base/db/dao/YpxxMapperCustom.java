package com.scg.base.db.dao;

import java.util.List;

import com.scg.base.db.model.vo.YpxxCoustomVo;
import com.scg.base.db.model.vo.YpxxCustom;

public interface YpxxMapperCustom {
	public List<YpxxCustom> selectListMedicine(YpxxCoustomVo ypxxCoustomVo) throws Exception;
	
	public int queryTotalMedicine(YpxxCoustomVo ypxxCoustomVo) throws Exception;
}
