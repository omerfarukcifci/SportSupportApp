package com.support.sport.sportsupport.ViewPackage;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.AsyncTask;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ApiClient;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.ViewPackage.Management.UserAddScreen;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;
import com.support.sport.sportsupport.ViewPackage.Menu.PaymentScreen;
import com.support.sport.sportsupport.ViewPackage.Menu.UpdateProfileScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.net.InetAddress;
import java.util.Calendar;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpScreen extends AppCompatActivity {


    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText name,surname,username,mail,password;
    private View mProgressView;
    private View mLoginFormView;
    Button signUp,setBDate;
    public static EditText birtdate;
    final Context context = this;


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            //  birtdate.setText("Selected Date: " + (month + 1) + "-" + day + "-" + year);
            birtdate.setText( year +"-"+(month + 1)+"-"+day);
        }
    }



    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new SignUpScreen.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }





    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){

            Toast.makeText(this, "New profile created ! ",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignUpScreen.this, CustomerNavigationMenu.class);
            startActivity(intent);

        }else{
            if(isNetworkConnected() )
                Toast.makeText(this, "Error ! This Username is already taken.",Toast.LENGTH_LONG).show();
            else
            Toast.makeText(this, "Error ! Please check your internet connection",Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_sign_up);
        // Set up the login form.

        name = findViewById(R.id.signup_name);
        surname = findViewById(R.id.signup_surname);
        username = findViewById(R.id.signup_userName);
        birtdate = findViewById(R.id.signup_birthDate);
        mail = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signUp = findViewById(R.id.sign_up_button);
        setBDate = findViewById(R.id.BTN_M_BDate);





        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                String surnameStr = surname.getText().toString();
                String usernameStr = username.getText().toString();
                String birtdateStr = birtdate.getText().toString();
                String mailStr = mail.getText().toString();
                String passwordStr = password.getText().toString();
                int spaceController = 0;
                spaceController = controlBlank(name);
                spaceController += controlBlank(surname);
                spaceController += controlBlank(username);
                spaceController += controlBlank(birtdate);
                spaceController += controlBlank(mail);
                spaceController += controlBlank(password);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                ProfileController cr = new ProfileController();


                if (spaceController == 0) {

                //cr.addMember(0,0,usernameStr,passwordStr,"active","standard",mailStr,nameStr,surnameStr);
             //   Toast.makeText(SignUpScreen.this,"Registration Completed!",Toast.LENGTH_LONG).show();
            //    Toast.makeText(SignUpScreen.this,"Succesfully Updated!",Toast.LENGTH_LONG).show();
                    UserController controller = new UserController();
                    controller.signUp(nameStr,surnameStr,usernameStr,passwordStr,mailStr,birtdateStr);
                    Key.updatedProfile = true;

                } else {
                    spaceController = 0;
                }
             //   showDialog();


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





    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }
}

