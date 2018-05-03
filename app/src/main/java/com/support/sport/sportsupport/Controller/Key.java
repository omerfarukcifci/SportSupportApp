package com.support.sport.sportsupport.Controller;

import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Member;

import java.util.List;

/**
 * Created by Merve on 2.05.2018.
 */

public class Key {

    public static Member cMember = null;
    public static Course oneCourse = null;

    public Member getCMember(){
        return cMember;
    }
    public Course getOneCourse(){
        return oneCourse;
    }

    public static ClassMemberList enrolledClassMemberList =null;
    public static List<Course> allClist = null;
    public static List<Course> myClist = null;

    public  List<Course> getMyClist() {
        return myClist;
    }
    public  ClassMemberList getEnrolledClassMemberList() {
        return enrolledClassMemberList;
    }
}
