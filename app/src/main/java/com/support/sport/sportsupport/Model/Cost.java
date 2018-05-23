package com.support.sport.sportsupport.Model;

/**
 * Created by Merve on 23.05.2018.
 */

public class Cost {

    private String type, p1, p2, p3;
    private int totalcost;

    public Cost(String t, String p11, String p22, String p33){
        type = t;
        p1 = p11;
        p2 = p22;
        p3 = p33;
    }

    public int getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }
}
