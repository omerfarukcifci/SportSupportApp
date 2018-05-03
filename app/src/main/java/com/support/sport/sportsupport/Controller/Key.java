package com.support.sport.sportsupport.Controller;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Member;

import java.util.List;

/**
 * Created by Merve on 2.05.2018.
 */

public class Key {

    public static Member cMember = null;

    public Member getCMember(){
        return cMember;
    }

    public static List<Course> allClist = null;
    public static List<Course> myClist = null;
}
