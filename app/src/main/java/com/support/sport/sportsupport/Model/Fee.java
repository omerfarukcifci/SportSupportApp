package com.support.sport.sportsupport.Model;


import com.google.gson.annotations.SerializedName;

public class Fee {

    @SerializedName("id")
	private int id;
    @SerializedName("weeklyClass")
	private int weeklyClass;
    @SerializedName("oneTimeClass")
	private int oneTimeClass;
    @SerializedName("poolMembership")
	private int poolMembership;
    @SerializedName("standardMembership")
	private int standardMembership;
    @SerializedName("goldMembership")
	private int goldMembership;
    @SerializedName("platinumMembership")
	private int platinumMembership;
    @SerializedName("branchId")
	private int branchId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeeklyClass() {
		return weeklyClass;
	}
	public void setWeeklyClass(int weeklyClass) {
		this.weeklyClass = weeklyClass;
	}
	public int getOneTimeClass() {
		return oneTimeClass;
	}
	public void setOneTimeClass(int oneTimeClass) {
		this.oneTimeClass = oneTimeClass;
	}
	public int getPoolMembership() {
		return poolMembership;
	}
	public void setPoolMembership(int poolMembership) {
		this.poolMembership = poolMembership;
	}
	public int getStandardMembership() {
		return standardMembership;
	}
	public void setStandardMembership(int standardMembership) {
		this.standardMembership = standardMembership;
	}
	public int getGoldMembership() {
		return goldMembership;
	}
	public void setGoldMembership(int goldMembership) {
		this.goldMembership = goldMembership;
	}
	public int getPlatinumMembership() {
		return platinumMembership;
	}
	public void setPlatinumMembership(int platinumMembership) {
		this.platinumMembership = platinumMembership;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public Fee(){}
	
	public Fee(int weeklyclass, int oneTimeClass, int poolMembership, int standardMembership, int goldMembership,
		int platinumMembership) {
		this.weeklyClass = weeklyclass;
		this.oneTimeClass = oneTimeClass;
		this.poolMembership = poolMembership;
		this.standardMembership = standardMembership;
		this.goldMembership = goldMembership;
		this.platinumMembership = platinumMembership;
	}
	
}
