package com.support.sport.sportsupport.Controller;

import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.BranchStats;
import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Fee;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Model.MemberList;
import com.support.sport.sportsupport.Model.Move;
import com.support.sport.sportsupport.Model.SpecialOffer;
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
    public static SpecialOffer newSpecialOffer = null;
    public static SpecialOffer appliedSpecialOffer = null;
    public static Fee selectedBranchFee = null;

    public static SpecialOffer getAppliedSpecialOffer() {
        return appliedSpecialOffer;
    }

    public static SpecialOffer getNewSpecialOffer() {
        return newSpecialOffer;
    }
    public static SpecialOffer deletedSpecialOffer = null;

    public static SpecialOffer getDeletedSpecialOffer() {
        return deletedSpecialOffer;
    }


    public static List<SpecialOffer> allSpecialOffers = null;
    public static List<SpecialOffer> membersSpecialOffer = null;

    public static List<SpecialOffer> getMembersSpecialOffer() {
        return membersSpecialOffer;
    }

    public static  Boolean controlSpecialOffer ;

    public static Boolean getControlSpecialOffer() {
        return controlSpecialOffer;
    }

    public static List<SpecialOffer> getAllSpecialOffers() {
        return allSpecialOffers;
    }
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
    public static Branch addedBranch=null;
    public static List<Member> allTrainees=null;
    public static List<Move> allMovements=null;
    public static List<Move> selectedMovements=null;
    public static Course oneCourse = null;
    public static List<Branch> allBranches = null;
    public static List<ActivityPlan> memberSchedule=null;
    public static Member updatedMember=null;
    public static Member addedMember=null;
    public static Member bannedMember=null;
    public static Member cancelledMember=null;
    public static List<Course> allClist = null;
    public static List<Course> myClist = null;
    public static boolean updatedProfile = false;
    public static boolean userCourseListChanged = false;
    public static boolean userMyCListChanged = false;
    public static Boolean isEnrolled = false;
    public static boolean offerListChanged = false;

    public static Member getBannedMember() {
        return bannedMember;
    }

    public static BranchStats selectedBranchStats = null;

    public static BranchStats getSelectedBranchStats() {
        return selectedBranchStats;
    }
}
