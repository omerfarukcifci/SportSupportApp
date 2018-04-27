package com.support.sport.sportsupport.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Merve on 25.04.2018.
 */


@SuppressWarnings("serial")
public class Course implements Serializable{

    private String name;
    private int quota;
    private int branchId;
    private Date startDate;
    private Date endDate;
    private int trainerId;
    private String species;
    private Date currentDate;
    private String description;
    private int availableQuota;

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    private String deleteDate;

    public String getDeleteDay() {
        return deleteDay;
    }

    public void setDeleteDay(String deleteDay) {
        this.deleteDay = deleteDay;
    }

    private String deleteDay;

    public String getDeleteTrainer() {
        return deleteTrainer;
    }

    public void setDeleteTrainer(String deleteTrainer) {
        this.deleteTrainer = deleteTrainer;
    }

    private String deleteTrainer;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuota() {
        return quota;
    }
    public void setQuota(int quota) {
        this.quota = quota;
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
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getTrainerId() {
        return trainerId;
    }
    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public Date getCurrentDate() {
        return currentDate;
    }
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAvailableQuota() {
        return availableQuota;
    }
    public void setAvailableQuota(int availableQuota) {
        this.availableQuota = availableQuota;
    }

    public Course(String name, int quota, int availableQuota, String day, String end, String trainer, String text){
        this.name = name;
        this.availableQuota = availableQuota;
        this.deleteDay = day;
        this.deleteDate = end;
        this.quota = quota;
        this.deleteTrainer = trainer;
        this.description=text;
    }

    public Course(String name, int quota, int branchId, Date startDate, Date endDate, int trainerId, String species, Date currentDate, String description, int availableQuota){
        this.name = name;
        this.quota = quota;
        this.branchId = branchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainerId = trainerId;
        this.species = species;
        this.currentDate = currentDate;
        this.description = description;
        this.availableQuota = availableQuota;
    }

}
