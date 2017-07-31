package com.scg.base.db.model.vo;

import com.scg.base.db.model.po.Ypxx;

public class YpxxCustom extends Ypxx{
	private String jyztmc;
	private String lbmc;

	
	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getJyztmc() {
		return jyztmc;
	}

	public void setJyztmc(String jyztmc) {
		this.jyztmc = jyztmc;
	}
	
}
