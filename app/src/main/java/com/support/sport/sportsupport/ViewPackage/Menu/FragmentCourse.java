package com.support.sport.sportsupport.ViewPackage.Menu;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import com.support.sport.sportsupport.ViewPackage.SignInScreen;
import com.support.sport.sportsupport.ViewPackage.SignUpScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentCourse extends AppCompatActivity {

    TextView title, date, trainer, freq, desc,quota;
    Button enrolldrop;
    Course c ;
    int enrollpro = 0;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {
        if (event.pID==-1){}
        else if (event.pID==0) {
            if (!Key.isEnrolled) enrolldrop.setText("ENROLL");
            else {
                enrolldrop.setText("DROP");
                enrolldrop.setBackgroundColor(Color.RED);
            }
            final int availableQuota = c.getAvailableQuota();
            enrolldrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (availableQuota == 0) {
                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.LLcourse), "No Quota Available !", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        if (!Key.isEnrolled) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FragmentCourse.this);
                            alertDialogBuilder.setTitle("Enroll to Course");
                            alertDialogBuilder
                                    .setMessage("Are you sure you want to enroll to this course?")
                                    .setCancelable(true)
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            enrollpro = 1;
                                            new CourseController().enrollCourse(c.getId(), Key.cMember.getId());
                                            finish();
                                        }
                                    });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();

                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FragmentCourse.this);
                            alertDialogBuilder.setTitle("Drop Course");
                            alertDialogBuilder
                                    .setMessage("Are you sure you want to drop this course?")
                                    .setCancelable(true)
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            new CourseController().dropCourse(c.getId(), Key.cMember.getId());
                                            finish();
                                        }
                                    });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    }
                }
            });

        }else{
                if (event.isRetrofitCompleted) {
                    if (enrollpro == 1) {
                        Toast.makeText(FragmentCourse.this, "Enrollment Completed!", Toast.LENGTH_LONG).show();
                        Key.userCourseListChanged = true;
                        c.setAvailableQuota(c.getAvailableQuota() - 1);
                    }else{
                        Toast.makeText(FragmentCourse.this, "Drop Completed!", Toast.LENGTH_LONG).show();
                        Key.userCourseListChanged = true;
                        c.setAvailableQuota(c.getAvailableQuota() + 1);
                        for (Course c1 : Key.myClist){
                            if (c1.getId()==c.getId()){
                                Key.myClist.remove(c1);
                                Key.userMyCListChanged = true;
                            }
                        }
                    }
                }else{
                    Toast.makeText(FragmentCourse.this, "Process failed. Please try later.", Toast.LENGTH_LONG).show();
                }
            }
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);

        Intent i = getIntent();
        final Course c1 = (Course) i.getSerializableExtra("MyCourse");
        c = c1;

        new CourseController().isEnrolledCheck(Key.cMember.getId(),c);

        enrolldrop = findViewById(R.id.spec_course_drop);
        title = findViewById(R.id.spec_course_name);
        date = findViewById(R.id.spec_course_enddate);
        trainer = findViewById(R.id.spec_course_trainer);
        freq = findViewById(R.id.spec_course_freq);
        desc = findViewById(R.id.spec_course_text);
        quota = findViewById(R.id.spec_course_quota);
        title.setText(c.getName()+" Class");
        date.setText("Ends at: "+(c.getEndDate().split("T"))[0]);
        trainer.setText("Trainer: "+c.getTrainerId());
        if(c.getSpecies().equals("weekly")) freq.setText("Every Week");
        else freq.setText("One Time");
        desc.setText(c.getDescription());
        quota.setText("Available Quota: "+c.getAvailableQuota()+"/"+c.getQuota());



    }

}