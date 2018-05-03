package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;

public class ClassMemberList {
	@SerializedName("id")
	private int id;

	@SerializedName("courseId")
	private int courseId;
	@SerializedName("memberId")
	private int memberId;
	@SerializedName("attendace")
	private int attendance;
	
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	
	public ClassMemberList(int courseId, int memberId, int attendance) {
		this.courseId = courseId;
		this.memberId = memberId;
		this.attendance = attendance;
	}
	
	public ClassMemberList() {
	}
	
}
