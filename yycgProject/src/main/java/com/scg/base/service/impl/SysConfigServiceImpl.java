package com.scg.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scg.base.db.dao.DictinfoMapper;
import com.scg.base.db.dao.DicttypeMapper;
import com.scg.base.db.model.po.Dictinfo;
import com.scg.base.db.model.po.Dicttype;
import com.scg.base.service.SysConfigService;

import com.scg.base.db.model.po.DictinfoExample;
import com.scg.base.db.model.po.DicttypeExample;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.ResultInfo;
import com.scg.base.process.result.ResultUtil;

@Service 
public class SysConfigServiceImpl implements SysConfigService{

	@Autowired
	private DicttypeMapper dicttypeMapper;
	@Autowired
	private DictinfoMapper dictinfoMapper;
	
	
	@Override
	public Dicttype findDictTypeByName(String name) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		DicttypeExample example = new DicttypeExample();
		DicttypeExample.Criteria criteria  = example.createCriteria();
		criteria.andTypenameEqualTo(name);
		List<Dicttype> list = dicttypeMapper.selectByExample(example);
		if(list==null||list.size()<=0){
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1201, null);
			ResultUtil.throwExcepion(resultInfo);
		}
		return list.get(0);
	}
	
	/**
	 * 通过typecode查询所有的数据字典中的类型
	 * @param typecode
	 * @return
	 */
	@Override
	public List<Dictinfo> findDictInfoAll(String typecode) throws Exception{
		DictinfoExample example = new DictinfoExample();
		DictinfoExample.Criteria criteria = example.createCriteria();
		criteria.andTypecodeEqualTo(typecode);
		example.setOrderByClause("dictsort");
		List<Dictinfo> list = dictinfoMapper.selectByExample(example);
		if(list==null||list.size()<=0){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 1202, null));
		}
		return list;
	}
	
	@Override
	public List<Dictinfo> serchDictinfo(String name) throws Exception{
		Dicttype dicttype = this.findDictTypeByName(name);
		List<Dictinfo> list = this.findDictInfoAll(dicttype.getTypecode());
		return list;
	}
}
