package com.scg.base.db.model;

public class BSS_SYS_AREA {

	private String areaId;
	private String areaName;
	private Integer areaLevel;
	private String areaFullName;
	private String parentId;
	private String shortName;
	private String isUnit;
	private String lastUpDate;
	private String yzCode;
	private String varchar1;
	private String varchar2;
	private String varchar3;
	private String varchar4;
	private String varchar5;
	private String varchar6;
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	public String getAreaFullName() {
		return areaFullName;
	}
	public void setAreaFullName(String areaFullName) {
		this.areaFullName = areaFullName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getIsUnit() {
		return isUnit;
	}
	public void setIsUnit(String isUnit) {
		this.isUnit = isUnit;
	}
	public String getLastUpDate() {
		return lastUpDate;
	}
	public void setLastUpDate(String lastUpDate) {
		this.lastUpDate = lastUpDate;
	}
	public String getYzCode() {
		return yzCode;
	}
	public void setYzCode(String yzCode) {
		this.yzCode = yzCode;
	}
	public String getVarchar1() {
		return varchar1;
	}
	public void setVarchar1(String varchar1) {
		this.varchar1 = varchar1;
	}
	public String getVarchar2() {
		return varchar2;
	}
	public void setVarchar2(String varchar2) {
		this.varchar2 = varchar2;
	}
	public String getVarchar3() {
		return varchar3;
	}
	public void setVarchar3(String varchar3) {
		this.varchar3 = varchar3;
	}
	public String getVarchar4() {
		return varchar4;
	}
	public void setVarchar4(String varchar4) {
		this.varchar4 = varchar4;
	}
	public String getVarchar5() {
		return varchar5;
	}
	public void setVarchar5(String varchar5) {
		this.varchar5 = varchar5;
	}
	public String getVarchar6() {
		return varchar6;
	}
	public void setVarchar6(String varchar6) {
		this.varchar6 = varchar6;
	}
	public BSS_SYS_AREA(String areaId, String areaName, Integer areaLevel, String areaFullName, String parentId,
			String shortName, String isUnit, String lastUpDate, String yzCode, String varchar1, String varchar2,
			String varchar3, String varchar4, String varchar5, String varchar6) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaLevel = areaLevel;
		this.areaFullName = areaFullName;
		this.parentId = parentId;
		this.shortName = shortName;
		this.isUnit = isUnit;
		this.lastUpDate = lastUpDate;
		this.yzCode = yzCode;
		this.varchar1 = varchar1;
		this.varchar2 = varchar2;
		this.varchar3 = varchar3;
		this.varchar4 = varchar4;
		this.varchar5 = varchar5;
		this.varchar6 = varchar6;
	}
	public BSS_SYS_AREA() {}
	@Override
	public String toString() {
		return "BSS_SYS_AREA [areaId=" + areaId + ", areaName=" + areaName + ", areaLevel=" + areaLevel
				+ ", areaFullName=" + areaFullName + ", parentId=" + parentId + ", shortName=" + shortName + ", isUnit="
				+ isUnit + ", lastUpDate=" + lastUpDate + ", yzCode=" + yzCode + ", varchar1=" + varchar1
				+ ", varchar2=" + varchar2 + ", varchar3=" + varchar3 + ", varchar4=" + varchar4 + ", varchar5="
				+ varchar5 + ", varchar6=" + varchar6 + "]";
	}
	
	
}

