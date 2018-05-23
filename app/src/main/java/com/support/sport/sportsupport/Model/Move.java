package com.support.sport.sportsupport.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Faruk on 21.05.2018.
 */

public class Move {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    private int setNumber;
    private int position;

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Move(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public Move( int setNumber,String name) {
        this.name = name;
        this.setNumber=setNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
