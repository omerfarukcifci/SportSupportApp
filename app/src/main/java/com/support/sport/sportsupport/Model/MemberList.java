package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;


public class MemberList {
	
	@SerializedName("id")
	private int id;

	@SerializedName("memberId")
	private int memberId;
	@SerializedName("branchId")
	private int branchId;
	@SerializedName("trainerId")
	private int trainerId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	
	public MemberList() {}
	
	public MemberList(int memberId, int branchId, int trainerId) {
		this.memberId = memberId;
		this.branchId = branchId;
		this.trainerId = trainerId;
	}
	
	

}
