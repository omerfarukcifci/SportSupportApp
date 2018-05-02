package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Merve on 12.04.2018.
 */

public class Member {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("statue")
    private String statue; //This is for user's statue. It can be banned, owner, active, inactive, non-member.
    @SerializedName("status")
    private String status; //It can be gold, platinum, standard.
    @SerializedName("mail")
    private String mail;
    @SerializedName("referenceNumber")
    private int referenceNumber; //This is for a member's friend count that came with member.
    @SerializedName("branchAuthority")
    private int branchAuthority; //This count for user's entered another branches except member's registered branch
    @SerializedName("age")
    private String age;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("startDate")
    private String startDate;

    public String getAge() { return age;}
    public void setAge(String age) { this.age = age;}
    public String getEndDate() { return endDate;}
    public void setEndDate(String endDate) {this.endDate = endDate;}
    public String getStartDate() { return startDate;}
    public void setStartDate(String startDate) { this.startDate = startDate;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStatue() {
        return statue;
    }
    public void setStatue(String statue) {
        this.statue = statue;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getBranchAuthority() {
        return branchAuthority;
    }
    public void setBranchAuthority(int branchAuthority) {
        this.branchAuthority = branchAuthority;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Member(String name, String surname, String username, String password, String statue, String status, String mail, int referenceNumber, int branchAuthority, String age){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.statue = statue;
        this.status = status;
        this.mail = mail;
        this.referenceNumber = referenceNumber;
        this.branchAuthority = branchAuthority;
        this.age = age;
    }

    public Member(String name, String surname, String username, String statue, String status){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.statue = statue;
        this.status = status;
    }

    public Member() {
    }
}
