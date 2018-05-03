package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            Toast.makeText(getApplicationContext(), "The process is successfully",Toast.LENGTH_LONG).show();
        }else{
        //    Toast.makeText(getApplicationContext(), "Invalid process",Toast.LENGTH_LONG).show();
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
        final Course c = (Course) i.getSerializableExtra("MyCourse");
        final  int category = i.getIntExtra("category",-1);
        //  int category =0;
        int find = 0;

        if(Key.myClist!=null)
        for(int l = 0 ; l < Key.myClist.size(); l++){
            if(Key.myClist.get(l).getId()==c.getId()){
                find =1;
            }

        }
        final int cat = find;

        enrolldrop = findViewById(R.id.spec_course_drop);
        if (cat==0) enrolldrop.setText("ENROLL");
        if (cat==1) {
            enrolldrop.setText("DROP");
            enrolldrop.setBackgroundColor(Color.RED);
        }

        title = findViewById(R.id.spec_course_name);
        date = findViewById(R.id.spec_course_enddate);
        trainer = findViewById(R.id.spec_course_trainer);
        freq = findViewById(R.id.spec_course_freq);
        desc = findViewById(R.id.spec_course_text);
        quota = findViewById(R.id.spec_course_quota);

        title.setText(c.getName()+" CLASS");

        date.setText("Ends at: "+c.getEndDate());
        trainer.setText("Trainer: "+c.getTrainerId());
        freq.setText("Every "+c.getDeleteDay());
        desc.setText(c.getDescription());
        quota.setText("Available Quota: "+c.getAvailableQuota()+"/"+c.getQuota());
      /*  if(c.getAvailableQuota()==0) {
            enrolldrop.setClickable(false);
            enrolldrop.setBackgroundColor(Color.GRAY);
        }
        */
        //   quota.setText("Available Quota: "+c.getAvailableQuota()+"/"+c.getQuota());
        final  int availableQuota = c.getAvailableQuota();


        enrolldrop.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (availableQuota == 0) {
             /*       Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.LLcourse), "No Quota Available !", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.show();
                    */


                }else{
                    if(cat==0) {




                        CourseController courseC = new CourseController();
                        courseC.enrollCourse(c.getId(),Key.cMember.getId());
                        courseC.getMyCourses(Key.cMember.getId());
                        //       Toast.makeText(FragmentCourse.this, "Succesfully Dropped!", Toast.LENGTH_LONG).show();

                        Toast.makeText(FragmentCourse.this, "Enrollment Completed!", Toast.LENGTH_LONG).show();
                        enrolldrop.setText("DROP");
                        enrolldrop.setBackgroundColor(Color.RED);
                        //   category = 1;
                    }else{

                        CourseController courseC = new CourseController();
                        courseC.dropCourse(c.getId(), Key.cMember.getId());
                        courseC.getMyCourses(Key.cMember.getId());
                        Toast.makeText(FragmentCourse.this, "Succesfully Dropped!", Toast.LENGTH_LONG).show();
                        enrolldrop.setText("ENROLL");
                        enrolldrop.setBackgroundColor(Color.parseColor("#3395ff"));
                        //   category = 0;
                    }
                }
            }
        });



    }
}