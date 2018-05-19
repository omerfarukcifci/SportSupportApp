package com.support.sport.sportsupport.Controller;

import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Model.MemberList;
import com.support.sport.sportsupport.Model.Trainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Merve on 2.05.2018.
 */

public class Key {

    public static Member cMember = null;
    public static Manager cManager = null;
    public static Trainer cTrainer = null;
    public static MemberList cMemberList = null;
    public static Manager newManager = null;
    public static Manager deletedManager = null;
    public static Trainer deletedTrainer = null;
    public static Member deletedMember = null;
    public static Trainer newTrainer = null;
    public static Course addedCourse = null;
    public static List<Manager> allManagers = null;
    public static List<Member> allMembers = null;
    public static List<Trainer> allTrainers = null;
    public static boolean manSetChanged = false;
    public static Branch deletedBranch = null;
    public static boolean branchSetChanged = false;
    public static boolean memberSetChanged = false;
    public static boolean courseSetChanged = false;
    public static boolean trainerSetChanged = false;
    public static ArrayList<String> getAllTrainersName() {
        ArrayList<String> trainerName = new ArrayList<String>();
        for (Trainer t : allTrainers){
            trainerName.add(t.getName()+" "+t.getSurname());
        }
        return trainerName;
    }

    public static ArrayList<String> getAllBranchesName() {
        ArrayList<String> branchName = new ArrayList<String>();
        for (Branch t : allBranches){
            branchName.add(t.getName());
        }
        return branchName;
    }

    public static Course oneCourse = null;
    public static Branch addedBranch = null;
    public static List<Branch> allBranches = null;
    public static List<ActivityPlan> memberSchedule=null;
    public static Member updatedMember=null;
    public static Member addedMember=null;
    public static Member cancelledMember=null;
    public static ClassMemberList enrolledClassMemberList =null;
    public static List<Course> allClist = null;
    public static List<Course> myClist = null;
    public static boolean updatedProfile = false;
    public static boolean courseUpdated = false;
    public static int isAv = 0;

}
