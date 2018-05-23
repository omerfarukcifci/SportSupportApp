package com.support.sport.sportsupport.ViewPackage.Menu;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ApiClient;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.MyProfile;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import com.support.sport.sportsupport.ViewPackage.SignInScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class UpdateProfileScreen extends AppCompatActivity {

    private EditText updateOldUsername,updateOldPassword,updateNewUsername,updateNewPassword,
            updateMail,updateBirthday,updateName,updateSurname;
    private Button update;
    final Context context = this;


    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            Toast.makeText(this, "Profile information updated ! ",Toast.LENGTH_LONG).show();
            //startActivity(new Intent(UpdateProfileScreen.this, CustomerNavigationMenu.class));
            finish();
        }else{
            Toast.makeText(this, "Error ! Profile isn't updated. Try again.",Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_update_profile);

        updateOldUsername = findViewById(R.id.editTextUpdate_oldusername);
        updateNewUsername = findViewById(R.id.editTextUpdate_newusername);
        updateOldPassword = findViewById(R.id.editTextUpdate_oldpassword);
        updateNewPassword = findViewById(R.id.editTextUpdate_newpassword);
        updateMail = findViewById(R.id.editTextUpdate_mail);
        updateBirthday = findViewById(R.id.editTextUpdate_birthday);
        updateName = findViewById(R.id.editTextUpdate_newname);
        updateSurname = findViewById(R.id.editTextUpdate_newsurname);
        update =    findViewById(R.id.buttonUpdate);

        Member m1 = Key.cMember;
        fillpreform(m1);


        /**/
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int blankController = 0;
                blankController = controlBlank(updateOldPassword);
                blankController += controlBlank(updateOldUsername);

                if(blankController == 0){
                    Member m = Key.cMember;
                    String newpassword = null;
                    String newusername = null;
                    if (updateNewPassword.length()==0){
                        newpassword = null;
                    }else newpassword = updateNewPassword.getText().toString();

                    if(!Key.cMember.getUsername().equals(updateNewUsername.getText().toString())){
                        newusername = updateNewUsername.getText().toString();
                    }
                    if(updateOldUsername.getText().toString().compareTo(m.getUsername())==0 && updateOldPassword.getText().toString().compareTo(m.getPassword())==0) {

                        MyProfile controller = new MyProfile();
                        controller.updateProfileInfo(m.getId(), updateName.getText().toString(), updateSurname.getText().toString(),
                                newusername, newpassword,
                                updateMail.getText().toString(), updateBirthday.getText().toString());
                        Key.updatedProfile = true;
                    }else{
                        Toast.makeText(UpdateProfileScreen.this, "Username or password invalid ! ", Toast.LENGTH_LONG).show();
                    }
               }else{
                    blankController =0;
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

    public void fillpreform(Member m){
        updateBirthday.setText(null);
        updateMail.setText(m.getMail());
        updateNewUsername.setText(m.getUsername());
        updateSurname.setText(m.getSurname());
        updateName.setText(m.getName());
    }


}
