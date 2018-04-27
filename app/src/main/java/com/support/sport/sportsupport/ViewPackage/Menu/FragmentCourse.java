package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.R;

public class FragmentCourse extends AppCompatActivity {

    TextView title, date, trainer, freq, desc,quota;
    Button enrolldrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);



        Intent i = getIntent();
        Course c = (Course) i.getSerializableExtra("MyCourse");
        int category = i.getIntExtra("category",-1);

        enrolldrop = findViewById(R.id.spec_course_drop);
        if (category==0) enrolldrop.setText("ENROLL");

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
        quota.setText("Available Quota: "+c.getAvailableQuota()+"/"+c.getQuota());
    }
}
