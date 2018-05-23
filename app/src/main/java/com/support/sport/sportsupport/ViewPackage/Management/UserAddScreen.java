package com.support.sport.sportsupport.ViewPackage.Management;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

public class UserAddScreen extends AppCompatActivity {
    private EditText name,surname,username,mail,password;
    private Button addNewUser,setBTBTN;
    public static EditText birtdate;
    final Context context = this;


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

        if(event.isRetrofitCompleted){
            Toast.makeText(UserAddScreen.this, "User Successfully Created" , Toast.LENGTH_LONG).show();
            Key.allMembers.add(0,Key.addedMember);
            Key.memberSetChanged = true;
            finish();
        }else{
            Toast.makeText(this, "User is not created ! Try again.",Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.user_add_screen);

        name = findViewById(R.id.name_M);
        surname = findViewById(R.id.surname_M);
        username = findViewById(R.id.username_M);
        birtdate = findViewById(R.id.birthdate_M);
        mail = findViewById(R.id.email_M);
        password = findViewById(R.id.password_MM);
        addNewUser = findViewById(R.id.add_new_user_button);
        setBTBTN = findViewById(R.id.setBTBTN);
        setBTBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",1);

                DialogFragment newFragment = new UserAddScreen.DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getFragmentManager(), "datePicker");
            }
        });


        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spaceController = 0;
                spaceController = controlBlank(name);
                spaceController += controlBlank(surname);
                spaceController += controlBlank(username);
                spaceController += controlBlank(birtdate);
                spaceController += controlBlank(mail);
                spaceController += controlBlank(password);

                final String nameStr = name.getText().toString();
                final String surnameStr = surname.getText().toString();
                final String usernameStr = username.getText().toString();
                final String birtdateStr = birtdate.getText().toString();
                final String mailStr = mail.getText().toString();
                final String passwordStr = password.getText().toString();

                if(spaceController == 0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Are you sure?");
                    alertDialogBuilder
                            .setMessage("Do you want to create this user?")
                            .setCancelable(false)
                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new UserController().addUser(nameStr,surnameStr,usernameStr,passwordStr,mailStr,birtdateStr);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    spaceController = 0;
                    alertDialog.show();

                }else{
                    spaceController =0;
                }
            }
        });


    }

    public int controlBlank(EditText edt){

        String strUserName = edt.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            edt.setError("This field cannot be empty.");
            return 1;
        }
        return 0;
    }
}
