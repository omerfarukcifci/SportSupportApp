package com.support.sport.sportsupport.ViewPackage.Management;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.MyProfile;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

public class UserUpdateScreen extends AppCompatActivity {
    private EditText name, surname, username,  mail, password;
    private Button updateUserBTN,setBirthdayBTN;
    final Context context = this;
    public static EditText birtdate;








    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        static final int START_DATE = 1;
        static final int END_DATE = 2;

        private int mChosenDate;

        int cur = 0;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            Bundle bundle = this.getArguments();
            if (bundle != null) {
                mChosenDate = bundle.getInt("DATE", 1);
            }


            switch (mChosenDate) {

                case START_DATE:
                    cur = START_DATE;
                    return new DatePickerDialog(getActivity(), this, year, month, day);

                case END_DATE:
                    cur = END_DATE;
                    return new DatePickerDialog(getActivity(), this, year, month, day);

            }
            return null;
        }


        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            if (cur == START_DATE) {
                // set selected date into textview

                birtdate.setText(new StringBuilder().append(year)
                        .append("-").append(month + 1).append("-").append(day)
                        );

            }
        }

    }


    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if (event.isRetrofitCompleted) {
            Toast.makeText(UserUpdateScreen.this, "User Successfully Updated", Toast.LENGTH_LONG).show();

            finish();
        } else {
            Toast.makeText(this, "User is not updated ! Try again.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_profile);

        name = findViewById(R.id.newNameUM);
        surname = findViewById(R.id.newSurnameUM);
        username = findViewById(R.id.newUserNameUM);
        birtdate = findViewById(R.id.newBirthdayUM);
        mail = findViewById(R.id.newEmailUM);
        password = findViewById(R.id.newPasswordUM);
        updateUserBTN = findViewById(R.id.BTN_updateUM);
        setBirthdayBTN = findViewById(R.id.BTN_set_birtdayUM);
        setBirthdayBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",1);

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getFragmentManager(), "datePicker");
            }
        });





        Bundle extras = getIntent().getExtras();

        name.setText(extras.getString("MemberName"));
        surname.setText(extras.getString("MemberSurname"));
        username.setText(extras.getString("MemberUsername"));
        mail.setText(extras.getString("MemberMail"));



        String pass = extras.getString("MemberPassword");
        String userN = extras.getString("MemberUsername");
     final   int id = extras.getInt("MemberId");


        updateUserBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String nameStr = name.getText().toString();
                final String surnameStr = surname.getText().toString();
                final String usernameStr = username.getText().toString();
                final String birtdateStr = birtdate.getText().toString();
                final String mailStr = mail.getText().toString();
                final String passwordStr = password.getText().toString();

                MyProfile controller = new MyProfile();
                controller.updateProfileInfo(id,name.getText().toString(),surname.getText().toString(),
                        username.getText().toString(),password.getText().toString(),
                        mail.getText().toString(),birtdate.getText().toString());
            }
        });


    }


}
