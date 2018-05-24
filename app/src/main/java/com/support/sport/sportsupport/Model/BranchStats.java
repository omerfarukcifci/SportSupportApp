package com.support.sport.sportsupport.Model;
import com.google.gson.annotations.SerializedName;
public class BranchStats {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(int managerCount) {
        this.managerCount = managerCount;
    }

    public int getTrainerCount() {
        return trainerCount;
    }

    public void setTrainerCount(int trainerCount) {
        this.trainerCount = trainerCount;
    }

    public int getStandartMemberCount() {
        return standardMemberCount;
    }

    public void setStandartMemberCount(int standartMemberCount) {
        this.standardMemberCount = standartMemberCount;
    }

    public int getGoldMemberCount() {
        return goldMemberCount;
    }

    public void setGoldMemberCount(int goldMemberCount) {
        this.goldMemberCount = goldMemberCount;
    }

    public int getPlatinumMemberCount() {
        return platinumMemberCount;
    }

    public void setPlatinumMemberCount(int platinumMemberCount) {
        this.platinumMemberCount = platinumMemberCount;
    }

    public int getCourseStudentCount() {
        return courseStudentCount;
    }

    public void setCourseStudentCount(int courseStudentCount) {
        this.courseStudentCount = courseStudentCount;
    }

    public BranchStats(String name, int managerCount, int trainerCount, int standartMemberCount, int goldMemberCount, int platinumMemberCount, int courseStudentCount) {
        this.name = name;
        this.managerCount = managerCount;
        this.trainerCount = trainerCount;
        this.standardMemberCount = standartMemberCount;
        this.goldMemberCount = goldMemberCount;
        this.platinumMemberCount = platinumMemberCount;
        this.courseStudentCount = courseStudentCount;
    }

    @SerializedName("branchName")
    private String name;
    @SerializedName("managerCount")
    private int managerCount;
    @SerializedName("trainerCount")
    private int trainerCount;
    @SerializedName("standardMemberCount")
    private int standardMemberCount;
    @SerializedName("goldMemberCount")
    private int goldMemberCount;
    @SerializedName("platinumMemberCount")
    private int platinumMemberCount;
    @SerializedName("courseStudentCount")
    private int courseStudentCount;





}
