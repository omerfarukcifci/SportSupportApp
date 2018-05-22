package com.support.sport.sportsupport.Model;


import com.google.gson.annotations.SerializedName;

public class ActivityPlan {

	@SerializedName("name")
	private String name;
	@SerializedName("memberId")
	private int memberId;
	@SerializedName("sets")
	private int sets;
	@SerializedName("status")
	private boolean status;


	public String getName() {
		return name;
	}
	public void setId(String name) {
		this.name = name;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}



	public ActivityPlan(String name, int memberId, int sets, boolean status){
		this.name = name;
		this.memberId = memberId;
		this.sets = sets;
		this.status = status;
	}
	public ActivityPlan(){}


}
