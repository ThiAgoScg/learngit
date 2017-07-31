package com.scg.base.db.model.vo;

import com.scg.base.db.model.po.PageQuery;

public class SysuserQueryCoustomVo {
	
	private SysuserCustom sysuserCustom;
	
	//分页的类
	private PageQuery pageQuery;

	public SysuserCustom getSysuserCustom() {
		return sysuserCustom;
	}

	public void setSysuserCustom(SysuserCustom sysuserCustom) {
		this.sysuserCustom = sysuserCustom;
	}

	public PageQuery getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	
	
	
	
}
