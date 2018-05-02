package com.support.sport.sportsupport.Model;

/**
 * Created by Faruk on 27.04.2018.
 */

public class Manager {

    private String name;
    private String surname;
    private String username;
    private String password;
    private int branchId;

    public Manager(String name, String surname, String username, String password, int branchId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.branchId = branchId;
    }

    public Manager(String name, String surname, String username, int branchId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.branchId = branchId;
    }

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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}