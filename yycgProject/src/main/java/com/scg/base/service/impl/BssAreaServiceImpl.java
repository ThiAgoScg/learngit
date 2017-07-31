package com.scg.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scg.base.db.dao.BssAreaMapper;
import com.scg.base.service.BssAreaService;

@Service
public class BssAreaServiceImpl implements BssAreaService{
	@Autowired
	private BssAreaMapper bsMapper;

	@Override
	public List<Map<String, Object>> findAllArea() {
		List<Map<String, Object>> bssAreaList = bsMapper.selectAllAreaList();
		return bssAreaList;
	}

}
