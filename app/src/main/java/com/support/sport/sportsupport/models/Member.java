package com.support.sport.sportsupport.models;

import java.util.Date;

/**
 * Created by Faruk on 12.04.2018.
 */

public class Member {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String statue; //This is for user's statue. It can be banned, owner, active, inactive, non-member.
    private String status; //It can be gold, platinum, standard.
    private String mail;
    private int referenceNumber; //This is for a member's friend count that came with member.
    private int branchAuthority; //This count for user's entered another branches except member's registered branch


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





}
