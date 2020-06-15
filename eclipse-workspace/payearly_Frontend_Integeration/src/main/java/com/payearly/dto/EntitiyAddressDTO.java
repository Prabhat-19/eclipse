package com.payearly.dto;

public class EntitiyAddressDTO {
	private String bnm;
	private String loc;
	private String st;
	private String bno;
	private String stcd;
	private String dst;
	private String city;
	private String flno;
	private String pncd;
	private Boolean isPrimary;
	
	
	public String getBnm() {
		return bnm;
	}
	public void setBnm(String bnm) {
		this.bnm = bnm;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFlno() {
		return flno;
	}
	public void setFlno(String flno) {
		this.flno = flno;
	}
	public String getPncd() {
		return pncd;
	}
	public void setPncd(String pncd) {
		this.pncd = pncd;
	}
	
	public Boolean getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	@Override
	public String toString() {
		return "EntitiyAddressDTO [bnm=" + bnm + ", loc=" + loc + ", st=" + st + ", bno=" + bno + ", stcd=" + stcd
				+ ", dst=" + dst + ", city=" + city + ", flno=" + flno + ", pncd=" + pncd + "]";
	}
	
	
	
}