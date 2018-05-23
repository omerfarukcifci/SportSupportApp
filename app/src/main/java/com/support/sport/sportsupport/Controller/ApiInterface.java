package com.support.sport.sportsupport.Controller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.google.gson.JsonObject;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Fee;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Model.MemberList;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.Model.Trainer;

import org.json.JSONObject;

import java.util.List;

public interface ApiInterface {

    //user controller
    @GET("member/get")
    Call<Member> getMemberWithUsernamePassword(@Query("username") String username, @Query("password") String password);
    @GET("manager/get")
    Call<Manager> getManagerWithUsernamePassword(@Query("username") String username, @Query("password") String password);
    @GET("trainer/get")
    Call<Trainer> getTrainerWithUsernamePassword(@Query("username") String username, @Query("password") String password);

    @GET("member/add")
    Call<Member> registerMember(@Query("name") String name, @Query("surname") String surname, @Query("username") String username,
                                @Query("password") String password, @Query("mail") String mail, @Query("age") String age);


    //course controller
    @GET("course/all/{id}")
    Call<List<Course>> showAllCourses(@Path("id") int branchId);

    @GET("enrolledcourses/all/my/{id}")
    Call<List<Course>> showMyCourses(@Path("id") int memberId);

    @GET("course/get/{id}")
    Call<Course> getOneCourse(@Path("id") int courseId);

    @GET("course/enroll/{id}")
    Call<ClassMemberList> enrollCourse(@Path("id") int courseId, @Query("memberId") int memberId);

    @GET("course/drop/{id}")
    Call<ClassMemberList> dropCourse(@Path("id") int courseId, @Query("memberId") int memberId);

    @GET("course/add")
    Call<Course> addCourse(@Query("name") String name, @Query("quota") int quota, @Query("trainerId") int traninerId,
                           @Query("branchId") int branchId, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("cDate") String cDate,
                           @Query("description") String description, @Query("species") String species);


    @GET("course/delete/{id}")
    Call<Course> deleteCourse(@Path("id") int id);

    //myprofile
    //class diagramdaki loadmemberinfo için retrofite gerek yok, loginde zaten biliglerini aldık, boş bir şey yazabilirisniz
    //viewbranchfullness i ben yaparım
    @GET("activity/schedule/{id}")
    Call<List<ActivityPlan>> getMySchedule(@Path("id") int memberId);

    @GET("member/update/personalinfo")
    Call<Member> updateProfile(@Query("id") int memberId, @Query("name") String name, @Query("surname") String surname,
                               @Query("newusername") String username, @Query("newpassword") String newpassword,
                               @Query("mail") String mail, @Query("age") String age);

    //Transactional -- farkli bir durumu olabilir
    @GET("member/cancel")
    Call<Member> cancelMembership(@Query("username") String username, @Query("endDate") String endDate);



    @GET("manager/add")
    Call<Manager> registerManager(@Query("name") String name, @Query("surname") String surname, @Query("username") String username,
                                 @Query("password") String password, @Query("branchId") int branchId);

    @GET("manager/all")
    Call<List<Manager>> allManagers();

    @GET("manager/allWithBranchNames")
    Call<List<Manager>> allManagersWithBranchNames();

    @GET("manager/delete/{id}")
    Call<Manager> deleteManager(@Path("id") String managerId);

    @GET("branch/all")
    Call<List<Branch>> showBranches();

    @GET("trainer/delete/{id}")
    Call<Trainer> deleteTrainer(@Path("id") int trainerId);

    @GET("member/delete/{id}")
    Call<Member> deleteMember(@Path("id") int memberId);

    @GET("member/all/{id}")
    Call<List<Member>> allMembers(@Path("id") int id);

    @GET("trainer/add")
    Call<Trainer> registerTrainer(@Query("name") String name, @Query("surname") String surname, @Query("username") String username,
                                  @Query("password") String password, @Query("id") int branchId);

    @GET("trainer/all/{id}")
    Call<List<Trainer>> findTrainerWithBranchId(@Path("id") int branchId);

    @GET("fee/add")
    Call<Fee> saveFeeList(@Query("weeklyClass") int weekly,@Query("oneTimeClass") int oneTime,@Query("poolMembership") int pool, @Query("standardMembership") int stand,@Query("goldMembership") int gold,@Query("platinumMembership") int platin, @Query("branchId") int branchId);

    @GET("fee/get/{id}")
    Call<Fee> showFeeList(@Path("id") int branchId);

    @GET("branch/add")
    Call<Branch> createBranch(@Query("name") String name, @Query("quota") int quota,
                              @Query("telephoneNumber") long phoneNumber, @Query("city") String city,
                              @Query("district") String district, @Query("address") String address);

    @GET("memberlist/get/memberlist/{id}")
    Call<MemberList> getBranchId(@Path("id") int id);

    @GET("branch/delete/{id}")
    Call<Branch> deleteBranch(@Path("id") int id);

    @GET("offer/all/branch/{id}")
    Call<List<SpecialOffer>> getSpecialOffers(@Path("id") int id);

    @GET("offer/canApply/member/{id}")
    Call<List<SpecialOffer>> getMembersSpecialOffer(@Path("id") int id);

    @GET("offer/add")
    Call<SpecialOffer> registerSpecialOffer(@Query("name") String name, @Query("branchId") String branchId, @Query("startDate") String startDate,
                                  @Query("finishDate") String finishDate, @Query("discountAmount") String discountAmount,  @Query("referenceNumberLimit") String referenceNumberLimit, @Query("attendanceLimit") String attendanceLimit);

    @GET("offer/delete/{id}")
    Call<SpecialOffer> deleteSpecialOffer(@Path("id") int id);

    @GET("offer/apply")
    Call<SpecialOffer> applySpecialOffer(@Query("offerId") int offerId, @Query("memberId") int memberId);

    @GET("offerlist/check")
    Call<Boolean> controlSpecialOffer(@Query("offerId") int offerId, @Query("memberId") int memberId);

    @GET("member/upgrade/membership/{id}")
    Call<Fee> loadUpgradeFee(@Path("id") int id);

    @GET("member/payment/membership")
    Call<Member> makePayment(@Query("id") int id, @Query("startDate") String startDate, @Query("branchId") int branchId, @Query("status") String status);

    @GET("enrolledcourses/is/enrolled")
    Call<ResponseBody> isEnrolled(@Query("memberId") int memberId, @Query("courseId") int courseId);

}