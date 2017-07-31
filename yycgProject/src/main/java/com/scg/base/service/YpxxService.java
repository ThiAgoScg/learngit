package com.scg.base.service;

import java.util.List;

import com.scg.base.db.model.po.Ypxx;
import com.scg.base.db.model.vo.YpxxCoustomVo;
import com.scg.base.db.model.vo.YpxxCustom;

public interface YpxxService {
	
	public List<YpxxCustom> findYpxxTotal(YpxxCoustomVo ypxxCoustomVo)throws Exception;
	
	public int findCountYpxx(YpxxCoustomVo ypxxCoustomVo) throws Exception;
	
	public int saveMedicineYpxx(YpxxCoustomVo ypxxCoustomVo) throws Exception;
	
	public int delMedicineYpxx(String id) throws Exception;
	
	public int updateMedicineYpxx(YpxxCoustomVo ypxxCoustomVo) throws Exception;
	
	public  Ypxx findypxxByid(String id) throws Exception;
}
