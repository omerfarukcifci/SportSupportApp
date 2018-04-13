package com.support.sport.sportsupport.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.services.ApiClient;
import com.support.sport.sportsupport.services.ApiInterface;
import com.support.sport.sportsupport.services.CrudOPs;

public class UpdateProfile extends AppCompatActivity {

    private EditText updateOldUsername,updateOldPassword,updateNewUsername,updateNewPassword;
    private EditText deleteUsername,deletePassword;
    private Button delete,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        updateOldUsername = findViewById(R.id.editTextUpdate_oldusername);
        updateNewUsername = findViewById(R.id.editTextUpdate_newusername);
        updateOldPassword = findViewById(R.id.editTextUpdate_oldpassword);
        updateNewPassword = findViewById(R.id.editTextUpdate_newpassword);
        deleteUsername =    findViewById(R.id.editTextDelete_username);
        deletePassword =    findViewById(R.id.editTextDelete_password);
        delete =    findViewById(R.id.buttonDelete);
        update =    findViewById(R.id.buttonUpdate);



        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final CrudOPs cr = new CrudOPs();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DelUsernameStr = deleteUsername.getText().toString();
                String DelPasswordStr = deletePassword.getText().toString();

                cr.deleteMember(apiService,DelUsernameStr,DelPasswordStr);
                Toast.makeText(UpdateProfile.this,"User Deleted ! ",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OldUsernameStr = updateOldUsername.getText().toString();
                String OldPasswordStr = updateOldPassword.getText().toString();
                String NewUsernameStr = updateNewUsername.getText().toString();
                String NewPasswordStr = updateNewPassword.getText().toString();
                cr.updateMember(apiService,OldUsernameStr,OldPasswordStr,NewUsernameStr,NewPasswordStr,null,null,null);
                Toast.makeText(UpdateProfile.this,"User Updated ! ",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

}
