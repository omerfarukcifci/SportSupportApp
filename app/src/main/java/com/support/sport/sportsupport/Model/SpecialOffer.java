package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Faruk on 26.04.2018.
 */

public class SpecialOffer {


    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("branchId")
    private int branchId;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("finishDate")
    private String finishDate;
    @SerializedName("referenceNumberLimit")
    private int referenceNumberLimit;
    @SerializedName("attendanceLimit")
    private int attendanceLimit;

    @SerializedName("discountAmount")
    private String discount;
    public SpecialOffer(String name, int branchId, String startDate, String finishDate, int referenceNumberLimit, int attendanceLimit) {
        this.name = name;
        this.branchId = branchId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.referenceNumberLimit = referenceNumberLimit;
        this.attendanceLimit = attendanceLimit;
    }

    public SpecialOffer(String name, String startDate, String finishDate, int attendanceLimit) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.attendanceLimit = attendanceLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getReferenceNumberLimit() {
        return referenceNumberLimit;
    }

    public void setReferenceNumberLimit(int referenceNumberLimit) {
        this.referenceNumberLimit = referenceNumberLimit;
    }

    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    public void setAttendanceLimit(int attendanceLimit) {
        this.attendanceLimit = attendanceLimit;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
