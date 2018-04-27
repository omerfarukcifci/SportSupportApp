package com.support.sport.sportsupport.ViewPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Controller.ApiClient;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */

public class SignInScreen extends AppCompatActivity  {

    private final String TAG = this.getClass().getSimpleName();
    private UserLoginTask mAuthTask = null;

    List<Member> memberList;
    // UI references.

    private EditText mUsernameView, mPasswordView;
    private Button signInButton;
    private CheckBox checkBoxMember,checkBoxManager,checkBoxOwner,checkBoxTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.

        mUsernameView= (EditText) findViewById(R.id.login_username);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        ControlCheckBoxes();

        signInButton = (Button) findViewById(R.id.userName_sign_in_button);

        // WE WILL HANDLE CHECKBOX IN ONCLICKLISTENER AND WE WILL CREATE INTENT ACCORDING TO CHECKBOX.
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();

                if(checkBoxManager.isChecked() || checkBoxTrainer.isChecked() || checkBoxOwner.isChecked()){
                    Intent intent = new Intent(SignInScreen.this,FragmentManagementPanel.class);
                    intent.putExtra("checkboxManager",checkBoxManager.isChecked());
                    intent.putExtra("checkboxTrainer",checkBoxTrainer.isChecked());
                    intent.putExtra("checkboxOwner",checkBoxOwner.isChecked());
                    startActivity(intent);
                }
                else if(checkBoxMember.isChecked()){
                    Intent intent2 = new Intent(SignInScreen.this,CustomerNavigationMenu.class);
                    startActivity(intent2);
                }else{
                    // Handle checkbox selections.
                }

                /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                ProfileController cr = new ProfileController();


                Member temp = cr.readOne(userName,password);
                if(temp!=null){
                    Toast.makeText(SignInActivity.this,"Hello "+temp.getName()+" :)",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignInActivity.this,MenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInActivity.this,"Password or Username invalid!",Toast.LENGTH_LONG).show();
                }*/


            }
        });
    }

    private void ControlCheckBoxes(){
        checkBoxMember = (CheckBox) findViewById(R.id.checkBox_member_login);
        checkBoxManager = (CheckBox) findViewById(R.id.checkBox_manager_login);
        checkBoxOwner = (CheckBox) findViewById(R.id.checkBox_owner_login);
        checkBoxTrainer = (CheckBox) findViewById(R.id.checkBox_trainer_login);

        checkBoxMember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkBoxMember.isChecked()){
                    checkBoxManager.setChecked(false);
                    checkBoxOwner.setChecked(false);
                    checkBoxTrainer.setChecked(false);


                    checkBoxManager.setEnabled(false);
                    checkBoxOwner.setEnabled(false);
                    checkBoxTrainer.setEnabled(false);
                }
                else{
                    checkBoxManager.setEnabled(true);
                    checkBoxOwner.setEnabled(true);
                    checkBoxTrainer.setEnabled(true);
                }
            }
        });

        checkBoxManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkBoxManager.isChecked()){
                    checkBoxMember.setChecked(false);
                    checkBoxOwner.setChecked(false);
                    checkBoxTrainer.setChecked(false);

                    checkBoxMember.setEnabled(false);
                    checkBoxOwner.setEnabled(false);
                    checkBoxTrainer.setEnabled(false);
                }else{
                    checkBoxMember.setEnabled(true);
                    checkBoxOwner.setEnabled(true);
                    checkBoxTrainer.setEnabled(true);
                }
            }
        });

        checkBoxOwner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkBoxOwner.isChecked()){
                    checkBoxManager.setChecked(false);
                    checkBoxMember.setChecked(false);
                    checkBoxTrainer.setChecked(false);

                    checkBoxManager.setEnabled(false);
                    checkBoxMember.setEnabled(false);
                    checkBoxTrainer.setEnabled(false);
                }else{
                    checkBoxManager.setEnabled(true);
                    checkBoxMember.setEnabled(true);
                    checkBoxTrainer.setEnabled(true);
                }
            }
        });

        checkBoxTrainer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkBoxTrainer.isChecked()){
                    checkBoxManager.setChecked(false);
                    checkBoxOwner.setChecked(false);
                    checkBoxMember.setChecked(false);

                    checkBoxManager.setEnabled(false);
                    checkBoxOwner.setEnabled(false);
                    checkBoxMember.setEnabled(false);
                }
                else {
                    checkBoxManager.setEnabled(true);
                    checkBoxOwner.setEnabled(true);
                    checkBoxMember.setEnabled(true);
                }
            }
        });
    }


    /*private void getAllPosts(PostService postService, final String username, String password) {
        Call<List<Member>> getAllPostsCall = postService.getAllPosts();
        final boolean checkusername=false;
        boolean checkpassword=false;
        boolean check=false;

        getAllPostsCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                //displayPost(response.body().get(0));
                for(int i=0; i<response.body().size();i++){
                    memberList.add(response.body().get(i));

                }

            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e(TAG, "Error occured while fetching post.");
            }
        });

    }*/


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }
}

