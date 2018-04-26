package com.support.sport.sportsupport.Model;

import java.util.Date;

/**
 * Created by Faruk on 26.04.2018.
 */

public class SpecialOffer {

    private String name;
    private int branchId;
    private Date startDate;
    private Date finishDate;
    private int referenceNumberLimit;
    private int attendanceLimit;

    public SpecialOffer(String name, int branchId, Date startDate, Date finishDate, int referenceNumberLimit, int attendanceLimit) {
        this.name = name;
        this.branchId = branchId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.referenceNumberLimit = referenceNumberLimit;
        this.attendanceLimit = attendanceLimit;
    }

    public SpecialOffer(String name, Date startDate, Date finishDate, int attendanceLimit) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
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
}
