package com.support.sport.sportsupport.ViewPackage.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ApiClient;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.R;

public class UpdateProfileScreen extends AppCompatActivity {

    private EditText updateOldUsername,updateOldPassword,updateNewUsername,updateNewPassword,
            updateMail,updateBirthday,updateName,updateSurname;
    private Button delete,update;

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
        delete = findViewById(R.id.buttonDelete);

        Member m = new Member();
        m.setAge("24/04/2018");
        m.setMail("testuser@gmail.com");
        m.setUsername("testuser");
        m.setName("Jane");
        m.setSurname("Doe");

        fillpreform(m);

        /**/

        /*final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final ProfileController cr = new ProfileController();*/

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //BURAYI DOLDUR
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // BURAYI DOLDUR
            }
        });

    }

    public void fillpreform(Member m){
        updateBirthday.setText(m.getAge());
        updateMail.setText(m.getMail());
        updateNewUsername.setText(m.getUsername());
        updateSurname.setText(m.getSurname());
        updateName.setText(m.getName());
    }

}
