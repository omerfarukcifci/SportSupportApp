package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Merve on 25.04.2018.
 */



public class Course implements Serializable{
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;
    @SerializedName("quota")
    private int quota;
    @SerializedName("branchId")
    private int branchId;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("trainerId")
    private int trainerId;
    @SerializedName("species")
    private String species;
    @SerializedName("currentDate")
    private String currentDate;
    @SerializedName("description")
    private String description;
    @SerializedName("availableQuota")
    private int availableQuota;

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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
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
    public String getCurrentDate() {
        return currentDate;
    }
    public void setCurrentDate(String currentDate) {
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Course(String name, int quota, int branchId, String startDate, String endDate, int trainerId, String species, String currentDate, String description, int availableQuota){
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
