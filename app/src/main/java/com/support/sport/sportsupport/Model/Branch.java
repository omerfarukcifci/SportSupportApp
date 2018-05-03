package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Faruk on 2.05.2018.
 */

public class Branch {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("quota")
    private int quota;
    @SerializedName("phoneNumber")
    private long phoneNumber;
    @SerializedName("city")
    private String city;
    @SerializedName("district")
    private String district;
    @SerializedName("address")
    private String address;

    public Branch(String name, int quota, long phoneNumber, String city, String district, String adress) {
        this.name = name;
        this.quota = quota;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.district = district;
        this.address = adress;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }
}
