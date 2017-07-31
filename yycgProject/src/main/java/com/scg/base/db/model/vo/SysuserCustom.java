package com.scg.base.db.model.vo;

import com.scg.base.db.model.po.Sysuser;

public class SysuserCustom extends Sysuser{
	
	private String sysmc;
	private String groupname;
	private String statename;
	
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getSysmc() {
		return sysmc;
	}

	public void setSysmc(String sysmc) {
		this.sysmc = sysmc;
	} 
	
	
}
