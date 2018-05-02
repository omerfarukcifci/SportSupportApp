package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Context;
import android.content.DialogInterface;
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
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.R;

public class UpdateProfileScreen extends AppCompatActivity {

    private EditText updateOldUsername,updateOldPassword,updateNewUsername,updateNewPassword,
            updateMail,updateBirthday,updateName,updateSurname;
    private Button delete,update;
    final Context context = this;
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
                int blankController = 0;

                blankController = controlBlank(updateOldPassword);
                blankController += controlBlank(updateOldUsername);

                if(blankController == 0){
                    showDialog();
                }else{

                    blankController =0;
                }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // BURAYI DOLDUR
                int blankController = 0;
                blankController = controlBlank(updateOldPassword);
                blankController += controlBlank(updateOldUsername);

                if(blankController == 0){
                    Toast.makeText(UpdateProfileScreen.this, "Profile Updated", Toast.LENGTH_LONG).show();
                }else{

                    blankController =0;
                }

            }
        });

    }
    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // alert dialog başlığını tanımlıyoruz.
      //  alertDialogBuilder.setTitle("Are you sure?");

        // alert dialog özelliklerini oluşturuyoruz.
        alertDialogBuilder
                .setMessage("Deleting this account will cause information loss. Are you sure to continue?")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher_round)
                // Evet butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UpdateProfileScreen.this, "Membership Deleted", Toast.LENGTH_LONG).show();
                    }
                })
                // İptal butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // alert dialog nesnesini oluşturuyoruz
        AlertDialog alertDialog = alertDialogBuilder.create();

        // alerti gösteriyoruz
        alertDialog.show();
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
        updateBirthday.setText(m.getAge());
        updateMail.setText(m.getMail());
        updateNewUsername.setText(m.getUsername());
        updateSurname.setText(m.getSurname());
        updateName.setText(m.getName());
    }

}
