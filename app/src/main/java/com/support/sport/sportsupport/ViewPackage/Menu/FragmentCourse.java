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

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.SignUpScreen;

public class FragmentCourse extends AppCompatActivity {

    TextView title, date, trainer, freq, desc,quota;
    Button enrolldrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);



        Intent i = getIntent();
        Course c = (Course) i.getSerializableExtra("MyCourse");
      //  int category = i.getIntExtra("category",-1);
        int category =1;

        enrolldrop = findViewById(R.id.spec_course_drop);
        if (category==0) enrolldrop.setText("ENROLL");
        if (category==1) {
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
        date.setText("Ends at: "+c.getDeleteDate());
        trainer.setText("Trainer: "+c.getDeleteTrainer());
        freq.setText("Every "+c.getDeleteDay());
        desc.setText(c.getDescription());
        quota.setText("Available Quota: 1/"+c.getQuota());
      /*  if(c.getAvailableQuota()==0) {
            enrolldrop.setClickable(false);
            enrolldrop.setBackgroundColor(Color.GRAY);
        }
        */
     //   quota.setText("Available Quota: "+c.getAvailableQuota()+"/"+c.getQuota());


        enrolldrop.setOnClickListener(new View.OnClickListener() {

            int availableQuota = 1;
            int category = 1;
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
                    if(category==0) {
                        Toast.makeText(FragmentCourse.this, "Enrollment Completed!", Toast.LENGTH_LONG).show();
                        enrolldrop.setText("DROP");
                        enrolldrop.setBackgroundColor(Color.RED);
                    }else{
                        Toast.makeText(FragmentCourse.this, "Succesfully Dropped!", Toast.LENGTH_LONG).show();
                        enrolldrop.setText("ENROLL");
                        enrolldrop.setBackgroundColor(Color.parseColor("#3395ff"));
                    }
                }
            }
        });
    }
}
