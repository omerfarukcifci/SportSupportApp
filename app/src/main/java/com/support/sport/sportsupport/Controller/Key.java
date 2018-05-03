package com.support.sport.sportsupport.Controller;

import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;

import java.util.List;

/**
 * Created by Merve on 2.05.2018.
 */

public class Key {

    public static Member cMember = null;
    public static Manager newManager = null;
    public static List<Manager> allManagers = null;

    public static List<Manager> getAllManagers() {
        return allManagers;
    }

    public static Course oneCourse = null;

    public Member getCMember(){
        return cMember;
    }
    public void setcMember(Member member){cMember=member;}
    public Course getOneCourse(){
        return oneCourse;
    }



    public static Manager getNewManager() {
        return newManager;
    }

    public static Branch addedBranch=null;

    public static List<ActivityPlan> memberSchedule=null;
    public static Member updatedMember=null;
    public static Member cancelledMember=null;
    public static ClassMemberList enrolledClassMemberList =null;
    public static List<Course> allClist = null;
    public static List<Course> myClist = null;

    public static boolean updatedProfile = false;

    public static boolean courseUpdated = false;




    public  List<Course> getMyClist() {
        return myClist;
    }
    public  ClassMemberList getEnrolledClassMemberList() {
        return enrolledClassMemberList;
    }
}
