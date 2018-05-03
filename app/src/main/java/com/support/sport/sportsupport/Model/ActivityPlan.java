package com.support.sport.sportsupport.Model;


import com.google.gson.annotations.SerializedName;

public class ActivityPlan {

	@SerializedName("id")
	private int id;
	@SerializedName("moveId")
	private int moveId;
	@SerializedName("memberId")
	private int memberId;
	@SerializedName("sets")
	private int sets;
	@SerializedName("status")
	private boolean status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMoveId() {
		return moveId;
	}
	public void setMoveId(int moveId) {
		this.moveId = moveId;
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
	
	ActivityPlan(){}
	
	ActivityPlan(int moveId, int memberId, int sets, boolean status){
		this.moveId = moveId;
		this.memberId = memberId;
		this.sets = sets;
		this.status = status;
	}


}
